package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestValues.INVALID_MEDCON;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.testutil.TestValues;

public class JsonAdaptedPersonTest {

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.INVALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.VALID_GENDER,
                                      TestValues.VALID_NRIC,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null,
                                                         TestValues.VALID_PHONE,
                                                         TestValues.VALID_EMAIL,
                                                         TestValues.VALID_ADDRESS,
                                                         TestValues.VALID_DATE_OF_BIRTH,
                                                         TestValues.VALID_GENDER,
                                                         TestValues.VALID_NRIC,
                                                         TestValues.VALID_TAGS,
                                                         TestValues.VALID_PRIORITY,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.INVALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.VALID_GENDER,
                                      TestValues.VALID_NRIC,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TestValues.VALID_NAME, null,
                                                         TestValues.VALID_EMAIL,
                                                         TestValues.VALID_ADDRESS,
                                                         TestValues.VALID_DATE_OF_BIRTH,
                                                         TestValues.VALID_GENDER,
                                                         TestValues.VALID_NRIC,
                                                         TestValues.VALID_TAGS,
                                                         TestValues.VALID_PRIORITY,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.INVALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.VALID_GENDER,
                                      TestValues.VALID_NRIC,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TestValues.VALID_NAME,
                                                         TestValues.VALID_PHONE, null,
                                                         TestValues.VALID_ADDRESS,
                                                         TestValues.VALID_DATE_OF_BIRTH,
                                                         TestValues.VALID_GENDER,
                                                         TestValues.VALID_NRIC,
                                                         TestValues.VALID_TAGS,
                                                         TestValues.VALID_PRIORITY,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.INVALID_ADDRESS,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.VALID_GENDER,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TestValues.VALID_NAME,
                                                         TestValues.VALID_PHONE,
                                                         TestValues.VALID_EMAIL, null,
                                                         TestValues.VALID_ADDRESS,
                                                         TestValues.VALID_DATE_OF_BIRTH,
                                                         TestValues.VALID_GENDER,
                                                         TestValues.VALID_TAGS,
                                                         TestValues.VALID_PRIORITY,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirthDateFormat_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.INVALID_DATE_OF_BIRTH_FORMAT,
                                      TestValues.VALID_GENDER,
                                      TestValues.VALID_NRIC,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS_WRONG_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirthFutureDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.INVALID_DATE_OF_BIRTH_FUTURE_DATE,
                                      TestValues.VALID_GENDER,
                                      TestValues.VALID_NRIC,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS_FUTURE_DATE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TestValues.VALID_NAME,
                                                         TestValues.VALID_PHONE,
                                                         TestValues.VALID_EMAIL,
                                                         TestValues.VALID_ADDRESS,
                                                         null,
                                                         TestValues.VALID_GENDER,
                                                         TestValues.VALID_NRIC,
                                                         TestValues.VALID_TAGS,
                                                         TestValues.VALID_PRIORITY,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.INVALID_GENDER,
                                      TestValues.VALID_NRIC,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TestValues.VALID_NAME,
                                                         TestValues.VALID_PHONE,
                                                         TestValues.VALID_EMAIL,
                                                         TestValues.VALID_ADDRESS,
                                                         TestValues.VALID_DATE_OF_BIRTH, null,
                                                         TestValues.VALID_NRIC,
                                                         TestValues.VALID_TAGS,
                                                         TestValues.VALID_PRIORITY,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.VALID_GENDER,
                                      TestValues.INVALID_NRIC,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TestValues.VALID_NAME,
                                                         TestValues.VALID_PHONE,
                                                         TestValues.VALID_EMAIL,
                                                         TestValues.VALID_ADDRESS,
                                                         TestValues.VALID_DATE_OF_BIRTH,
                                                         TestValues.VALID_GENDER, null,
                                                         TestValues.VALID_TAGS,
                                                         TestValues.VALID_PRIORITY,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(TestValues.VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(TestValues.INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.VALID_GENDER, invalidTags,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      TestValues.VALID_MEDCONS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidAppointments_throwsIllegalValueException() {
        List<JsonAdaptedAppointment> invalidAppointments = new ArrayList<>(TestValues.VALID_APPOINTMENTS);
        invalidAppointments.add(new JsonAdaptedAppointment(TestValues.INVALID_APPOINTMENT_NAME_SERIALISED));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.VALID_GENDER,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      invalidAppointments,
                                      TestValues.VALID_MEDCONS);
    }
    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TestValues.VALID_NAME,
                                                         TestValues.VALID_PHONE,
                                                         TestValues.VALID_EMAIL,
                                                         TestValues.VALID_ADDRESS,
                                                         TestValues.VALID_DATE_OF_BIRTH,
                                                         TestValues.VALID_GENDER,
                                                         TestValues.VALID_NRIC,
                                                         TestValues.VALID_TAGS,
                                                         TestValues.INVALID_PRIORITY,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TestValues.VALID_NAME,
                                                         TestValues.VALID_PHONE,
                                                         TestValues.VALID_EMAIL,
                                                         TestValues.VALID_ADDRESS,
                                                         TestValues.VALID_DATE_OF_BIRTH,
                                                         TestValues.VALID_GENDER,
                                                         TestValues.VALID_NRIC,
                                                         TestValues.VALID_TAGS, null,
                                                         TestValues.VALID_APPOINTMENTS,
                                                         TestValues.VALID_MEDCONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMedCons_throwsIllegalValueException() {
        List<JsonAdaptedMedCon> invalidMedCons = new ArrayList<>(TestValues.VALID_MEDCONS);
        invalidMedCons.add(new JsonAdaptedMedCon(INVALID_MEDCON));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(TestValues.VALID_NAME,
                                      TestValues.VALID_PHONE,
                                      TestValues.VALID_EMAIL,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_ADDRESS,
                                      TestValues.VALID_DATE_OF_BIRTH,
                                      TestValues.VALID_GENDER,
                                      TestValues.VALID_TAGS,
                                      TestValues.VALID_PRIORITY,
                                      TestValues.VALID_APPOINTMENTS,
                                      invalidMedCons);
        assertThrows(IllegalValueException.class, person::toModelType);
    }



}
