package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a patient's NRIC in MediBase3.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)} )}
 */
public class Nric {
    public static final String MESSAGE_CONSTRAINTS = "NRIC should start with S, T, F or G, "
            + "followed by 7 digits, and end with a letter.";
    //Regex accounts for both uppercase and lowercase NRIC and FIN formats
    public static final String VALIDATION_REGEX = "(?i)^[STFG]\\d{7}[A-Z]$";

    public final String value;

    /**
     * Constructs a {@code Nric} Object.
     *
     * @param nric A valid Nric.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        this.value = nric.toUpperCase();
    }

    /**
     * Returns if a given string is a valid Nric.
     */
    public static boolean isValidNric(String nric) {
        return nric.matches(VALIDATION_REGEX);
    }

    /**
     * Parses a {@code String nric} into a {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param nric The Nric to be parsed.
     * @return The parsed Nric.
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!isValidNric(trimmedNric)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Nric)) {
            return false;
        }

        Nric otherNric = (Nric) other;
        return value.equals(otherNric.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
