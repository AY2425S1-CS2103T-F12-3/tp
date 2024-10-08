package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEPERIOD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricMatchesPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds a person to the address book.
 */
public class AddApptCommand extends Command {

    public static final String COMMAND_WORD = "addAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to a patient"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DATE + "Date of appointment "
            + PREFIX_TIMEPERIOD + "Time period (in HHMM-HHMM)";

    public static final String MESSAGE_SUCCESS_2S = "Appointment for %s scheduled: %s";
    public static final String MESSAGE_DUPLICATE_APPT_1S = "Appointment already exists for this date and time: %s.";

    private static final Logger logger = LogsCenter.getLogger(AddApptCommand.class);
    private final NricMatchesPredicate predicate;
    private final String newApptName;
    private final String newApptDate;
    private final String newApptTime;

    /**
     * Creates an EditCommand to edit the details of the person with the specified {@code nric}
     * using the fields in {@code editPersonDescriptor}.
     *
     * @param predicate of the person to edit.
     * @param newApptName name of appointment to be added.
     * @param newApptDate date of appointment to be added.
     * @param newApptTime time period of appointment to be added.
     */
    public AddApptCommand(NricMatchesPredicate predicate,
                          String newApptName,
                          String newApptDate,
                          String newApptTime) {
        requireNonNull(newApptName);
        requireNonNull(newApptDate);
        requireNonNull(newApptTime);
        requireNonNull(predicate);

        this.predicate = predicate;
        this.newApptName = newApptName;
        this.newApptDate = newApptDate;
        this.newApptTime = newApptTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = model.fetchPersonIfPresent(predicate)
                                   .orElseThrow(() -> new CommandException(Messages.MESSAGE_PERSON_NRIC_NOT_FOUND));
        Person editedPerson = createEditedPerson(personToEdit, newApptName, newApptDate, newApptTime);
        model.setPerson(personToEdit, editedPerson);
        logger.info("AddAppt command has been exceuted successfully on a person with NRIC: " + predicate);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS_2S, predicate, String.format("%s [ %s @ %s ]",
                                                                                            newApptName,
                                                                                            newApptDate,
                                                                                            newApptTime)));
    }


    /**
     * Creates a new <code>Person</code> with the added <code>Appointment</code>.
     *
     * @param personToEdit Person to have the appointment added to.
     * @param newApptName name of new appointment.
     * @param newApptDate date of new appointment.
     * @param newApptTime time period of new appointment.
     * @return new edited person with the added appointment.
     * @throws CommandException if appointment already exists.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             String newApptName,
                                             String newApptDate,
                                             String newApptTime) throws CommandException {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        DateOfBirth updatedDateOfBirth = personToEdit.getDateOfBirth();
        Gender updatedGender = personToEdit.getGender();
        Nric updatedNric = personToEdit.getNric();
        Set<Tag> updatedTags = personToEdit.getTags();

        ArrayList<Appointment> oldAppointmentList = new ArrayList<>(personToEdit.getAppointments());
        for (Appointment a : oldAppointmentList) {
            if (a.isClashing(newApptDate, newApptTime)) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_APPT_1S, a));
            }
        }

        oldAppointmentList.add(new Appointment(newApptName, newApptDate, newApptTime));
        Set<Appointment> newAppointments = new HashSet<>(oldAppointmentList);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedNric, updatedAddress, updatedDateOfBirth,
                          updatedGender, updatedTags, newAppointments);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApptCommand otherCommand)) {
            return false;
        }

        return predicate.equals(otherCommand.predicate)
                && newApptName.equals(otherCommand.newApptName)
                && newApptDate.equals(otherCommand.newApptDate)
                && newApptTime.equals(otherCommand.newApptTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .add("newApptName", newApptName)
                .add("newApptDate", newApptDate)
                .add("newApptTime", newApptTime)
                .toString();
    }
}
