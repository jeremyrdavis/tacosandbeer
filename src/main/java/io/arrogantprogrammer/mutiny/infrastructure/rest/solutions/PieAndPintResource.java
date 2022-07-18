package io.arrogantprogrammer.mutiny.infrastructure.rest.solutions;

import io.arrogantprogrammer.mutiny.domain.PieAndPint;
import io.arrogantprogrammer.mutiny.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.domain.greeting.Greeting;
import io.arrogantprogrammer.mutiny.infrastructure.rest.clients.mutiny.MutinyBeerClient;
import io.arrogantprogrammer.mutiny.infrastructure.rest.clients.mutiny.MutinyPieClient;
import io.arrogantprogrammer.mutiny.infrastructure.services.GreetingService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/pieandpint")
@Produces(MediaType.APPLICATION_JSON)
public class PieAndPintResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PieAndPintResource.class);

    @Inject
    @RestClient
    MutinyPieClient mutinyPieClient;

    @Inject
    @RestClient
    MutinyBeerClient mutinyBeerClient;

    @Inject
    GreetingService greetingService;

    @GET
    public Uni<String> getPieAndPint() {

        return Uni
                .<String>combine()
                .all()
                .unis(
                        getRandomGreeting(),
                        getRandomBeer(),
                        getRandomPie())
                .asTuple()
                .map(t -> {
                    return new PieAndPint(t.getItem1(), t.getItem2(), t.getItem3()).toString();
                });
    }

    private Uni<PieAndPint> reallyRandomPie() {
        return Uni
                .<String>combine()
                .all()
                .unis(
                        getRandomGreeting(),
                        getRandomBeer(),
                        getRandomPie())
                .asTuple()
                .map(t -> {
                    return new PieAndPint(t.getItem1(), t.getItem2(), t.getItem3());
                });

    }

    private Uni<String> getRandomPie() {
        return Uni.<String>combine()
                .all()
                .unis(
                        mutinyPieClient.getProtein().onItem().transform(proteins -> {
                            return proteins.get(new Random().nextInt(proteins.size()));
                        }),
                        mutinyPieClient.getVeg().onItem().transform(vegetables -> {
                            return vegetables.get(new Random().nextInt(vegetables.size()));
                        }))
                .asTuple()
                .map(t -> {
                    return String.format("%s and %s pie", t.getItem1(), t.getItem2());
                });
    }

    private Uni<Beer> getRandomBeer() {
        return Multi.createBy()
                .repeating()
                .uni(AtomicInteger::new, page ->
                        mutinyBeerClient.getBeersPage(page.incrementAndGet())
                                .onFailure()
                                .recoverWithUni((Uni.createFrom().item(Collections.emptyList())))
                )
                .until(List::isEmpty)
                .onItem()
                .disjoint()
                .collect()
                .asList()
                .onItem()
                .transform(beerList -> {
                    return(Beer) beerList.get(new Random().nextInt(beerList.size()));
                });
    }

    private Uni<Greeting> getRandomGreeting() {
        return greetingService.findAll()
                .collect()
                .asList()
                .onItem()
                .transform(greetings -> {
                    return greetings.get(new Random().nextInt(greetings.size()));
                });
    }

}