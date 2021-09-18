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
	private static final DateTimeFormatter FORMATTER_WITHOUT_TIMEZONE = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss");

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

		var date_2020_03_24 = LocalDateTime.parse("2020-03-24 12:00:00", FORMATTER_WITHOUT_TIMEZONE);
		var date_2020_03_24_13 = LocalDateTime.parse("2020-03-24 13:00:00", FORMATTER_WITHOUT_TIMEZONE);

		assertThat(date_2020_03_24).isEqualTo(dateTimeOffset01);
		assertThat(date_2020_03_24).isEqualTo(dateTimeOffset02);
		assertThat(date_2020_03_24).isNotEqualTo(date_2020_03_24_13);

		ZoneId zone = ZoneId.of("Europe/Berlin");
		var zone_date_2020_03_24 = date_2020_03_24.atZone(zone);
		assertThat(zone_date_2020_03_24).isNotEqualTo(date_2020_03_24);
		assertThat(date_2020_03_24).isNotEqualTo(zone_date_2020_03_24);

		assertThat(zone_date_2020_03_24).isInstanceOf(ZonedDateTime.class);
		assertThat(date_2020_03_24).isInstanceOf(LocalDateTime.class);
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
		DateTimeFormatter airportDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		// 22.08.2016 14:30 Uhr
		LocalDateTime ldt = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30);
		System.out.println("LocalDateTime : " + airportDateTimeFormat.format(ldt));

		// UTC+8
		ZonedDateTime klDateTime = ldt.atZone(ASIA_KUALA_LUMPUR);
		System.out.println("Depart : " + airportDateTimeFormat.format(klDateTime));

		// UTC+9 and flight duration = 7 hours => #withZomeSameInstant vs. #atZone
		ZonedDateTime japanDateTime = klDateTime.withZoneSameInstant(ASIA_TOKYO).plusHours(7);
		System.out.println("Arrive +7 hours at Tokyo and Kuala Lumpur timezone: "
				+ airportDateTimeFormat.format(klDateTime.plusHours(7)));
		System.out.println("Arrive +7 hours and Tokyo timezone: " + airportDateTimeFormat.format(japanDateTime));

		System.out.println("\n---Detail---");
		System.out.println("Depart : " + klDateTime);
		System.out.println("Arrive : " + japanDateTime);
	}

}
