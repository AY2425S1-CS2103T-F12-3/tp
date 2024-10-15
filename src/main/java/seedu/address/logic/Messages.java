package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_PERSON_NRIC_NOT_FOUND = "Unable to find the patient to delete with the given "
            + "NRIC";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d patients listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_CONSTRAINTS_LENGTH =
            "Medical condition length exceeds the limit of 45 characters";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("\nNRIC: ")
                .append(person.getNric())
                .append("\nGender: ")
                .append(person.getGender())
                .append("\nDate of Birth: ")
                .append(person.getDateOfBirth())
                .append("\nPhone: ")
                .append(person.getPhone())
                .append("\nEmail: ")
                .append(person.getEmail())
                .append("\nAddress: ")
                .append(person.getAddress())
                .append("\nPriority: ")
                .append(person.getPriority());

        builder.append("\nMedical Conditions: ");
        appendWithComma(builder, person.getMedCons());

        builder.append("\nAppointments: ");
        appendWithComma(builder, person.getAppointments());

        builder.append("\nTags: ");
        appendWithComma(builder, person.getTags());

        return builder.toString();
    }

    private static void appendWithComma(StringBuilder builder, Set<?> toAppend) {
        String result = toAppend.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        builder.append(result);
    }
}
