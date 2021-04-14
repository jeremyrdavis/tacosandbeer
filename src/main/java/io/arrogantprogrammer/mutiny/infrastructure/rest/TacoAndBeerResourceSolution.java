package io.arrogantprogrammer.mutiny.infrastructure.rest;

import io.arrogantprogrammer.mutiny.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.domain.greeting.Greeting;
import io.arrogantprogrammer.mutiny.domain.tacos.Taco;
import io.arrogantprogrammer.mutiny.infrastructure.rest.clients.MutinyBeerClient;
import io.arrogantprogrammer.mutiny.infrastructure.rest.clients.MutinyTacoClient;
import io.arrogantprogrammer.mutiny.infrastructure.services.GreetingService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/tacoandbeersolution")
public class TacoAndBeerResourceSolution {

    @Inject
    GreetingService greetingService;

    @Inject
    @RestClient
    MutinyBeerClient beerClient;

    @Inject
    @RestClient
    MutinyTacoClient tacoClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Response> tacoAndABeer() {
        return Uni.combine().all().unis(getRandomGreeting(), getRandomTaco(), getRandomBeer())
                .asTuple()
                .map(tuple -> {
                    final StringBuilder stringBuilder = new StringBuilder()
                            .append(tuple.getItem1().getBody())
                            .append("! Today's Taco is :")
                            .append(tuple.getItem2().getFilling().getName())
                            .append(" with ")
                            .append(tuple.getItem2().getMixin().getName())
                            .append(" and ")
                            .append(tuple.getItem2().getSeasoning().getName())
                            .append(" in a ")
                            .append(tuple.getItem2().getShell().getName())
                            .append(" with a ")
                            .append(tuple.getItem3().getName());
                    return Response.ok().entity(stringBuilder.toString()).build();
                });
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Response> personalizedTacoAndBeer(@PathParam("name") final String name) {
        return Uni.combine().all().unis(getRandomGreeting(), getRandomTaco(), getRandomBeer())
                .asTuple()
                .map(tuple -> {
                    final StringBuilder stringBuilder = new StringBuilder()
                            .append(tuple.getItem1().getBody())
                            .append(", ")
                            .append(name)
                            .append("! Today's Taco is :")
                            .append(tuple.getItem2().getFilling().getName())
                            .append(" with ")
                            .append(tuple.getItem2().getMixin().getName())
                            .append(" and ")
                            .append(tuple.getItem2().getSeasoning().getName())
                            .append(" in a ")
                            .append(tuple.getItem2().getShell().getName())
                            .append(" with a ")
                            .append(tuple.getItem3().getName());
                    return Response.ok().entity(stringBuilder.toString()).build();
                });
    }

    private Uni<Beer> getRandomBeer() {
        return Multi.createBy()
                .repeating()
                .uni(AtomicInteger::new, page ->
                        beerClient.getBeersPage(page.incrementAndGet())
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
        return greetingService
                .findAll()
                .collect()
                .asList()
                .onItem()
                .transform(list -> {
                    return list.get(new Random().nextInt(list.size()));
                });
    }

    private Uni<Taco> getRandomTaco() {
        return tacoClient.getRandomTaco();
    }

    private Uni<Taco> reallyRandomTaco() {
        return Uni
                .<String>combine()
                .all()
                .unis(
                        tacoClient.getBaseLayers().onItem().transform(fillings -> {
                            return fillings.get(new Random().nextInt(fillings.size()));
                        }),
                        tacoClient.getMixins().onItem().transform(mixins -> {
                            return mixins.get(new Random().nextInt(mixins.size()));
                        }),
                        tacoClient.getSeasonings().onItem().transform(seasonings -> {
                            return seasonings.get(new Random().nextInt(seasonings.size()));
                        }),
                        tacoClient.getCondiments().onItem().transform(condiments -> {
                            return condiments.get(new Random().nextInt(condiments.size()));
                        }),
                        tacoClient.getShells().onItem().transform(shells -> {
                            return shells.get(new Random().nextInt(shells.size()));
                        }))
                .asTuple()
                .map(t -> {
                    return new Taco(t.getItem1(),t.getItem2(),t.getItem3(), t.getItem4(), t.getItem5());
                });
    }

}
