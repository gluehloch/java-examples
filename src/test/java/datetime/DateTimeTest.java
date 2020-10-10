package datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void stringToZonedDateTime() {
        // var europeBerlinZone = ZoneId.of("Europe/Berlin");
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        var dateTime = LocalDateTime.parse("2020-03-24 12:00:00 +01:00", formatter);
        System.out.println(dateTime);
    }
    
}
