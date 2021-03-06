package model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InfoEntity {
    Map<String, Person> persons;
    List<String> statuses;
    Integer runningPort;
    String defaultStatus;

    public InfoEntity(Map<String, Person> persons, List<String> statuses, Integer runningPort, Integer defaultStatus) {
        this.persons = persons;
        this.statuses = statuses;
        this.runningPort = runningPort;
        this.defaultStatus = statuses.get(defaultStatus);
    }
}
