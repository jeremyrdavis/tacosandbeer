package io.arrogantprogrammer.mutiny.api;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloResource {

    @GET
    @Path("/imperative")
    public String imperativeHello() {
        return "Imperative Hello!";
    }

    @GET
    @Path("/mutiny")
    public Uni<String> mutinyHello() {
        return Uni.createFrom().item("Mutiny Hello!");
    }
}
