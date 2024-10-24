import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductScanner {

    ProductType p1 = new ProductType("ProductType 1", 10, ProductGroup.Beverage);
    ProductType p2 = new ProductType("ProductType 2", 20, ProductGroup.Beverage);

    Employee employee = new Employee("Otto", "Westling", "930220-1234", "070-12345678", "otto@king.se", "Hemvägen 12");

    @Test
    void ProductScanner_SetActiveOrder_ScannerHasActiveOrder() {
        ProductScanner p = new ProductScanner(employee);
        Order o = new Order(employee);
        p.setActiveOrder(o);
        assertEquals(o, p.getActiveOrder());
    }

    @Test
    void ProductScanner_ScanSingleProduct_ProductAddedToOrder() {
        ProductScanner p = new ProductScanner(employee);
        Order o = new Order(employee);
        p.setActiveOrder(o);
        p.scanProduct(p1);
        assertTrue(p.getActiveOrder().containsProduct(p1));
    }

    @Test
    void ProductScanner_ScanMultipleProducts_ProductsAddedToOrder () {
        ProductScanner p = new ProductScanner(employee);
        Order o = new Order(employee);
        p.setActiveOrder(o);
        p.scanProduct(p1);
        p.scanProduct(p2);
        assertTrue(p.getActiveOrder().containsProduct(p1));
        assertTrue(p.getActiveOrder().containsProduct(p2));
    }

    @Test
    void ProductScanner_GetActiveEmployee_HasActiveEmployee() {
        ProductScanner p = new ProductScanner(employee);
        assertEquals(employee, p.getActiveEmployee());
    }

    @Test
    void ProductScanner_HasActiveOrder_True() {
        ProductScanner p = new ProductScanner(employee);
        p.startNewOrder();
        assertTrue(p.hasActiveOrder());
    }

    @Test
    void OrderOnHold_ExistsAndHoldsScannedProduct() {
        ProductScanner p = new ProductScanner(employee);
        Order o = new Order(employee);
        String s = o.getId();
        p.setActiveOrder(o);
        p.scanProduct(p1);
        p.setActiveOrderOnHold();
        assertEquals(o, p.getOrderOnHold(s));
    }
}
