package io.arrogantprogrammer.mutiny.infrastructure.rest.clients.imperative;

import io.arrogantprogrammer.mutiny.domain.tacos.domain.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "imperative-tacofancy-api")
@Produces(MediaType.APPLICATION_JSON)
public interface ImperativeTacoClient {

    @GET
    @Path("/random/")
    Taco getRandomTaco();

    @GET
    @Path("/base_layers/")
    List<Filling> getBaseLayer();

    @GET
    @Path("/mixins/")
    List<Mixin> getMixins();

    @GET
    @Path("/mixins/")
    List<Condiment> getCondiments();

    @GET
    @Path("/mixins/")
    List<Seasoning> getSeasonings();

    @GET
    @Path("/mixins/")
    List<Shell> getShells();

}
