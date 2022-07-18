package io.arrogantprogrammer.mutiny.infrastructure.rest;

import io.arrogantprogrammer.mutiny.domain.greeting.Greeting;
import io.arrogantprogrammer.mutiny.infrastructure.services.GreetingService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Random;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @GET
    @Path("/imperative/{name}")
    public String imperativeHello(@PathParam("name") String name) {

        return String.format("Hello, %s!", name);
    }

    @GET
    @Path("/mutiny/{name}")
    public Uni<String> mutinyHello(@PathParam("name") String name) {
        return greetingService.findAll()
                .collect()
                .asList()
                .onItem()
                .transform(greetings -> {
                    return String.format("%s, %s!", greetings.get(new Random().nextInt(greetings.size())).getBody(), name);
                });
    }

    @GET
    public Multi<Greeting> allGreetings() {
        return greetingService.findAll();
    }

}
