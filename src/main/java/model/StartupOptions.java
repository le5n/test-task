package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StartupOptions {
    NAMES("-n"),
    STATUSES("-s"),
    PORT("-p");

    private String param;
}
