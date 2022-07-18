package io.arrogantprogrammer.mutiny.infrastructure.rest.clients.mutiny;

import io.arrogantprogrammer.mutiny.domain.tacos.domain.*;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "tacofancy-api")
public interface MutinyTacoClient {

    @GET
    @Path("/random/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Taco> getRandomTaco();

    @GET
    @Path("/base_layers/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Filling>> getBaseLayers();

    @GET
    @Path("/mixins/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Mixin>> getMixins();

    @GET
    @Path("/mixins/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Condiment>> getCondiments();

    @GET
    @Path("/seasonings/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Seasoning>> getSeasonings();

    @GET
    @Path("/shells/")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Shell>> getShells();

    @GET
    @Path("/shells/")
    @Produces(MediaType.APPLICATION_JSON)
    Multi<Shell> getMultiShells();
}
