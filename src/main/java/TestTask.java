import akka.http.javadsl.server.AllDirectives;
import lombok.AllArgsConstructor;
import model.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class TestTask extends AllDirectives {
    private static final ArgsParser ARGS_PARSER = new ArgsParser();
    private static final long INITIAL_DELAY = 3;
    private static final long PERIOD = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> statuses = ARGS_PARSER.getStatuses(args);
        int defaultStatus = new Random().nextInt(statuses.size() - 1);

        ARGS_PARSER.setDefaultStatus(defaultStatus);

        Map<String, Person> persons = ARGS_PARSER.getPersons(args);
        int port = ARGS_PARSER.getPort(args);

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable task1 = () -> persons.values().forEach(v -> v.updateStatus(statuses));
        ses.scheduleAtFixedRate(task1, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);

        final HttpMapper app = new HttpMapper(persons, statuses, defaultStatus, port);
        app.startServer("localhost", port);
    }
}
