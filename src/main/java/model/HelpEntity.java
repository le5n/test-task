package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class HelpEntity {
    Map<String, Person> persons;
    Integer runningPort;
    String defaultStatus;
}
