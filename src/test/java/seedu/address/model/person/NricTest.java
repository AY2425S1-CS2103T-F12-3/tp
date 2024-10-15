package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.TestValues;

public class NricTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null nric
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid nric
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("^")); // only invalid characters
        assertFalse(Nric.isValidNric("Z1234567A")); // invalid first letter
        assertFalse(Nric.isValidNric("S12345672")); // invalid last letter
        assertFalse(Nric.isValidNric("S12345678A")); // invalid length
        assertFalse(Nric.isValidNric("S1234567A example")); // extra characters

        // valid nric
        assertTrue(Nric.isValidNric("S1234567A")); // valid nric all uppercase
        assertTrue(Nric.isValidNric("s1234567a")); // valid nric all lowercase
        assertTrue(Nric.isValidNric("S1234567a")); // valid nric with lowercase for last letter
        assertTrue(Nric.isValidNric("s1234567B")); // valid nric with lowercase first letter
    }

    @Test
    public void equals() {
        Nric nric = new Nric("S1234567A");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("S1234567A")));

        // same values but lowercase -> returns true
        assertTrue(nric.equals(new Nric("s1234567a")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("S1234567B")));
    }

    @Test
    public void parseNric_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Nric.parseNric((String) null));
    }

    @Test
    public void parseNric_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> Nric.parseNric(TestValues.INVALID_NRIC));
    }

    @Test
    public void parseNric_validValueWithoutWhitespace_returnsNric() throws Exception {
        Nric expectedNric = new Nric(TestValues.VALID_NRIC);
        assertEquals(expectedNric, Nric.parseNric(TestValues.VALID_NRIC));
    }

    @Test
    public void parseNric_validValueWithWhitespace_returnsTrimmedNric() throws Exception {
        String nricWithWhitespace = TestValues.WHITESPACE + TestValues.VALID_NRIC + TestValues.WHITESPACE;
        Nric expectedNric = new Nric(TestValues.VALID_NRIC);
        assertEquals(expectedNric, Nric.parseNric(nricWithWhitespace));
    }
}
