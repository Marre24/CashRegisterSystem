import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEmployee {

    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAddress = "Email@Adress.com";
    final private String homeAddress = "Address1";
    final private String socialSecurityNr = "11112233-4444";
    ProductType p = new ProductType("ProductName", 0, ProductGroup.Alcoholic);
    ProductType p1 = new ProductType("ProductName1", 1, ProductGroup.Candy);
    ProductType p2 = new ProductType("ProductName2", 2, ProductGroup.Fruit);

    @Test
    void Constructor_ValidArguments_ValidEmployee(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        assertEquals(employee.getFullName(), employee.toString());
    }

    @Test
    void Add_SingleProduct_ProductAdded(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);

        employee.scanProduct(p);

        assertEquals(1, employee.getScanner().getOrder().amountOfDifferentProducts());
        assertTrue(employee.getScanner().containsProduct(p));
    }

    @Test
    void Add_MultipleDifferentProducts_ProductsAdded(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);

        employee.scanProduct(p);
        employee.scanProduct(p1);
        employee.scanProduct(p2);

        assertEquals(3, employee.getScanner().getOrder().amountOfDifferentProducts());
        assertTrue(employee.getScanner().containsProduct(p));
        assertTrue(employee.getScanner().containsProduct(p1));
        assertTrue(employee.getScanner().containsProduct(p2));
    }

    @Test
    void Add_MultipleOfSameProduct_ProductsAdded(){
        Order order = new Order(null);

        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p);

        assertEquals(1, order.amountOfDifferentProducts());
        assertTrue(order.getProducts().containsKey(p));
        assertEquals(3, order.getAmount(p));
    }
}
