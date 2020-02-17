import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import lombok.AllArgsConstructor;
import model.InfoEntity;
import model.Person;
import scala.collection.JavaConverters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HttpMapper extends HttpApp {
    private final String CONTEXT_PATH = "check";
    private final String HELP_PATH = "help";

    private final Map<String, Person> persons;
    private final List<String> statuses;
    private final Integer port;
    private final Integer defaultStatus;

    @Override
    protected Route routes() {
        List<Route> routes =
                persons.keySet().stream()
                        .map(key ->
                                path(PathMatchers.segment(CONTEXT_PATH).slash(key), () -> getMappingPersons(persons.get(key))))
                        .collect(Collectors.toList());

        return concat(
                path(PathMatchers.segment(CONTEXT_PATH).slash(HELP_PATH), () -> getMappingDefault()),
                JavaConverters.asScalaBuffer(routes).toSeq()
        );
    }

    private Route getMappingPersons(Person person) {
        return get(() ->
                completeOK(person, Jackson.marshaller()));
    }

    private Route getMappingDefault() {
        return get(() ->
                completeOK(new InfoEntity(persons, statuses, port, defaultStatus), Jackson.marshaller()));
    }
}

