package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MedCon;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Command to unassign one or more medical conditions from a patient in the address book.
 */
public class DelMedConCommand extends Command {

    public static final String COMMAND_WORD = "delMedCon";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unassigns "
            + "one or more medical conditions to a patient in the address book.\n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + "c/CONDITION...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "T0123456F "
            + "c/Diabetes c/Hypertension";

    public static final String MESSAGE_DELETE_MEDCON_SUCCESS = "Removed medical condition: %1$s from Person: %2$s";
    public static final String PATIENT_DOES_NOT_EXIST = "Patient does not exist in contact list";
    public static final String MESSAGE_MEDCON_NOT_FOUND = "Condition not found: %1$s";

    private final Nric nric;
    private final Set<MedCon> medCons;

    /**
     * @param nric of the patient to unassign the medical conditions from.
     * @param medCons set of medical conditions to be removed.
     */
    public DelMedConCommand(Nric nric, Set<MedCon> medCons) {
        requireAllNonNull(nric, medCons);

        this.nric = nric;
        this.medCons = medCons;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        for (Person person : lastShownList) {
            if (person.getNric().equals(this.nric)) {
                Set<MedCon> updatedMedConSet = new HashSet<>(person.getMedCons());

                // check if the medical conditions to delete exist in the current set
                for (MedCon medCon : medCons) {
                    if (!updatedMedConSet.remove(medCon)) {
                        throw new CommandException(String.format(MESSAGE_MEDCON_NOT_FOUND, medCon.value));
                    }
                }

                // create an edited person with the updated medical conditions
                Person editedPerson = new Person(
                        person.getName(), person.getPhone(), person.getEmail(),
                        person.getNric(), person.getAddress(), person.getDateOfBirth(),
                        person.getGender(), person.getAllergies(), person.getPriority(), person.getAppointments(),
                        updatedMedConSet);

                model.setPerson(person, editedPerson);
                model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
                return new CommandResult(generateSuccessMessage(editedPerson));
            }
        }

        throw new CommandException(PATIENT_DOES_NOT_EXIST);
    }

    /**
     * Generates a command execution success message based on the removed medical conditions.
     */
    private String generateSuccessMessage(Person personToEdit) {
        StringBuilder medConsString = new StringBuilder();
        medCons.forEach(medCon -> medConsString.append(medCon.value).append(", "));

        // Remove trailing comma and space, if any
        if (medConsString.length() > 0) {
            medConsString.setLength(medConsString.length() - 2);
        }

        String resultMedCon = '[' + medConsString.toString() + ']';

        return String.format(MESSAGE_DELETE_MEDCON_SUCCESS, resultMedCon, personToEdit.getName().fullName);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DelMedConCommand)) {
            return false;
        }

        DelMedConCommand c = (DelMedConCommand) other;
        return nric.equals(c.nric)
                && medCons.equals(c.medCons);
    }
}
