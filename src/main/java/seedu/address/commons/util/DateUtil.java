package seedu.address.commons.util;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class for representing dates.
 */
public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Returns if a given string is a valid date.
     *
     * @param date The date to be checked.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * Returns if a given string is a date that is after today.
     *
     * @param date The date to be checked.
     * @return true if the date is after today, false otherwise.
     */
    public static boolean isAfterToday(String date) {
        if (!isValidDate(date)) {
            return false;
        }
        LocalDate parsedDate = LocalDate.parse(date, DATE_FORMATTER);
        return parsedDate.isAfter(LocalDate.now());
    }
}
