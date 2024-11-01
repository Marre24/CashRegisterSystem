import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInputFormatter {


    //
    //ADD PERSON COMMAND TESTS
    //

    /* Arguments length for add person */
    @Test
    void PersonArguments_TooManyArguments_ReturnsFalse(){
        String invalidNoOfArgs = "abc abc abc abc abc abc abc";
        assertFalse(InputFormatter.hasRightAmountOfArgsForAddPerson(invalidNoOfArgs));
    }

    @Test
    void PersonArguments_TooFewArguments_ReturnsFalse() {
        String invalidNoOfArgs = "abc abc abc";
        assertFalse(InputFormatter.hasRightAmountOfArgsForAddPerson(invalidNoOfArgs));
    }

    @Test
    void PersonArguments_CorrectAmountOfArguments_ReturnsTrue() {
        String args = "abc abc abc abc abc abc";

        assertTrue(InputFormatter.hasRightAmountOfArgsForAddPerson(args));
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
        String invalidFirstName = "JohnsonsNameIsSoFreakingLongItsTooMuchYup";
        assertFalse(InputFormatter.isCorrectName(invalidFirstName));
    }

    @Test
    void Name_EmptyName_ReturnsFalse(){
        String invalidFirstName = "";
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
    void PhoneNumber_SomethingProceedsStockholmAreaCode_ReturnsFalse() {
        String invalidPhoneNr = "080-111 222";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    @Test
    void PhoneNumber_NothingProceedsPhoneNrCode_ReturnsFalse() {
        String invalidPhoneNr = "07-111 22 33";
        assertFalse(InputFormatter.isCorrectPhoneNr(invalidPhoneNr));
    }

    /* Email address */

    @Test
    void EmailAddress_SingleCharacterUsername_ReturnTrue() {
        String validEmailAddress = "f@g.com";
        assertTrue(InputFormatter.isCorrectEmailAddress(validEmailAddress));
    }

    @Test
    void EmailAddress_DotSeparatedUserName_ReturnTrue() {
        String validEmailAddress = "f.g@g.com";
        assertTrue(InputFormatter.isCorrectEmailAddress(validEmailAddress));
    }

    @Test
    void EmailAddress_SingleDigitUsername_ReturnTrue() {
        String validEmailAddress = "1@g.com";
        assertTrue(InputFormatter.isCorrectEmailAddress(validEmailAddress));
    }

    @Test
    void EmailAddress_UsernameEndingInNumbers_ReturnTrue() {
        String validEmailAddress = "coolmonkey22@g.com";
        assertTrue(InputFormatter.isCorrectEmailAddress(validEmailAddress));
    }

    @Test
    void EmailAddress_UsernameStartingWithNumber_ReturnTrue() {
        String validEmailAddress = "69gamer@g.com";
        assertTrue(InputFormatter.isCorrectEmailAddress(validEmailAddress));
    }

    @Test
    void EmailAddress_UsernameEndingWithDot_ReturnFalse() {
        String invalidEmailAddress = "f.@g.com";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_UsernameStartingWithDot_ReturnFalse() {
        String invalidEmailAddress = ".f@g.com";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_UsernameIsDot_ReturnFalse() {
        String invalidEmailAddress = ".@g.com";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_HasNoUsername_ReturnFalse() {
        String invalidEmailAddress = "@g.com";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_UsernameHasMultipleDots_ReturnFalse() {
        String invalidEmailAddress = "f.e.g@g.com";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_TopLevelDomainIsNotDotCom_ReturnFalse() {
        String invalidEmailAddress = "f@g.se";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_MissingTopLevelDomain_ReturnFalse() {
        String invalidEmailAddress = "f@g";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_MissingDomainName_ReturnFalse() {
        String invalidEmailAddress = "f@.com";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_MissingDomainSymbol_ReturnFalse() {
        String invalidEmailAddress = "fg.com";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_OnlyUsername_ReturnFalse() {
        String invalidEmailAddress = "f";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    @Test
    void EmailAddress_MissingTopLevelDomainName_ReturnFalse() {
        String invalidEmailAddress = "f@g.";
        assertFalse(InputFormatter.isCorrectEmailAddress(invalidEmailAddress));
    }

    /* Home Address */
    @Test
    void HomeAddress_ValidAddress_ReturnsTrue() {
        String homeAddress = "thisIsAValidAddress 56";

        assertTrue(InputFormatter.isCorrectHomeAddress(homeAddress));
    }

    @Test
    void HomeAddress_AddressHasNoStreetNo_ReturnsFalse() {
        String homeAddress = "thisIsAnInvalidAddress";

        assertFalse(InputFormatter.isCorrectHomeAddress(homeAddress));
    }

    @Test
    void HomeAddress_WrongOrderNameAndNumber_ReturnsFalse() {
        String homeAddress = "56 thisIsAlsoInvalid";

        assertFalse(InputFormatter.isCorrectHomeAddress(homeAddress));
    }

    @Test
    void HomeAddress_HasNoStreetName_ReturnsFalse() {
        String homeAddress = "56";

        assertFalse(InputFormatter.isCorrectHomeAddress(homeAddress));
    }

    @Test
    void HomeAddress_EmptyString_ReturnsFalse() {
        String homeAddress = "";

        assertFalse(InputFormatter.isCorrectHomeAddress(homeAddress));
    }

    @Test
    void HomeAddress_UnformattedAddress_ReturnFormattedAddress(){
        String testAddress = "STReEtNAMe 87";
        String expectedAddress = "Streetname 87";

        assertEquals(expectedAddress, InputFormatter.formatAddress(testAddress));
    }

    @Test
    void socialSecurityIsEqual_CorrectSSNDifferentFormats_ReturnTrue(){
        String socialSecurity = "123456-7890";
        String socialSecurity1 = "11123456-7890";
        assertTrue(InputFormatter.socialSecurityIsEqual(socialSecurity, socialSecurity1));
    }

    @Test
    void FormatAddress_NullOrEmptyAddress_ReturnsNullOrEmpty(){
        assertNull(InputFormatter.formatAddress(null));
        assertEquals("", InputFormatter.formatAddress(""));
    }
}
