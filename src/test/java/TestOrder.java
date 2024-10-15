import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    Product p = new Product("ProductName", 0);
    Product p1 = new Product("ProductName1",1);
    Product p2 = new Product("ProductName2", 2);

    @Test
    void Constructor_EmployeeCreatesOrder_EmptyOrder(){
        Order order = new Order(null);

        assertTrue(order.isEmpty());
    }

    @Test
    void Add_SingleProduct_ProductAdded(){
        Order order = new Order(null);

        order.addProduct(p);

        assertEquals(1, order.differentProductAmount());
        assertTrue(order.getProducts().containsKey(p));
    }

    @Test
    void Add_MultipleDifferentProducts_ProductsAdded(){
        Order order = new Order(null);
        
        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals(3, order.differentProductAmount());
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

        assertEquals(1, order.differentProductAmount());
        assertTrue(order.getProducts().containsKey(p));
        assertEquals(3, order.getAmountOfProduct(p));
    }

    //Order needs to be sorted and Product comparable
    @Test
    void toString_MultipleProductsNoDuplicates_CorrectlyFormattedString(){
        Employee employee = new Employee("", "", "", "", "", "");

        Order order = employee.createOrder();

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals("1 x %s %d\n1 x %s %d\n1 x %s %d".formatted(p.getName(), p.getPrice(), p1.getName(), p1.getPrice(), p2.getName(), p2.getPrice()), order.toString());
    }

    @Test
    void toString_MultipleProductsWithDuplicates_CorrectlyFormattedString(){
        Employee employee = new Employee("", "", "", "", "", "");

        Order order = employee.createOrder();

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p1);

        assertEquals("1 x %s %d\n2 x %s %d".formatted(p.getName(), p.getPrice(), p1.getName(), p1.getPrice()), order.toString());
    }
}
