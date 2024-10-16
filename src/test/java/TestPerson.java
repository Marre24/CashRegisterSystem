import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {

    final private String toStringExpectedResult = "Name: FirstName SurName\nSocial Security Number: 111111111111\nPhone Number: 2222222222\nEmail Address: Email@Adress.com\nHome Address: Address1";
    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "2222222222";
    final private String emailAddress = "Email@Adress.com";
    final private String homeAddress = "Address1";
    final private String socialSecurityNr = "111111111111";

    final private String otherFirstName = "OtherFirstName";
    final private String otherSurName = "OtherSurName";
    final private String otherPhoneNumber = "4444444444";
    final private String otherEmailAddress = "OtherEmail@Adress.com";
    final private String otherHomeAddress = "OtherAddress1";

    @Test
    void Constructor_ValidParameters_ObjectCreated(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        assertEquals(firstName, actual.getFirstName());
        assertEquals(surName, actual.getSurName());
        assertEquals(socialSecurityNr, actual.getSocialSecurityNr());
        assertEquals(phoneNumber, actual.getPhoneNr());
        assertEquals(emailAddress, actual.getEmailAddress());
        assertEquals(homeAddress, actual.getHomeAddress());
    }

    @Test
    void SetFirstName_SimpleString_FirstNameChanged() {
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        actual.setFirstName(otherFirstName);
        assertEquals(otherFirstName, actual.getFirstName());
    }

    @Test
    void SetSurName_SimpleString_SurNameChanged(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        actual.setSurName(otherSurName);
        assertEquals(otherSurName, actual.getSurName());
    }

    @Test
    void SetPhoneNr_NumberOnlyString_PhoneNrChanged(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        actual.setPhoneNr(otherPhoneNumber);
        assertEquals(otherPhoneNumber, actual.getPhoneNr());
    }

    @Test
    void SetEmailAddress_CorrectlyFormattedString_EmailAddressChanged(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        actual.setEmailAddress(otherEmailAddress);
        assertEquals(otherEmailAddress, actual.getEmailAddress());
    }

    @Test
    void SetHomeAddress_CorrectlyFormattedAddress_HomeAddressChanged(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        actual.setHomeAddress(otherHomeAddress);
        assertEquals(otherHomeAddress, actual.getHomeAddress());
    }

    @Test
    void GetFullName_ValidPerson_FirstAndSurNameReceived(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        assertEquals(firstName + " " + surName, actual.getFullName());
    }

    @Test
    void ToString_ValidPerson_CorrectStringFormat(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        assertEquals(toStringExpectedResult, actual.toString());
    }
}
