import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMembership {

    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAdress = "Email@Adress.com";
    final private String homeAdress = "Adress1";
    final private String socialSecurityNr = "11112233-4444";

    @Test
    void Constructor_Membership_ClassInstance(){
        Person customer = new Person(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        Membership actual = new Membership(customer);
        assertEquals(customer, actual.getOwner());
    }

    //Test some more :)
}

