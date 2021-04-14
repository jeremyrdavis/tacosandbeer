package io.arrogantprogrammer.mutiny.api;

import io.arrogantprogrammer.mutiny.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.domain.tacos.*;
import io.arrogantprogrammer.mutiny.infrastructure.ImperativeBeerClient;
import io.arrogantprogrammer.mutiny.infrastructure.ImperativeTacoClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Path("/imperative-api")
public class ImperativeApiResource {

    @Inject
    @RestClient
    ImperativeTacoClient tacoClient;

    @Inject
    @RestClient
    ImperativeBeerClient beerClient;

    @GET
    @Path("/tacoandbeer")
    public String getRandomTacoAndBeer() {
        String taco = getRandomTaco();
        String beer = getRandomBeer();
        return new StringBuilder()
                .append("Today's Taco and Beer is a ")
                .append(taco)
                .append(" and a cold ")
                .append(beer)
                .toString();
    }

    private String getRandomTaco() {
        List<Filling> fillings = tacoClient.getBaseLayer();
        List<Mixin> mixins = tacoClient.getMixins();
        List<Condiment> condiments = tacoClient.getCondiments();
        List<Seasoning> seasonings = tacoClient.getSeasonings();
        List<Shell> shells = tacoClient.getShells();
        return new StringBuilder()
                .append(fillings.get(new Random().nextInt(fillings.size())).getName())
                .append(" with ")
                .append(mixins.get(new Random().nextInt(mixins.size())).getName())
                .append(", ")
                .append(condiments.get(new Random().nextInt(condiments.size())).getName())
                .append(" and ")
                .append(seasonings.get(new Random().nextInt(seasonings.size())).getName())
                .append(" wrapped in a ")
                .append(shells.get(new Random().nextInt(shells.size())).getName())
                .toString();
    }

    private String getRandomBeer() {
        List<Beer> beers = new ArrayList<>();
        boolean moreBeersToRetrieve = true;
        int page = 1;
        while (moreBeersToRetrieve) {
            try {
                List<Beer> moreBeers = beerClient.getBeersPage(page);
                System.out.println("added " + moreBeers.size() + " beers from page " + page);
                if (moreBeers.size() >= 1) {
                    beers.addAll(moreBeers);
                    System.out.println("added " + moreBeers.size() + " beers");
                } else {
                    moreBeersToRetrieve = false;
                }
            } catch (Exception e) {
                moreBeersToRetrieve = false;
            }
            page++;
        }
        System.out.println(beers.size() + " beers retrieved");
        return new StringBuilder()
                .append(beers.get(new Random().nextInt(beers.size())).getName())
                .toString();
    }

}
