import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmployee {

    final private String firstName = "FirstName";
    final private String surName = "SurName";
    final private String phoneNumber = "111111111111";
    final private String emailAddress = "Email@Adress.com";
    final private String homeAddress = "Address1";
    final private String socialSecurityNr = "11112233-4444";
    private final Product p = new Product("ProductName", 0, Producer.Arla, ProductGroup.Alcoholic);
    private final Product p1 = new Product("ProductName1", 1, Producer.Arla, ProductGroup.Candy);
    private final Product p2 = new Product("ProductName2", 2, Producer.Arla, ProductGroup.Fruit);

    @Test
    void ToString_FirstNameAndLastName_CorrectFormatting(){
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        assertEquals(firstName + " " + surName, employee.toString());
    }

    @Test
    void LogIn_OnNonActiveScanner_ActiveScannerChanged(){
        ProductScanner ps = new ProductScanner();
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);

        employee.logIntoScanner(ps);

        assertEquals(ps, employee.getActiveScanner());
        assertEquals(employee, ps.getLoggedInEmployee());
    }

    @Test
    void ScanProduct_SingleProduct_ProductScanned(){
        ProductScanner ps = new ProductScanner();
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        employee.logIntoScanner(ps);
        int expectedAmountOfOrderLines = 1;
        int expectedAmountOfScannedProducts = 1;

        employee.scanProduct(p);

        assertEquals(expectedAmountOfOrderLines, employee.getActiveScanner().getActiveOrder().getOrderLines().size());
        assertEquals(expectedAmountOfScannedProducts, employee.getActiveScanner().getActiveOrder().getProductAmount(p));
        assertTrue(employee.getActiveScanner().containsProduct(p));
    }

    @Test
    void ScanProduct_MultipleDifferentProducts_ProductsScanned(){
        ProductScanner ps = new ProductScanner();
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        employee.logIntoScanner(ps);
        int expectedAmountOfOrderLines = 3;

        employee.scanProduct(p);
        employee.scanProduct(p1);
        employee.scanProduct(p2);

        assertEquals(expectedAmountOfOrderLines, employee.getActiveScanner().getActiveOrder().getOrderLines().size());
        assertTrue(employee.getActiveScanner().containsProduct(p));
        assertTrue(employee.getActiveScanner().containsProduct(p1));
        assertTrue(employee.getActiveScanner().containsProduct(p2));
    }

    @Test
    void Add_MultipleOfSameProduct_ProductsAdded(){
        ProductScanner ps = new ProductScanner();
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        employee.logIntoScanner(ps);
        int expectedAmountOfOrderLines = 1;
        int expectedAmountOfScannedProducts = 3;

        employee.scanProduct(p);
        employee.scanProduct(p);
        employee.scanProduct(p);

        assertEquals(expectedAmountOfOrderLines, employee.getActiveScanner().getActiveOrder().getOrderLines().size());
        assertEquals(expectedAmountOfScannedProducts, employee.getActiveScanner().getActiveOrder().getProductAmount(p));
    }

    @Test
    void Add_MultipleOrderLinesWithMultipleProducts_ProductsAdded(){
        ProductScanner ps = new ProductScanner();
        Employee employee = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        employee.logIntoScanner(ps);
        int expectedAmountOfOrderLines = 3;
        int expectedAmountOfp = 3;
        int expectedAmountOfp1 = 2;
        int expectedAmountOfp2 = 1;

        employee.scanProduct(p);
        employee.scanProduct(p1);
        employee.scanProduct(p);
        employee.scanProduct(p1);
        employee.scanProduct(p);
        employee.scanProduct(p2);

        assertEquals(expectedAmountOfOrderLines, employee.getActiveScanner().getActiveOrder().getOrderLines().size());
        assertEquals(expectedAmountOfp, employee.getActiveScanner().getActiveOrder().getProductAmount(p));
        assertEquals(expectedAmountOfp1, employee.getActiveScanner().getActiveOrder().getProductAmount(p1));
        assertEquals(expectedAmountOfp2, employee.getActiveScanner().getActiveOrder().getProductAmount(p2));
    }
}
