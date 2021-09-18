package zoneddatetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ZonedDateTimeTest {

	@DisplayName("Parse UTC date time")
	@Test
	void zoneDateTime() {
		var dateTimeUTC = "2020-10-02T18:30:00Z";
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		var localDateTime = LocalDateTime.from(formatter.parse(dateTimeUTC));
		assertThat(localDateTime.getHour()).isEqualTo(18);

		ZonedDateTime utcDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.UTC);
		assertThat(utcDateTime.getHour()).isEqualTo(18);
		System.out.println(utcDateTime);

		ZoneId europeBerlinZoneId = ZoneId.of("Europe/Berlin");
		ZonedDateTime zonedDateTime = utcDateTime.withZoneSameLocal(europeBerlinZoneId);
		// ZonedDateTime zonedDateTime = ZonedDateTime.of(utcDateTime,
		// europeBerlinZoneId);
		assertThat(zonedDateTime.getHour()).isEqualTo(18);
		System.out.println(zonedDateTime);

		System.out.println(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(zonedDateTime));
		System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss x").format(zonedDateTime));
		System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").format(zonedDateTime));
		System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z").format(zonedDateTime));
	}

}
