package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withNric("S1234567A").withDateOfBirth("2000-01-01").withGender("F")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withPriority("HIGH")
            .withAppointments("Physio:2024-12-01:1500-1600", "Orthopedic:2024-12-01:1200-1300")
            .withTags("friends")
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withNric("T0234547A").withDateOfBirth("2002-01-01").withGender("M")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withPriority("HIGH")
            .withTags("owesMoney", "friends")
            .withMedCons("Lung Cancer", "Diabetes")
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withNric("S1234167F").withDateOfBirth("2000-01-05").withGender("M")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withPriority("LOW")
            .withMedCons("Arthritis", "Scoliosis", "Myopia")
            .build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withNric("T0234567B").withDateOfBirth("2005-01-01").withGender("M")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withPriority("LOW")
            .withMedCons("Skin Cancer", "Diabetes")
            .build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withNric("T0234567F").withDateOfBirth("2001-01-01").withGender("F")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withPriority("MEDIUM")
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withNric("S1234561A").withDateOfBirth("2000-01-01").withGender("F")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withPriority("MEDIUM")
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withNric("S1234565A").withDateOfBirth("2000-01-01").withGender("M")
            .withEmail("anna@example.com").withAddress("4th street")
            .withPriority("NONE")
            .build();
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withNric("S1234568A").withDateOfBirth("2000-01-01").withGender("F")
            .withEmail("stefan@example.com").withAddress("little india")
            .build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withNric("S1234567Z").withDateOfBirth("2000-01-01").withGender("F")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
