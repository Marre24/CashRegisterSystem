import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {

    String toStringExpectedResult = "Name: FirstName SurName\nSocial Security Number: 111111111111\nPhone Number: 2222222222\nEmail Adress: Email@Adress.com\nHome Adress: Adress1";

    @Test
    void Constructor_ValidParameterInput_ObjectCreated(){
        Customer actual = new Customer ("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        assertEquals("FirstName", actual.getFirstName());
        assertEquals("SurName", actual.getSurName());
        assertEquals(111111111111L, actual.getSocialSecurityNr());
        assertEquals(2222222222L, actual.getPhoneNr());
        assertEquals("Email@Adress.com", actual.getEmailAdress());
        assertEquals("Adress1", actual.getHomeAdress());
    }

    @Test
    void setFirstName_ValidParameterInput_FirstNameChanged() {
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setFirstName("newFirstName");
        assertEquals("newFirstName", actual.getFirstName());
    }

    @Test
    void setSurName_ValidParameterInput_SurNameChanged(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setSurName("newSurName");
        assertEquals("newSurName", actual.getSurName());
    }

    @Test
    void setPhoneNr_ValidParameterInput_PhoneNrChanged(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setPhoneNr(2222222222L);
        assertEquals(2222222222L, actual.getPhoneNr());
    }

    @Test
    void setEmailAdress_ValidParameterInput_EmailAdressChanged(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setEmailAdress("New@Adress.com");
        assertEquals("New@Adress.com", actual.getEmailAdress());
    }

    @Test
    void setHomeAdress_ValidParameterInput_HomeAdressChanged(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setHomeAdress("New home adress");
        assertEquals("New home adress", actual.getHomeAdress());
    }

    @Test
    void getFullName_ValidCustomer_FirstAndSurNameRecived(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        assertEquals("FirstName SurName", actual.getFullName());
    }

    @Test
    void isMember_NewlyCreatedValidCustomer_FalseExpected(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        assertEquals(false, actual.isMember());
    }

    @Test
    void setMemberStatus_setAsTrue_TrueExpected(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        actual.setMemberStatus(true);
        assertEquals(true, actual.isMember());
    }

    @Test
    void toString_ValidCustomer_CorrectStringFormat(){
        Customer actual = new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        assertEquals(toStringExpectedResult, actual.toString());
    }
}
