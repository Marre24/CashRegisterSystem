import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMembership {

    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAddress = "Email@Adress.com";
    final private String homeAddress = "Address1";
    final private String socialSecurityNr = "11112233-4444";

    @Test
    void GetOwner_WithPerson_ReturnsOwner(){
        Person member = new Person(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        Membership actual = new Membership(member);

        assertEquals(member, actual.getOwner());
    }

    @Test
    void Constructor_PersonIsNull_ThrowsException(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Membership(null),
                "Member was null" );
    }

    @Test
    void Constructor_PersonIsAlreadyMember_ThrowsException(){
        Person person = new Person(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        Membership membership = new Membership(person);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Membership(person),
                "Member was null" );
    }

}

