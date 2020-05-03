package top.liyf.base.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

/**
 * @author liyf
 * Created in 2020-05-02
 */
public class LocalDateTimeUtils {

    private final static String REGEX_TIME = "^(\\d{10,13}|\\d{4}-\\d{2}-\\d{2}.\\d{2}:\\d{2}.*)$";

    public static LocalDateTime convert(String resolver) {
        if (Pattern.matches(REGEX_TIME, resolver)) {
            Instant instant;
            switch (resolver.length()) {
                case 10:
                    instant = Instant.ofEpochSecond(Long.parseLong(resolver));
                    return LocalDateTime.ofInstant(instant, ZoneId.of("GMT+8"));
                case 13:
                    instant = Instant.ofEpochMilli(Long.parseLong(resolver));
                    return LocalDateTime.ofInstant(instant, ZoneId.of("GMT+8"));
                default:
                    break;
            }

            if (resolver.endsWith("Z")) {
                return LocalDateTime.ofInstant(Instant.parse(resolver), ZoneId.of("GMT+8"));
            } else if (resolver.charAt(10) == 'T') {
                return LocalDateTime.parse(resolver, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } else if (resolver.charAt(10) == ' ') {
                return LocalDateTime.parse(resolver, new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .append(ISO_LOCAL_DATE)
                        .appendLiteral(' ')
                        .append(ISO_LOCAL_TIME)
                        .toFormatter());
            }
        }
        return null;
    }
}
