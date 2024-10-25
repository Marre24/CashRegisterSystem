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
}
