package model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd HH:mm:ss z")
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
