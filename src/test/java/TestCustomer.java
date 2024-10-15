import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {

    final private String toStringExpectedResult = "Name: FirstName SurName\nSocial Security Number: 111111111111\nPhone Number: 2222222222\nEmail Adress: Email@Adress.com\nHome Adress: Adress1";
    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAdress = "Email@Adress.com";
    final private String homeAdress = "Adress1";
    final private String socialSecurityNr = "11112233-4444";

    @Test
    void isMember_NewlyCreatedValidCustomer_FalseExpected(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        assertEquals(false, actual.isMember());
    }

    @Test
    void setMemberStatus_setAsTrue_TrueExpected(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        actual.setMemberStatus(true);
        assertEquals(true, actual.isMember());
    }

    @Test
    void toString_ValidCustomer_CorrectStringFormat(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        assertEquals(toStringExpectedResult, actual.toString());
    }

}
