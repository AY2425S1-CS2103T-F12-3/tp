package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a patient's gender in MediBase3.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS = "Gender should be either 'M' or 'F'";
    public static final String VALIDATION_REGEX = "[mMfF]";

    public final String value;

    /**
     * Constructs a {@code Gender} Object.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.value = gender.toUpperCase();
    }

    public static boolean isValidGender(String gender) {
        return gender.matches(VALIDATION_REGEX);
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param gender The Gender to be parsed.
     * @return The parsed Gender.
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!isValidGender(trimmedGender)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Gender)) {
            return false;
        }

        Gender otherGender = (Gender) other;
        return value.equals(otherGender.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
