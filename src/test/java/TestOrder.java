import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    ProductType p = new ProductType("ProductName", 0, ProductGroup.Beverage);
    ProductType p1 = new ProductType("ProductName1",1, ProductGroup.Dairy);
    ProductType p2 = new ProductType("ProductName2", 2, ProductGroup.Candy);

    @Test
    void Constructor_EmployeeCreatesOrder_EmptyOrder(){
        Order order = new Order(null);

        assertTrue(order.isEmpty());
    }

    @Test
    void Add_SingleProduct_ProductAdded(){
        Order order = new Order(null);

        order.addProduct(p);

        assertEquals(1, order.amountOfDifferentProducts());
        assertTrue(order.getProducts().containsKey(p));
    }

    @Test
    void Add_MultipleDifferentProducts_ProductsAdded(){
        Order order = new Order(null);
        
        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals(3, order.amountOfDifferentProducts());
        assertTrue(order.getProducts().containsKey(p));
        assertTrue(order.getProducts().containsKey(p1));
        assertTrue(order.getProducts().containsKey(p2));
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

    //Order needs to be sorted and Product comparable
    @Test
    void toString_MultipleProductsNoDuplicates_CorrectlyFormattedString(){
        Employee employee = new Employee("", "", "", "", "", "");

        Order order = new Order(employee);

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals("1 x %s %d\n1 x %s %d\n1 x %s %d".formatted(p.getName(), p.getPrice(), p1.getName(), p1.getPrice(), p2.getName(), p2.getPrice()), order.toString());
    }

    @Test
    void toString_MultipleProductsWithDuplicates_CorrectlyFormattedString(){
        Employee employee = new Employee("", "", "", "", "", "");

        Order order = new Order(employee);

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p1);

        assertEquals("1 x %s %d\n2 x %s %d".formatted(p.getName(), p.getPrice(), p1.getName(), p1.getPrice()), order.toString());
    }
}
