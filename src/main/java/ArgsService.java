import lombok.AllArgsConstructor;
import model.Person;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class ArgsService {
    private Integer defaultStatusIndex;
    private List<String> startupOptions;

    public int getPort() {
        String port = getByOption(StartupOptions.PORT).get(0);

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
        int indexOfParam = startupOptions.indexOf(option.getParam());

        if (indexOfParam == -1) {
            throw new RuntimeException("Parameter " + option + " is required but was not provided");
        }
        String namesString = startupOptions.get(indexOfParam + 1);
        return Arrays.asList(namesString.split("\\s*,\\s*"));
    }
}
