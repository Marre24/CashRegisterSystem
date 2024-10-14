import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {

    String toStringExpectedResult = "Name: FirstName SurName\nSocial Security Number: 111111111111\nPhone Number: 2222222222\nEmail Adress: Email@Adress.com\nHome Adress: Adress1";

    @Test
    void testConstructor(){
        Customer actual = new Customer ("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        assertEquals("FirstName", actual.getFirstName());
        assertEquals("SurName", actual.getSurName());
        assertEquals(111111111111L, actual.getSocialSecurityNr());
        assertEquals(2222222222L, actual.getPhoneNr());
        assertEquals("Email@Adress.com", actual.getEmailAdress());
        assertEquals("Adress1", actual.getHomeAdress());;
    }

    @Test
    void testSetFirstName() {
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setFirstName("newFirstName");
        assertEquals("newFirstName", actual.getFirstName());
    }

    @Test
    void testSetSurName(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setSurName("newSurName");
        assertEquals("newSurName", actual.getSurName());
    }

    @Test
    void testSetPhoneNr(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setPhoneNr(2222222222L);
        assertEquals(2222222222L, actual.getPhoneNr());
    }

    @Test
    void testSetEmailAdress(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setEmailAdress("New@Adress.com");
        assertEquals("New@Adress.com", actual.getEmailAdress());
    }

    @Test
    void testSetHomeAdress(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setHomeAdress("New home adress");
        assertEquals("New home adress", actual.getHomeAdress());
    }

    @Test
    void testGetFullName(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        assertEquals("FirstName SurName", actual.getFullName());
    }

    @Test
    void testToString(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        assertEquals(toStringExpectedResult, actual.toString());
    }

}
