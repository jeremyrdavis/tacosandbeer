package io.arrogantprogrammer.mutiny.api;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/hello")
public class HelloResource {

    @Inject
    GreetingService greetingService;

    @GET
    @Path("/imperative")
    public String imperativeHello() {
        return "Imperative Hello!";
    }

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
}
