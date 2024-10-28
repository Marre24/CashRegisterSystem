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
    void SocialSecurityNumber_MonthTooHigh_ReturnsFalse() {
        String invalidSSN = "20011310-1452";
        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
    }

    @Test
    void SocialSecurityNumber_MonthTooLow_ReturnsFalse() {
        String invalidSSN = "19990030-1452";
        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
    }

   @Test
   void SocialSecurityNumber_DateTooHigh_ReturnsFalse() {
        String invalidSSN = "20020333-1451";
        assertFalse(InputFormatter.isCorrectSSN(invalidSSN));
   }

   @Test
   void SocialSecurityNumber_DateTooLow_ReturnsFalse() {
        String invalidSSN = "20010500-1455";
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

    @Test
    void PhoneNumber_PhoneNrCodeSevenDigitSubscriberNumber_ReturnsTrue() {
        String invalidPhoneNr = "070-111 22 33";
        assertTrue(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_TwoDigitAreaCodeEightDigitSubscriberNumber_ReturnsTrue() {
        String validPhoneNr = "08-111 222 33";
        assertTrue(InputFormatter.isCorrectPhoneNr(validPhoneNr));
    }

    @Test
    void PhoneNumber_ThreeDigitAreaCodeSevenDigitSubscriberNumber_ReturnsTrue() {
        String validPhoneNr = "011-111 22 33";
        assertTrue(InputFormatter.isCorrectPhoneNr(validPhoneNr));
    }

    @Test
    void PhoneNumber_FourDigitAreaCodeSixDigitSubscriberNumber_ReturnsTrue() {
        String validPhoneNr = "0111-222333";
        assertTrue(InputFormatter.isCorrectPhoneNr(validPhoneNr));
    }

    @Test
    void PhoneNumber_TwoDigitAreaCodeNineDigitSubscriberNumber_ReturnsFalse() {
        String invalidPhoneNr = "08-111 222 333";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_TwoDigitAreaCodeFiveDigitSubscriberNumber_ReturnsFalse() {
        String invalidPhoneNr = "08-111 22";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_ThreeDigitAreaCodeEightDigitSubscriberNumber_ReturnsFalse() {
        String invalidPhoneNr = "011-111 222 33";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_ThreeDigitAreaCodeFourDigitSubscriberNumber_ReturnsFalse() {
        String invalidPhoneNr = "011-11 22";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_FourDigitAreaCodeSevenDigitSubscriberNumber_ReturnsFalse() {
        String invalidPhoneNr = "0111-111 22 33";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_FourDigitAreaCodeFourDigitSubscriberNumber_ReturnsFalse() {
        String invalidPhoneNr = "0111-11 22";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_SingleDigitAreaCode_ReturnsFalse() {
        String invalidPhoneNr = "0-111 222";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_FiveDigitAreaCode_ReturnsFalse() {
        String invalidPhoneNr = "01111-111 22";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }


    @Test
    void PhoneNumber_PhoneNrCodeSixDigitSubscriberNumber_ReturnsFalse() {
        String invalidPhoneNr = "070-111 222";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_PhoneNrCodeEightDigitSubscriberNumber_ReturnsFalse() {
        String invalidPhoneNr = "070-111 222 33";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_SomethingProceedsSthlmAreaCode_ReturnsFalse() {
        String invalidPhoneNr = "080-111 222";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_NothingProceedsPhoneNrCode_ReturnsFalse() {
        String invalidPhoneNr = "07-111 22 33";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }



    /* Email Address */

    /* Home Address */
}