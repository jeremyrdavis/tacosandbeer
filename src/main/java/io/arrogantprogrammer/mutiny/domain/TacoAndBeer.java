package io.arrogantprogrammer.mutiny.domain;

import io.arrogantprogrammer.mutiny.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.domain.greeting.Greeting;
import io.arrogantprogrammer.mutiny.domain.tacos.Taco;

public class TacoAndBeer {

    private Greeting greeting;

    private Taco taco;

    private Beer beer;

    public TacoAndBeer(Greeting greeting, Taco taco, Beer beer) {
        this.greeting = greeting;
        this.taco = taco;
        this.beer = beer;
    }

    public TacoAndBeer() {
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(greeting.getBody())
                .append("! Today's Taco is a ")
                .append(taco.getFilling().getName())
                .append(" with ")
                .append(taco.getMixin().getName())
                .append(", ")
                .append(taco.getSeasoning().getName())
                .append(" wrapped in ")
                .append(taco.getShell().getName())
                .append(" served with a ")
                .append(beer.getName())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TacoAndBeer that = (TacoAndBeer) o;

        if (greeting != null ? !greeting.equals(that.greeting) : that.greeting != null) return false;
        if (taco != null ? !taco.equals(that.taco) : that.taco != null) return false;
        return beer != null ? beer.equals(that.beer) : that.beer == null;
    }

    @Override
    public int hashCode() {
        int result = greeting != null ? greeting.hashCode() : 0;
        result = 31 * result + (taco != null ? taco.hashCode() : 0);
        result = 31 * result + (beer != null ? beer.hashCode() : 0);
        return result;
    }

    public Greeting getGreeting() {
        return greeting;
    }

    public void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }

    public Taco getTaco() {
        return taco;
    }

    public void setTaco(Taco taco) {
        this.taco = taco;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }
}
