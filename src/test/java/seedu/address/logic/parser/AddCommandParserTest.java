package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestValues.ADDRESS_DESC_AMY;
import static seedu.address.testutil.TestValues.ADDRESS_DESC_BOB;
import static seedu.address.testutil.TestValues.DOB_DESC_AMY;
import static seedu.address.testutil.TestValues.DOB_DESC_BOB;
import static seedu.address.testutil.TestValues.EMAIL_DESC_AMY;
import static seedu.address.testutil.TestValues.EMAIL_DESC_BOB;
import static seedu.address.testutil.TestValues.GENDER_DESC_AMY;
import static seedu.address.testutil.TestValues.GENDER_DESC_BOB;
import static seedu.address.testutil.TestValues.INVALID_ADDRESS_DESC;
import static seedu.address.testutil.TestValues.INVALID_DOB_FORMAT_DESC;
import static seedu.address.testutil.TestValues.INVALID_DOB_VALUE_DESC;
import static seedu.address.testutil.TestValues.INVALID_EMAIL_DESC;
import static seedu.address.testutil.TestValues.INVALID_GENDER_DESC;
import static seedu.address.testutil.TestValues.INVALID_NAME_DESC;
import static seedu.address.testutil.TestValues.INVALID_NRIC_DESC;
import static seedu.address.testutil.TestValues.INVALID_PHONE_DESC;
import static seedu.address.testutil.TestValues.INVALID_TAG_DESC;
import static seedu.address.testutil.TestValues.NAME_DESC_AMY;
import static seedu.address.testutil.TestValues.NAME_DESC_BOB;
import static seedu.address.testutil.TestValues.NRIC_DESC_AMY;
import static seedu.address.testutil.TestValues.NRIC_DESC_BOB;
import static seedu.address.testutil.TestValues.PHONE_DESC_AMY;
import static seedu.address.testutil.TestValues.PHONE_DESC_BOB;
import static seedu.address.testutil.TestValues.PREAMBLE_NON_EMPTY;
import static seedu.address.testutil.TestValues.PREAMBLE_WHITESPACE;
import static seedu.address.testutil.TestValues.PRIORITY_DESC_AMY;
import static seedu.address.testutil.TestValues.PRIORITY_DESC_BOB;
import static seedu.address.testutil.TestValues.TAG_DESC_FRIEND;
import static seedu.address.testutil.TestValues.TAG_DESC_HUSBAND;
import static seedu.address.testutil.TestValues.VALID_ADDRESS_BOB;
import static seedu.address.testutil.TestValues.VALID_DOB_BOB;
import static seedu.address.testutil.TestValues.VALID_EMAIL_BOB;
import static seedu.address.testutil.TestValues.VALID_GENDER_BOB;
import static seedu.address.testutil.TestValues.VALID_NAME_BOB;
import static seedu.address.testutil.TestValues.VALID_NRIC_BOB;
import static seedu.address.testutil.TestValues.VALID_PHONE_BOB;
import static seedu.address.testutil.TestValues.VALID_TAG_FRIEND;
import static seedu.address.testutil.TestValues.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + ADDRESS_DESC_BOB + PRIORITY_DESC_BOB,
                           new AddCommand(expectedPerson));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + NRIC_DESC_BOB
                + DOB_DESC_BOB + GENDER_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple nrics
        assertParseFailure(parser, NRIC_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        // multiple date of births
        assertParseFailure(parser, DOB_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOB));

        //multiple genders
        assertParseFailure(parser, GENDER_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + NRIC_DESC_AMY + DOB_DESC_AMY + GENDER_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE,
                        PREFIX_NRIC, PREFIX_GENDER, PREFIX_DOB));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        //invalid nric
        assertParseFailure(parser, INVALID_NRIC_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        //invalid gender
        assertParseFailure(parser, INVALID_GENDER_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        //invalid date of birth format
        assertParseFailure(parser, INVALID_DOB_FORMAT_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOB));

        //invalid date of birth value
        assertParseFailure(parser, INVALID_DOB_VALUE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOB));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        //invalid nric
        assertParseFailure(parser, validExpectedPersonString + INVALID_NRIC_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NRIC));

        //invalid gender
        assertParseFailure(parser, validExpectedPersonString + INVALID_GENDER_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        //invalid date of birth format
        assertParseFailure(parser, validExpectedPersonString + INVALID_DOB_FORMAT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOB));

        //invalid date of birth value
        assertParseFailure(parser, validExpectedPersonString + INVALID_DOB_VALUE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DOB));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().withMedCons().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + NRIC_DESC_AMY + DOB_DESC_AMY + GENDER_DESC_AMY + PRIORITY_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        //missing nric prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + VALID_NRIC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        //missing date of birth prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + VALID_DOB_BOB + GENDER_DESC_BOB, expectedMessage);

        //missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + VALID_GENDER_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + VALID_GENDER_BOB + VALID_NRIC_BOB + VALID_DOB_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid nric
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_NRIC_DESC + DOB_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Nric.MESSAGE_CONSTRAINTS);

        // invalid date of birth format
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + INVALID_DOB_FORMAT_DESC + GENDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                DateOfBirth.MESSAGE_CONSTRAINTS_WRONG_FORMAT);

        // invalid date of birth value
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + INVALID_DOB_VALUE_DESC + GENDER_DESC_BOB,
                DateOfBirth.MESSAGE_CONSTRAINTS_FUTURE_DATE);

        //invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC_BOB + DOB_DESC_BOB + INVALID_GENDER_DESC, Gender.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + NRIC_DESC_BOB + DOB_DESC_BOB + GENDER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

}
