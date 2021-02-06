package datetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DateTimeTest {

    private static final String JTAG = "dateTime";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
    private static final ZoneId EUROPE_BERLIN = ZoneId.of("Europe/Berlin");
    private static final ZoneId ASIA_KUALA_LUMPUR = ZoneId.of("Asia/Kuala_Lumpur");
    private static final ZoneId ASIA_TOKYO = ZoneId.of("Asia/Tokyo");
    
    @Tag(JTAG)
    @Test
    void stringToZonedDateTime() {
        var dateTimeOffset01 = LocalDateTime.parse("2020-03-24 12:00:00 +01:00", FORMATTER);
        var dateTimeOffset02 = LocalDateTime.parse("2020-03-24 12:00:00 +02:00", FORMATTER);

        assertThat(dateTimeOffset01.toString()).isEqualTo("2020-03-24T12:00");
        assertThat(dateTimeOffset02.toString()).isEqualTo("2020-03-24T12:00");

        ZonedDateTime atZone = dateTimeOffset01.atZone(EUROPE_BERLIN);
        assertThat(atZone.toString()).isEqualTo("2020-03-24T12:00+01:00[Europe/Berlin]");

        assertThat(atZone).isNotEqualTo(dateTimeOffset01);
    }

    @Tag(JTAG)
    @Test
    void zoneId() {
        ZoneOffset zoneOffsetPlus01 = ZoneOffset.of("+01");
        assertThat(zoneOffsetPlus01).isNotNull();
        
        ZoneOffset zoneUtc = ZoneOffset.of("Z");
        assertThat(zoneUtc).isNotNull();
    }

    @Tag(JTAG)
    @Test
    void zoneDateTime() {
        DateTimeFormatter airportDateTimeFormat = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");

        LocalDateTime ldt = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30);
        System.out.println("LocalDateTime : " + airportDateTimeFormat.format(ldt));

        // UTC+8
        ZonedDateTime klDateTime = ldt.atZone(ASIA_KUALA_LUMPUR);
        System.out.println("Depart : " + airportDateTimeFormat.format(klDateTime));

        // UTC+9 and flight duration = 7 hours
        ZonedDateTime japanDateTime = klDateTime.withZoneSameInstant(ASIA_TOKYO).plusHours(7);
        System.out.println("Arrive : " + airportDateTimeFormat.format(japanDateTime));
        
        System.out.println("\n---Detail---");
        System.out.println("Depart : " + klDateTime);
        System.out.println("Arrive : " + japanDateTime);
    }

}
