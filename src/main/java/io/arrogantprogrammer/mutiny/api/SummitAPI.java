package io.arrogantprogrammer.mutiny.api;

import io.arrogantprogrammer.mutiny.domain.tacos.Taco;
import io.arrogantprogrammer.mutiny.infrastructure.ImperativeTacoClient;
import io.arrogantprogrammer.mutiny.infrastructure.ReactiveTacoClient;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/summit-api")
public class SummitAPI {

    @Inject
    @RestClient
    ImperativeTacoClient imperativeTacoClient;

    @Inject
    @RestClient
    ReactiveTacoClient reactiveTacoClient;

    @GET
    @Path("/taco")
    public Taco taco() {
        return imperativeTacoClient.getRandomTaco();
    }

    @GET
    @Path("/mutiny-taco")
    public Uni<Taco> randomTaco() {

        return Uni.createFrom().item(reactiveTacoClient.getRandomTaco());
    }


}
