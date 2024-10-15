import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {

    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAdress = "Email@Adress.com";
    final private String homeAdress = "Adress1";
    final private String socialSecurityNr = "11112233-4444";

    @Test
    void isMember_NewlyCreatedValidCustomer_FalseExpected(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        assertFalse(actual.isMember());
    }

    @Test
    void setMemberStatus_setAsTrue_TrueExpected(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        actual.setMemberStatus(true);
        assertTrue(actual.isMember());
    }

    @Test
    void toString_ValidCustomer_CorrectStringFormat(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);

        String toStringExpectedResult = "Name: %s %s\nSocial Security Number: %s\nPhone Number: %s\nEmail Adress: %s\nHome Adress: %s\nIs member: false".formatted(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);

        assertEquals(toStringExpectedResult, actual.toString());
    }

}
