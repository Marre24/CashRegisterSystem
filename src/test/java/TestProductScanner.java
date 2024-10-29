import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductScanner {

    Product p1 = new Product("ProductType 1", 10, Producer.Arla, ProductGroup.Beverage);
    Product p2 = new Product("ProductType 2", 20, Producer.Arla, ProductGroup.Beverage);

    Employee employee = new Employee("Otto", "Westling", "930220-1234", "070-12345678", "otto@king.se", "Hemvägen 12");

    @Test
    void ProductScanner_SetActiveOrder_ScannerHasActiveOrder() {
        ProductScanner p = new ProductScanner();
        p.setLoggedInEmployee(employee);
        Order o = new Order(employee);
        p.setActiveOrder(o);
        assertEquals(o, p.getActiveOrder());
    }

    @Test
    void ProductScanner_ScanSingleProduct_ProductAddedToOrder() {
        ProductScanner p = new ProductScanner();
        p.setLoggedInEmployee(employee);
        Order o = new Order(employee);
        p.setActiveOrder(o);
        p.scanProduct(p1);
        assertTrue(p.getActiveOrder().containsProduct(p1));
    }

    @Test
    void ProductScanner_ScanMultipleProducts_ProductsAddedToOrder () {
        ProductScanner p = new ProductScanner();
        p.setLoggedInEmployee(employee);
        Order o = new Order(employee);
        p.setActiveOrder(o);
        p.scanProduct(p1);
        p.scanProduct(p2);
        assertTrue(p.getActiveOrder().containsProduct(p1));
        assertTrue(p.getActiveOrder().containsProduct(p2));
    }

    @Test
    void ProductScanner_GetActiveEmployee_HasActiveEmployee() {
        ProductScanner ps = new ProductScanner();
        ps.setLoggedInEmployee(employee);
        assertEquals(employee, ps.getLoggedInEmployee());
    }

    @Test
    void ProductScanner_HasActiveOrder_True() {
        ProductScanner p = new ProductScanner();
        p.setLoggedInEmployee(employee);
        p.startNewOrder();
        assertTrue(p.hasActiveOrder());
    }

    @Test
    void OrderOnHold_ExistsAndHoldsScannedProduct() {
        ProductScanner p = new ProductScanner();
        p.setLoggedInEmployee(employee);
        Order o = new Order(employee);
        String s = o.getId();
        p.setActiveOrder(o);
        p.scanProduct(p1);
        p.setActiveOrderOnHold();
        assertEquals(o, p.getOrderOnHold(s));
    }
}
