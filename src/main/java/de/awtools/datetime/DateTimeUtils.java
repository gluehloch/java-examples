package de.awtools.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;

public class DateTimeUtils {

    public static Temporal toDateTimeWithOptionalOffsetAndTimeZoneId(final String value, ZoneId zoneId) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .optionalStart() // time made optional
                .appendLiteral('T')
                .append(DateTimeFormatter.ISO_LOCAL_TIME)
                .optionalStart() // zone and offset made optional
                .appendOffsetId()
                .optionalStart()
                .appendLiteral('[')
                .parseCaseSensitive()
                .appendZoneRegionId()
                .appendLiteral(']')
                .optionalEnd()
                .optionalEnd()
                .optionalEnd()
                .toFormatter();

        TemporalAccessor temporalAccessor = formatter.parseBest(value, ZonedDateTime::from, LocalDateTime::from,
                LocalDate::from);
        if (temporalAccessor instanceof ZonedDateTime) {
            return ((ZonedDateTime) temporalAccessor);
        }
        if (temporalAccessor instanceof LocalDateTime) {
            return ((LocalDateTime) temporalAccessor).atZone(zoneId);
        }
        return ((LocalDate) temporalAccessor).atStartOfDay(zoneId);
    }

}