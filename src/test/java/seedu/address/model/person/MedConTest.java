package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MedConTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedCon(null));
    }

    @Test
    public void equals() {
        MedCon medCon = new MedCon("Diabetes");

        // same values -> returns true
        assertTrue(medCon.equals(new MedCon("Diabetes")));

        // same object -> returns true
        assertTrue(medCon.equals(medCon));

        // null -> returns false
        assertFalse(medCon.equals(null));

        // different types -> returns false
        assertFalse(medCon.equals(5.0f));

        // different values -> returns false
        assertFalse(medCon.equals(new MedCon("Hypertension")));
    }
}
