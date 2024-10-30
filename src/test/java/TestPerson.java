import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {

    final static private String TO_STRING_EXPECTED_RESULT = "Name: FirstName SurName\nSocial Security Number: 111111111111\nPhone Number: 2222222222\nEmail Address: Email@Adress.com\nHome Address: Address1";
    final static private String FIRST_NAME = "FirstName";
    final static private String SUR_NAME = "SurName";
    final static private String PHONE_NUMBER = "2222222222";
    final static private String EMAIL_ADDRESS = "Email@Adress.com";
    final static private String HOME_ADDRESS = "Address1";
    final static private String SOCIAL_SECURITY_NR = "111111111111";

    final static private String OTHER_FIRST_NAME = "OtherFirstName";
    final static private String OTHER_SUR_NAME = "OtherSurName";
    final static private String OTHER_PHONE_NUMBER = "4444444444";
    final static private String OTHER_EMAIL_ADDRESS = "OtherEmail@Adress.com";
    final static private String OTHER_HOME_ADDRESS = "OtherAddress1";
    final static private String OTHER_SOCIAL_SECURITY_NR = "222222222222";

    @Test
    void Constructor_ValidParameters_ObjectCreated(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        assertEquals(FIRST_NAME, actual.getFirstName());
        assertEquals(SUR_NAME, actual.getSurName());
        assertEquals(SOCIAL_SECURITY_NR, actual.getSocialSecurityNr());
        assertEquals(PHONE_NUMBER, actual.getPhoneNr());
        assertEquals(EMAIL_ADDRESS, actual.getEmailAddress());
        assertEquals(HOME_ADDRESS, actual.getHomeAddress());
    }

    @Test
    void SetFirstName_SimpleString_FirstNameChanged() {
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        actual.setFirstName(OTHER_FIRST_NAME);
        assertEquals(OTHER_FIRST_NAME, actual.getFirstName());
    }

    @Test
    void SetSurName_SimpleString_SurNameChanged(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        actual.setSurName(OTHER_SUR_NAME);
        assertEquals(OTHER_SUR_NAME, actual.getSurName());
    }

    @Test
    void SetPhoneNr_NumberOnlyString_PhoneNrChanged(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        actual.setPhoneNr(OTHER_PHONE_NUMBER);
        assertEquals(OTHER_PHONE_NUMBER, actual.getPhoneNr());
    }

    @Test
    void SetEmailAddress_CorrectlyFormattedString_EmailAddressChanged(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        actual.setEmailAddress(OTHER_EMAIL_ADDRESS);
        assertEquals(OTHER_EMAIL_ADDRESS, actual.getEmailAddress());
    }

    @Test
    void SetHomeAddress_CorrectlyFormattedAddress_HomeAddressChanged(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        actual.setHomeAddress(OTHER_HOME_ADDRESS);
        assertEquals(OTHER_HOME_ADDRESS, actual.getHomeAddress());
    }

    @Test
    void GetFullName_ValidPerson_FirstAndSurNameReceived(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        assertEquals(FIRST_NAME + " " + SUR_NAME, actual.getFullName());
    }

    @Test
    void ToString_ValidPerson_CorrectStringFormat(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        assertEquals(TO_STRING_EXPECTED_RESULT, actual.toString());
    }

    @Test
    void AddMembership_AddMembershipToPersonWithActiveMembership_ExceptionThrown(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        Membership membership = new Membership(actual);
        assertThrows(IllegalArgumentException.class, () -> actual.addMembership(membership));
    }

    @Test
    void removeMembership_removeMembershipFromPersonWithActiveMembership_ReturnNull(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        Membership membership = new Membership(actual);
        actual.removeMembership();
        assertFalse(actual.isMember());
    }

    @Test
    void Equals_PassNull_ReturnFalse(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        assertFalse(actual.equals(null));
    }

    @Test
    void Equals_NotPerson_ReturnFalse(){
        Person actual = new Person (FIRST_NAME, SUR_NAME, SOCIAL_SECURITY_NR, PHONE_NUMBER, EMAIL_ADDRESS, HOME_ADDRESS);
        Membership membership = new Membership(actual);
        assertFalse(actual.equals(membership));
    }
}
