package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final String DEFAULT_PRIORITY = "NONE";
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Nric("S1234567A"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new DateOfBirth("1990-01-01"), new Gender("M"),
                getTagSet("friends"), new Priority(DEFAULT_PRIORITY)),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Nric("S1234567B"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new DateOfBirth("1990-01-01"),
                    new Gender("M"),
                getTagSet("colleagues", "friends"), new Priority(DEFAULT_PRIORITY)),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Nric("S1234567C"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new DateOfBirth("1990-01-01"),
                    new Gender("M"),
                getTagSet("neighbours"), new Priority(DEFAULT_PRIORITY)),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Nric("S1234567D"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new DateOfBirth("1990-01-01"),
                new Gender("M"),
                getTagSet("family"), new Priority(DEFAULT_PRIORITY)),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Nric("S1234567E"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new DateOfBirth("1990-01-01"),
                new Gender("M"),
                getTagSet("classmates"), new Priority(DEFAULT_PRIORITY)),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Nric("S1234567F"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    new DateOfBirth("1990-01-01"),
                    new Gender("M"),
                getTagSet("colleagues"), new Priority(DEFAULT_PRIORITY))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
