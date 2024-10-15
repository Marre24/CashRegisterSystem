import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEmployee {


    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAdress = "Email@Adress.com";
    final private String homeAdress = "Adress1";
    final private String socialSecurityNr = "11112233-4444";
    Product p = new Product("ProductName", 0);
    Product p1 = new Product("ProductName1", 0);
    Product p2 = new Product("ProductName2", 0);

    @Test
    void Constructor_ValidArguments_ValidId(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
        assertEquals(employee.getFullName(), employee.toString());
    }

    @Test
    void Add_SingleProduct_ProductAdded(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);

        employee.scanProduct(p);

        assertEquals(1, employee.getScanner().getOrder().differentProductAmount());
        assertTrue(employee.getScanner().containsKey(p));
    }

    @Test
    void Add_MultipleDifferentProducts_ProductsAdded(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);

        employee.scanProduct(p);
        employee.scanProduct(p1);
        employee.scanProduct(p2);

        assertEquals(3, employee.getScanner().getOrder().differentProductAmount());
        assertTrue(employee.getScanner().containsKey(p));
        assertTrue(employee.getScanner().containsKey(p1));
        assertTrue(employee.getScanner().containsKey(p2));
    }

    @Test
    void Add_MultipleOfSameProduct_ProductsAdded(){
        Order order = new Order(null);

        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p);

        assertEquals(1, order.differentProductAmount());
        assertTrue(order.getProducts().containsKey(p));
        assertEquals(3, order.getAmountOfProduct(p));
    }

}
