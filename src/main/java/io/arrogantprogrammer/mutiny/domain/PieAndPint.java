package io.arrogantprogrammer.mutiny.domain;

import io.arrogantprogrammer.mutiny.domain.beers.Beer;
import io.arrogantprogrammer.mutiny.domain.greeting.Greeting;

public class PieAndPint {

    private Greeting greeting;

    private Beer pint;

    private String pie;

    public PieAndPint(Greeting greeting, Beer pint, String pie) {
        this.greeting = greeting;
        this.pint = pint;
        this.pie = pie;
    }

    public PieAndPint() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PieAndPint{");
        sb.append("greeting=").append(greeting);
        sb.append(", pint=").append(pint);
        sb.append(", pie='").append(pie).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Greeting getGreeting() {
        return greeting;
    }

    public void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }

    public Beer getPint() {
        return pint;
    }

    public void setPint(Beer pint) {
        this.pint = pint;
    }

    public String getPie() {
        return pie;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }
}
