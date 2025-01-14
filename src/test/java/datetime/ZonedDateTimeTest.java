package datetime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

class ZonedDateTimeTest {

    @Test
    void zonedDateTime() {
        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));

        System.out.println("Current UTC time: " + utcTime);
        System.out.println("Current New York time: " + nyTime);
        System.out.println("Current Tokyo time: " + tokyoTime);
    }

}
