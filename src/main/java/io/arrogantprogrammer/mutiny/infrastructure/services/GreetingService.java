package io.arrogantprogrammer.mutiny.infrastructure.services;

import io.arrogantprogrammer.mutiny.greetings.domain.Greeting;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped @Startup
public class GreetingService {

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Inject
    @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
    boolean schemaCreate;

    @PostConstruct
    void config() {
        if (schemaCreate) {
            initdb();
        }
    }

    private void initdb() {
        client.query("DROP TABLE IF EXISTS mutiny.greetings").execute()
                .flatMap(r -> client.query("CREATE TABLE mutiny.greetings (id SERIAL PRIMARY KEY, body TEXT NOT NULL)").execute())
                .flatMap(r -> client.query("INSERT INTO mutiny.greetings (body) VALUES ('Hello')").execute())
                .flatMap(r -> client.query("INSERT INTO mutiny.greetings (body) VALUES ('Bonjour')").execute())
                .flatMap(r -> client.query("INSERT INTO mutiny.greetings (body) VALUES ('Hola')").execute())
                .flatMap(r -> client.query("INSERT INTO mutiny.greetings (body) VALUES ('Hallo')").execute())
                .flatMap(r -> client.query("INSERT INTO mutiny.greetings (body) VALUES ('Ciao')").execute())
                .await().indefinitely();
    }

    public Uni<Greeting> findById(long id) {
        return client
                .preparedQuery("SELECT id, body FROM mutiny.greetings WHERE id = $1")
                .execute(Tuple.of(id))
                .onItem().transform(RowSet::iterator)
                .onItem()
                .transform(iterator -> iterator.hasNext() ? Greeting.from(iterator.next()) : null);
    }

    public Multi<Greeting> findAll() {
        return client.query("SELECT id, body FROM mutiny.greetings").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Greeting::from);
    }
}
