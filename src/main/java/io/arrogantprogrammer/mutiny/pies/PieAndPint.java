package io.arrogantprogrammer.mutiny.pies;

import io.arrogantprogrammer.mutiny.beers.domain.Beer;
import io.arrogantprogrammer.mutiny.greetings.domain.Greeting;

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

        return new StringBuilder()
                .append(greeting.getBody())
                .append("! Today's Pie is a ")
                .append(this.pie)
                .append(", and today's pint is ")
                .append(this.pint.getName())
                .append(" ")
                .append(this.pint.getTagline())
                .toString();
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
