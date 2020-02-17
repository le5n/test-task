import lombok.AllArgsConstructor;
import model.InfoEntity;
import model.Person;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class ArgsService {
    private final int DEFAULT_RUNNING_PORT = 8080;
    private Integer defaultStatusIndex;
    private List<String> startupOptions;

    public int getPort() {
        String port = getByOption(StartupOptions.PORT).get(0);

        if (port == null) {
            return DEFAULT_RUNNING_PORT;
        }
        return Integer.parseInt(port);
    }

    public List<String> getStatuses() {
        return getByOption(StartupOptions.STATUSES);
    }

    public Map<String, Person> getPersons() {
        List<String> names = getByOption(StartupOptions.NAMES);
        List<String> statuses = getByOption(StartupOptions.STATUSES);

        Map<String, Person> persons = new ConcurrentHashMap<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            String status = statuses.size() > i ? statuses.get(i) : statuses.get(defaultStatusIndex);
            Person person = new Person(name, status, Timestamp.valueOf(LocalDateTime.now()));

            persons.put(name, person);
        }

        return persons;
    }


    private List<String> getByOption(StartupOptions option) {
        String namesString = startupOptions.get(startupOptions.indexOf(option.getParam()) + 1);

        return Arrays.asList(namesString.split("\\s*,\\s*"));
    }
}
