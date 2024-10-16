package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.TestValues;

public class DateOfBirthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDateOfBirth = "invalid-date";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDateOfBirth));
    }

    @Test
    public void isValidDateOfBirth() {
        String futureDate = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // null date of birth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // invalid date of birth
        assertFalse(DateOfBirth.isValidDateOfBirth(futureDate)); // future date

        // valid date of birth
        assertTrue(DateOfBirth.isValidDateOfBirth("2023-01-01")); // valid date
    }

    @Test
    public void equals() {
        DateOfBirth dateOfBirth = new DateOfBirth("2000-01-01");

        // same values -> returns true
        assertTrue(dateOfBirth.equals(new DateOfBirth("2000-01-01")));

        // same object -> returns true
        assertTrue(dateOfBirth.equals(dateOfBirth));

        // null -> returns false
        assertFalse(dateOfBirth.equals(null));

        // different types -> returns false
        assertFalse(dateOfBirth.equals(5.0f));

        // different values -> returns false
        assertFalse(dateOfBirth.equals(new DateOfBirth("1999-01-01")));
    }

    @Test
    public void parseDateOfBirth_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateOfBirth.parseDateOfBirth((String) null));
    }

    @Test
    public void parseDateOfBirth_invalidFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> DateOfBirth.parseDateOfBirth(
                TestValues.INVALID_DATE_OF_BIRTH_FORMAT));
    }

    @Test
    public void parseDateOfBirth_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> DateOfBirth.parseDateOfBirth(TestValues.INVALID_DATE_OF_BIRTH_VALUE));
    }

    @Test
    public void parseDateOfBirth_validValueWithoutWhitespace_returnsDateOfBirth() throws Exception {
        DateOfBirth expectedDateOfBirth = new DateOfBirth(TestValues.VALID_DOB);
        assertEquals(expectedDateOfBirth, DateOfBirth.parseDateOfBirth(TestValues.VALID_DOB));
    }

    @Test
    public void parseDateOfBirth_validValueWithWhitespace_returnsTrimmedDateOfBirth() throws Exception {
        String dobWithWhitespace = TestValues.WHITESPACE + TestValues.VALID_DOB + TestValues.WHITESPACE;
        DateOfBirth expectedDateOfBirth = new DateOfBirth(TestValues.VALID_DOB);
        assertEquals(expectedDateOfBirth, DateOfBirth.parseDateOfBirth(dobWithWhitespace));
    }
}
