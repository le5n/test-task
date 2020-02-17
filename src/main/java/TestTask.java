import akka.http.javadsl.server.AllDirectives;
import lombok.AllArgsConstructor;
import model.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class TestTask extends AllDirectives {
    private static final long INITIAL_DELAY = 5;
    private static final long PERIOD = 5;
    private static final Integer DEFAULT_STATUS_INDEX = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArgsService argsService = new ArgsService(DEFAULT_STATUS_INDEX, Arrays.asList(args));

        int port = argsService.getPort();
        List<String> statuses = argsService.getStatuses();
        Map<String, Person> persons = argsService.getPersons();

        scheduledStatusUpdate(statuses, persons);

        final HttpMapper app = new HttpMapper(persons, statuses, port, DEFAULT_STATUS_INDEX);
        app.startServer("localhost", port);
    }

    private static void scheduledStatusUpdate(List<String> statuses, Map<String, Person> persons) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable scheduledTask = () -> persons.values().forEach(v -> v.updateStatus(statuses));
        ses.scheduleAtFixedRate(scheduledTask, INITIAL_DELAY, PERIOD, TimeUnit.SECONDS);
    }
}
