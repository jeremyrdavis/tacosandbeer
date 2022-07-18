package io.arrogantprogrammer.mutiny.greetings.domain;

import io.vertx.mutiny.sqlclient.Row;

public class Greeting {

    private Long id;

    private String body;

    public Greeting(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public Greeting() {
    }

    public static Greeting from(Row row) {
        return new Greeting(row.getLong("id"), row.getString("body"));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Greeting{");
        sb.append("id=").append(id);
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Greeting greeting = (Greeting) o;

        if (id != null ? !id.equals(greeting.id) : greeting.id != null) return false;
        return body != null ? body.equals(greeting.body) : greeting.body == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
