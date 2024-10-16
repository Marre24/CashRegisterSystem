import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {

    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAddress = "Email@Address.com";
    final private String homeAddress = "Address1";
    final private String socialSecurityNr = "11112233-4444";

    @Test
    void IsMember_NewlyCreatedValidCustomer_FalseExpected(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        assertFalse(actual.isMember());
    }

    @Test
    void SetMemberStatus_setAsTrue_TrueExpected(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        actual.setMemberStatus(true);
        assertTrue(actual.isMember());
    }

    @Test
    void ToString_ValidCustomer_CorrectStringFormat(){
        Customer actual = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);

        String toStringExpectedResult = "Name: %s %s\nSocial Security Number: %s\nPhone Number: %s\nEmail Address: %s\nHome Address: %s\nIs member: false".formatted(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);

        assertEquals(toStringExpectedResult, actual.toString());
    }

}