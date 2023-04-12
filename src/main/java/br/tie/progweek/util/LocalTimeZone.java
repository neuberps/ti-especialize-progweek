package br.tie.progweek.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalTimeZone {

    private static final String ZONE_ID = "Brazil/East";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String now() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(ZONE_ID));
        return now.format(DATE_TIME_FORMATTER);
    }

}
