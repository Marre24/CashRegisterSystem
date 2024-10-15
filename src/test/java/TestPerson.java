import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {

    final private String toStringExpectedResult = "Name: FirstName SurName\nSocial Security Number: 111111111111\nPhone Number: 2222222222\nEmail Adress: Email@Adress.com\nHome Adress: Adress1";
    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAdress = "Email@Adress.com";
    final private String homeAdress = "Adress1";
    final private String socialSecurityNr = "11112233-4444";

    final private String otherFirstName = "OtherFirstName";
    final private String otherSurName = "OTherSurName";
    final private String otherPhoneNumber = "4444444444";
    final private String otherEmailAdress = "OtherEmail@Adress.com";
    final private String otherHomeAdress = "OtherAdress1";



    @Test
    void Constructor_ValidParameterInput_ObjectCreated(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        assertEquals(firstName, actual.getFirstName());
        assertEquals(surName, actual.getSurName());
        assertEquals(socialSecurityNr, actual.getSocialSecurityNr());
        assertEquals(phoneNumber, actual.getPhoneNr());
        assertEquals(emailAdress, actual.getEmailAdress());
        assertEquals(homeAdress, actual.getHomeAdress());
    }

    @Test
    void setFirstName_ValidParameterInput_FirstNameChanged() {
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        actual.setFirstName(otherFirstName);
        assertEquals(otherFirstName, actual.getFirstName());
    }

    @Test
    void setSurName_ValidParameterInput_SurNameChanged(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        actual.setSurName(otherSurName);
        assertEquals(otherSurName, actual.getSurName());
    }

    @Test
    void setPhoneNr_ValidParameterInput_PhoneNrChanged(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        actual.setPhoneNr(otherPhoneNumber);
        assertEquals(otherPhoneNumber, actual.getPhoneNr());
    }

    @Test
    void setEmailAdress_ValidParameterInput_EmailAdressChanged(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        actual.setEmailAdress(otherEmailAdress);
        assertEquals(otherEmailAdress, actual.getEmailAdress());
    }

    @Test
    void setHomeAdress_ValidParameterInput_HomeAdressChanged(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        actual.setHomeAdress(otherHomeAdress);
        assertEquals(otherHomeAdress, actual.getHomeAdress());
    }

    @Test
    void getFullName_ValidPerson_FirstAndSurNameRecived(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        assertEquals(firstName + " " + surName, actual.getFullName());
    }

    @Test
    void toString_ValidPerson_CorrectStringFormat(){
        Person actual = new Person (firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        assertEquals(toStringExpectedResult, actual.toString());
    }
}
