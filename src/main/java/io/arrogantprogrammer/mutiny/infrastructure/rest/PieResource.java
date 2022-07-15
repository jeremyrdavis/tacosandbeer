package io.arrogantprogrammer.mutiny.infrastructure.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/pies")
public class PieResource {

    List<String> vegList = new ArrayList<String>(){{
        add("cabbage");
        add("onion");
        add("leek");
        add("potato");
        add("butternut squash");
    }};

    List<String> proteinList = new ArrayList<>(){{
        add("steak");
        add("fish");
        add("chorizo");
    }};

    @GET
    @Path("/veg")
    public Response allVeg() {

        return Response.ok().entity(vegList).build();
    }

    @GET
    @Path("/protein")
    public Response allProteins() {
        return Response.ok().entity(proteinList).build();
    }
}
