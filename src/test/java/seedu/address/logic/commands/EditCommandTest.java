package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestValues.AMY;
import static seedu.address.testutil.TestValues.BOB;
import static seedu.address.testutil.TestValues.VALID_ADDRESS_AMY;
import static seedu.address.testutil.TestValues.VALID_ADDRESS_BOB;
import static seedu.address.testutil.TestValues.VALID_DOB_AMY;
import static seedu.address.testutil.TestValues.VALID_DOB_BOB;
import static seedu.address.testutil.TestValues.VALID_EMAIL_AMY;
import static seedu.address.testutil.TestValues.VALID_EMAIL_BOB;
import static seedu.address.testutil.TestValues.VALID_GENDER_AMY;
import static seedu.address.testutil.TestValues.VALID_GENDER_BOB;
import static seedu.address.testutil.TestValues.VALID_NAME_AMY;
import static seedu.address.testutil.TestValues.VALID_NAME_BOB;
import static seedu.address.testutil.TestValues.VALID_NRIC_AMY;
import static seedu.address.testutil.TestValues.VALID_NRIC_BOB;
import static seedu.address.testutil.TestValues.VALID_PHONE_AMY;
import static seedu.address.testutil.TestValues.VALID_PHONE_BOB;
import static seedu.address.testutil.TestValues.VALID_TAG_FRIEND;
import static seedu.address.testutil.TestValues.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricMatchesPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */

public class EditCommandTest {

    public static final EditPersonDescriptor DESC_AMY =
            new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                                             .withGender(VALID_GENDER_AMY)
                                             .withNric(VALID_NRIC_AMY)
                                             .withDateOfBirth(VALID_DOB_AMY)
                                             .withPhone(VALID_PHONE_AMY)
                                             .withEmail(VALID_EMAIL_AMY)
                                             .withAddress(VALID_ADDRESS_AMY)
                                             .withTags(VALID_TAG_FRIEND)
                                             .build();

    public static final EditPersonDescriptor DESC_BOB =
            new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withNric(VALID_NRIC_BOB)
                                             .withDateOfBirth(VALID_DOB_BOB).withGender(VALID_GENDER_BOB)
                                             .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                                             .withAddress(VALID_ADDRESS_BOB)
                                             .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    @Disabled
    public void execute_allFieldsSpecified_success() {
        Person editedPerson = new PersonBuilder().withNric(ALICE.getNric().value).build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(new NricMatchesPredicate(ALICE.getNric()), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditCommand editCommand = new EditCommand(new NricMatchesPredicate(DANIEL.getNric()),
                new EditPersonDescriptor());
        Person editedPerson = model.fetchPersonIfPresent(new NricMatchesPredicate(DANIEL.getNric()))
                .orElse(null);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_failure() {
        // edit person in filtered list into a duplicate in address book
        Person alice = model.fetchPersonIfPresent(new NricMatchesPredicate(ALICE.getNric())).orElse(null);
        EditCommand editCommand = new EditCommand(new NricMatchesPredicate(DANIEL.getNric()),
                new EditPersonDescriptorBuilder(alice).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_patientNricNotFound_failure() {
        Nric nric = new Nric("T9999999Z");
        NricMatchesPredicate nricNotFound = new NricMatchesPredicate(nric);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editPersonCommand = new EditCommand(nricNotFound, descriptor);

        assertCommandFailure(editPersonCommand, model, Messages.MESSAGE_PERSON_NRIC_NOT_FOUND);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(new NricMatchesPredicate(AMY.getNric()), DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(new NricMatchesPredicate(AMY.getNric()), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different nric -> returns false
        assertFalse(standardCommand.equals(new EditCommand(new NricMatchesPredicate(BOB.getNric()), DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(new NricMatchesPredicate(AMY.getNric()), DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(new NricMatchesPredicate(ALICE.getNric()), editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{predicate="
                + NricMatchesPredicate.class.getCanonicalName() + "{nric=" + ALICE.getNric()
                + "}, editPersonDescriptor=" + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
