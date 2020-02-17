import lombok.Setter;
import model.Person;
import model.StartupOptions;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Setter
public class ArgsParser {
    private Integer defaultStatus;

    public int getPort(String[] args) {
        String port = getByOption(args, StartupOptions.PORT).get(0);

        if (port == null) {
            throw new RuntimeException("Console params are incorrect");
        }

        return Integer.parseInt(port);
    }

    public Map<String, Person> getPersons(String[] args) {
        List<String> names = getByOption(args, StartupOptions.NAMES);
        List<String> statuses = getByOption(args, StartupOptions.STATUSES);

        Map<String, Person> persons = new ConcurrentHashMap<>();

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            String status = statuses.size() > i ? statuses.get(i) : statuses.get(defaultStatus);

            persons.put(name, new Person(name, status, Timestamp.valueOf(LocalDateTime.now())));
        }

        return persons;
    }

    public List<String> getStatuses(String[] args) {
        return getByOption(args, StartupOptions.STATUSES);
    }

    private List<String> getByOption(String[] args, StartupOptions option) {
        List<String> argsList = Arrays.asList(args);
        String namesString = argsList.get(argsList.indexOf(option.getParam()) + 1);

        return Arrays.asList(namesString.split("\\s*,\\s*"));
    }
}
