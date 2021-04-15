package io.arrogantprogrammer.mutiny.infrastructure.rest;

import io.arrogantprogrammer.mutiny.domain.TacoAndBeer;
import io.arrogantprogrammer.mutiny.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.domain.greeting.Greeting;
import io.arrogantprogrammer.mutiny.domain.tacos.Taco;
import io.arrogantprogrammer.mutiny.infrastructure.rest.clients.mutiny.MutinyBeerClient;
import io.arrogantprogrammer.mutiny.infrastructure.rest.clients.mutiny.MutinyTacoClient;
import io.arrogantprogrammer.mutiny.infrastructure.services.GreetingService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/tacoandbeer")
@Produces(MediaType.TEXT_PLAIN)
public class TacoAndBeerResource {

    @Inject
    GreetingService greetingService;

    @Inject
    @RestClient
    MutinyBeerClient beerClient;

    @Inject
    @RestClient
    MutinyTacoClient tacoClient;

    @GET
    public Uni<String> tacoAndBeer() {

        return Uni.createFrom().item("Taco and a Beer");
    }

}
