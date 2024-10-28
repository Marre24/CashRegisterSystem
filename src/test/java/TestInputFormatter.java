import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestInputFormatter {

    /* Arguments length */
    @Test
    void PersonArguments_TooManyArguments_ReturnsFalse(){
        String invalidNoOfArgs = "abc abc abc abc abc abc abc";
        assertFalse(InputFormatter.isCorrectFormatPerson(invalidNoOfArgs));
    }

    @Test
    void PersonArguments_TooFewArguments_ReturnsFalse() {
        String invalidNoOfArgs = "abc abc abc";
        assertFalse(InputFormatter.isCorrectFormatPerson(invalidNoOfArgs));
    }

    /* FirstName or SurName */
    @Test
    void Name_ContainsNumericCharacter_ReturnsFalse() {
        String invalidFirstName = "1John";
        assertFalse(InputFormatter.isCorrectName(invalidFirstName));
    }

    @Test
    void Name_ContainsNonAlphaNumericCharacter_ReturnsFalse() {
        String invalidFirstName = "Jo.hn";
        assertFalse(InputFormatter.isCorrectName(invalidFirstName));
    }

    @Test
    void Name_TooLongName_ReturnsFalse(){
        String invalidFirstName = "Johnsonsnameissofreakinglongitstoomuchthi";
        assertFalse(InputFormatter.isCorrectName(invalidFirstName));
    }

    @Test
    void Name_ValidName_ReturnsTrue() {
        String validName = "John";
        assertTrue(InputFormatter.isCorrectName(validName));
    }

    /* Social Security Number */
    @Test
    void SocialSecurityNumber_TooShortLength_ReturnsFalse() {
        String invalidSSN = "12345-1234";
        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
    }

    @Test
    void SocialSecurityNumber_LengthIs12_ReturnsFalse() {
        String invalidSSN = "2020201-1212";
        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
    }

    @Test
    void SocialSecurityNumber_TooLongLength_ReturnsFalse() {
        String invalidSSN = "2000021014-5235";
        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
    }

    @Test
    void SocialSecurityNumber_MissingDashAtCorrectPosition_ReturnsFalse() {
        String invalidSSN = "2001021014352";

        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
    }

    @Test
    void SocialSecurityNumber_InvalidMonth_ReturnsFalse() {
        String invalidSSN = "20011310-1452";
        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
    }

    @Test
    void SocialSecurityNumber_DashAtIncorrectPosition_ReturnsFalse() {
        String invalidSSN = "20011101-4-52";
        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
    }

    @Test
    void SocialSecurityNumber_ThirteenCharacterSSN_ReturnsTrue() {
        String validSSN = "20040624-1450";
        assertTrue(InputFormatter.isCorrectSSN(validSSN));
    }

    @Test
    void SocialSecurityNumber_ElevenCharacterSSN_ReturnsTrue() {
        String validSSN = "040624-1450";
        assertTrue(InputFormatter.isCorrectSSN(validSSN));
    }

    /* Phone Number */

    /* Email Address */

    /* Home Address */
    // <alfabetisk karaktar> <siffra> <5siffror> <alfabetiska karakt"arer>
    // the input formatter actually formats input, and we should test that it has done so at the end
    // for example user inputs SOLNa, this should pass, but it should reformat to Solna, and we must test
    // address som en klass, eller bara splitta?
    // streetName, streetNo, postNumber, postAddress
    @Test
    void HomeAddress_ValidAddress_ReturnsTrue() {
        String homeAddress = "thisIsAValidAddress 56";
        boolean isValidAddress = InputFormatter.isCorrectHomeAddress(homeAddress);
        assertTrue(isValidAddress);
    }

    @Test
    void HomeAddress_AddressHasNoStreetNo_ReturnsFalse() {
        String homeAddress = "thisIsAnInvalidAddress";
        boolean invalidAddress = InputFormatter.isCorrectHomeAddress(homeAddress);
        assertFalse(invalidAddress);
    }

    @Test
    void HomeAddress_WrongOrderNameAndNumber_ReturnsFalse() {
        String homeAddress = "56 thisIsAlsoInvalid";
        boolean invalidAddress = InputFormatter.isCorrectHomeAddress(homeAddress);
        assertFalse(invalidAddress);
    }

    @Test
    void HomeAddress_HasNoStreetName_ReturnsFalse() {
        String homeAddress = "56";
        boolean invalidAddress = InputFormatter.isCorrectHomeAddress(homeAddress);
        assertFalse(invalidAddress);
    }

    @Test
    void HomeAddress_EmptyString_ReturnsFalse() {
        String homeAddress = "";
        boolean invalidAddress = InputFormatter.isCorrectHomeAddress(homeAddress);
        assertFalse(invalidAddress);
    }
}
