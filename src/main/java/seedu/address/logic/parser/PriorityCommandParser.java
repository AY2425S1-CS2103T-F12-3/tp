package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.setPriorityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Priority;


/**
 * Parses user input for the {@link setPriorityCommand} and creates a new instance of it.
 */
public class PriorityCommandParser implements Parser<setPriorityCommand> {

    /**
     * Parses the given arguments string and creates a {@link setPriorityCommand} object.
     *
     * @param args the arguments string containing user input.
     * @return A {@link setPriorityCommand} object containing the parsed NRIC and priority.
     * @throws ParseException if the user input does not conform to the expected format or
     *         if the NRIC or priority is not provided.
     */
    public setPriorityCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NRIC, PREFIX_PRIORITY);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_PRIORITY);
        if (!argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    setPriorityCommand.MESSAGE_USAGE));
        }
        if (!argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    setPriorityCommand.MESSAGE_USAGE));
        }

        String nricStr = argMultimap.getValue(PREFIX_NRIC).orElse("");
        Nric nric = ParserUtil.parseNric(nricStr);
        String priorityStr = argMultimap.getValue(PREFIX_PRIORITY).orElse("");
        Priority priority = ParserUtil.parsePriority(priorityStr);

        return new setPriorityCommand(nric, priority);
    }
}
