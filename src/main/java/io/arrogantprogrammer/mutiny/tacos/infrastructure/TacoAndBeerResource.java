package io.arrogantprogrammer.mutiny.tacos.infrastructure;

import io.arrogantprogrammer.mutiny.infrastructure.rest.clients.mutiny.MutinyBeerClient;
import io.arrogantprogrammer.mutiny.infrastructure.rest.clients.mutiny.MutinyTacoClient;
import io.arrogantprogrammer.mutiny.infrastructure.services.GreetingService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
