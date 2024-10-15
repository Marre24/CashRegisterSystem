import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEmployee {


    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAdress = "Email@Adress.com";
    final private String homeAdress = "Adress1";
    final private String socialSecurityNr = "11112233-4444";

    final String noTitle = "NO TITLE";
    final String title = "TITLE";

    @Test
    void Constructor_ValidArguments_ValidId(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        assertEquals(employee.getFullName(), employee.toString());
    }

    @Test
    void TitleGetterSetter_OtherTitle_ChangesTitle(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);

        assertEquals(noTitle, employee.getTitle());

        employee.setTitle(title);

        assertEquals(title, employee.getTitle());
    }

    @Test
    void SalaryGetterSetter_PositiveValue_ChangesSalary(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);

        assertEquals(0, employee.getMonthlySalary());

        employee.setMonthlySalary(10);

        assertEquals(10, employee.getMonthlySalary());
    }

    @Test
    void SalarySetter_NegativeValue_ExceptionThrown(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);

        assertThrows(IllegalArgumentException.class,
                () -> { employee.setMonthlySalary(-1); },
                "Negative parameter value should throw exception");
    }

}
