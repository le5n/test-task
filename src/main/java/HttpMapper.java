import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import lombok.AllArgsConstructor;
import model.HelpEntity;
import model.Person;
import scala.collection.JavaConverters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HttpMapper extends HttpApp {
    private final Map<String, Person> PERSONS;
    private final List<String> STATUSES;
    private final Integer DEFAULT_STATUS;
    private final Integer RUNNING_PORT;

    @Override
    protected Route routes() {
        List<Route> routes =
                PERSONS.keySet().stream()
                        .map(a -> path(a, () -> get(() -> completeOK(PERSONS.get(a), Jackson.marshaller()))))
                        .collect(Collectors.toList());

        return concat(
                path("help", () -> get(() ->
                        completeOK(new HelpEntity(PERSONS, RUNNING_PORT, STATUSES.get(DEFAULT_STATUS)), Jackson.marshaller()))),
                JavaConverters.asScalaBuffer(routes).toSeq()
        );
    }
}

