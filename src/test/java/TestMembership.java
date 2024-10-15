import org.junit.jupiter.api.BeforeAll;
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

    @BeforeEach
    public void init(){
        Customer customer = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        Membership m = new Membership(customer);
        m.setNextId(0);
    }

    @Test
    void Constructor_Membership_ClassInstance(){
        Customer customer = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        Membership actual = new Membership(customer);
        assertEquals(customer, actual.getMember());
    }

    @Test
    void testMemberId(){
        Customer customer = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        Membership actual = new Membership(customer);
        assertEquals(0, actual.getId());
    }

    @Test
    void testMultipleMemberIds(){
        Customer customer1 = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        Customer customer2 = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        Customer customer3 = new Customer(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        Membership actual = new Membership(customer1);
        Membership actual2 = new Membership(customer2);
        Membership actual3 = new Membership(customer3);
        assertEquals(0, actual.getId());
        assertEquals(1, actual2.getId());
        assertEquals(2, actual3.getId());
    }
}

