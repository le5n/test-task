package model;

import akka.actor.FSM;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Getter
@AllArgsConstructor
public class Person {
    private String name;
    private volatile String status;
    private Timestamp since;

    public void updateStatus(List<String> statuses) {
        int random = new Random().nextInt(statuses.size());

        String temp = status;
        status = statuses.get(random);

        if (!Objects.equals(temp, status)) {
            since = Timestamp.valueOf(LocalDateTime.now());
        }
    }
}
