package io.arrogantprogrammer.mutiny.infrastructure.rest.clients.mutiny;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RegisterRestClient(configKey = "reactive-pies")
public interface MutinyPieClient {

    @GET
    @Path("/veg")
    Uni<List<String>> getVeg();

    @GET
    @Path("/protein")
    Uni<List<String>> getProtein();
}
