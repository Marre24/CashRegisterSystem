import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMembership {

    @Test
    void testConstructor(){
        Customer exampleCustomer= new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        Membership actual = new Membership(exampleCustomer);
        assertEquals(exampleCustomer, actual.getMember());
    }

    @Test
    void testNewMemberHasZeroPoints(){
        Customer exampleCustomer= new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        Membership actual = new Membership(exampleCustomer);
        assertEquals(0, actual.getPoints());
    }

    @Test
    void testMemberId(){
        Customer exampleCustomer= new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        Membership actual = new Membership(exampleCustomer);
        assertEquals(0, actual.getId());
    }

    @Test
    void testMultipleMemberIds(){
        Customer exampleCustomer= new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        Customer exampleCustomer1= new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        Customer exampleCustomer2= new Customer("FirstName", "SurName", 111111111111L, 2222222222L, "Email@Adress.com", "Adress1");
        Membership actual = new Membership(exampleCustomer);
        Membership actual2 = new Membership(exampleCustomer1);
        Membership actual3 = new Membership(exampleCustomer2);
        assertEquals(0, actual.getId());
        assertEquals(1, actual2.getId());
        assertEquals(2, actual3.getId());
    }
}

