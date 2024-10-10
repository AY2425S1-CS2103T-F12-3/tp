package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MedCon;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String dateOfBirth;
    private final String gender;
    private final String nric;
    private final String medCon;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("dateOfBirth") String dateOfBirth,
            @JsonProperty("gender") String gender,
            @JsonProperty("nric") String nric,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
            @JsonProperty("medCon") String medCon) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.nric = nric;
        this.medCon = medCon;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (appointments != null) {
            this.appointments.addAll(appointments);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        dateOfBirth = source.getDateOfBirth().value;
        gender = source.getGender().value;
        nric = source.getNric().value;
        medCon = source.getMedCon().value;
        tags.addAll(source.getTags()
                          .stream()
                          .map(JsonAdaptedTag::new)
                          .toList());
        appointments.addAll(source.getAppointments()
                                  .stream()
                                  .map(JsonAdaptedAppointment::new)
                                  .toList());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        final List<Appointment> personAppointments = new ArrayList<>();

        for (JsonAdaptedAppointment appointment : appointments) {
            personAppointments.add(appointment.toModelType());
        }

        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (dateOfBirth == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }
        if (!DateUtil.isValidDate(dateOfBirth)) {
            throw new IllegalValueException(DateOfBirth.MESSAGE_CONSTRAINTS_WRONG_FORMAT);
        }
        if (!DateOfBirth.isValidDateOfBirth(dateOfBirth)) {
            throw new IllegalValueException(DateOfBirth.MESSAGE_CONSTRAINTS_FUTURE_DATE);
        }
        final DateOfBirth modelDateOfBirth = new DateOfBirth(dateOfBirth);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Appointment> modelAppointments = new HashSet<>(personAppointments);

        if (medCon == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, MedCon.class.getSimpleName()));
        }
        final MedCon modelMedCon = new MedCon(medCon);

        return new Person(modelName, modelPhone, modelEmail, modelNric, modelAddress, modelDateOfBirth,
                modelGender, modelTags, modelAppointments, modelMedCon);
    }

}
