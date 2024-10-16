package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.MedCon;
import seedu.address.testutil.TestValues;

public class JsonAdaptedMedConTest {

    @Test
    public void toModelType_validMedCon_returnsMedCon() throws Exception {
        JsonAdaptedMedCon jsonAdaptedMedCon = new JsonAdaptedMedCon(TestValues.VALID_MEDCON);
        MedCon expectedMedCon = new MedCon(TestValues.VALID_MEDCON);
        assertEquals(expectedMedCon, jsonAdaptedMedCon.toModelType());
    }

    @Test
    @Disabled
    public void constructor_medConObject_createsValidJsonAdaptedMedCon() {
        MedCon medCon = new MedCon(TestValues.VALID_MEDCON);
        JsonAdaptedMedCon jsonAdaptedMedCon = new JsonAdaptedMedCon(medCon);
        assertEquals(TestValues.VALID_MEDCONS, jsonAdaptedMedCon.getmedConName());
    }

    @Test
    @Disabled
    public void getmedConName_validMedCon_returnsCorrectValue() {
        JsonAdaptedMedCon jsonAdaptedMedCon = new JsonAdaptedMedCon(TestValues.VALID_MEDCON);
        assertEquals(TestValues.VALID_MEDCONS, jsonAdaptedMedCon.getmedConName());
    }
}
