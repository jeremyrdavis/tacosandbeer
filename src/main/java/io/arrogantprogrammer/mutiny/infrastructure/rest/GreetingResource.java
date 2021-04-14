package io.arrogantprogrammer.mutiny.infrastructure.rest;

import io.arrogantprogrammer.mutiny.infrastructure.services.GreetingService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
        return greetingService
                .findAll()
                .collect()
                .asList()
                .onItem()
                .transform(list -> {
                    return list.get(new Random().nextInt(list.size()));
                })
                .onItem()
                .transform(greeting -> {
                    return String.format("%s, %s!", greeting.getBody(), name);
                });
    }

/*
    @GET
    @Path("/mutiny")
    public Uni<String> mutinyHello() {

        return greetingService
                    .findAll()
                    .collect()
                    .asList()
                    .onItem()
                    .transform(l -> l.get(new Random().nextInt(l.size())))
                    .onItem()
                    .transform(greeting -> {
                        return greeting.getBody() + ", Mutiny!";
                    });
    }
*/
}
