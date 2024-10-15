import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    Product p = new Product("ProductName", null);
    Product p1 = new Product("ProductName1", null);
    Product p2 = new Product("ProductName2", null);

    @Test
    void Constructor_EmployeeCreatesOrder_EmptyOrder(){
        Employee employee = new Employee("", "", "", "", "", "");
        Order order = employee.createOrder();

        assertTrue(order.isEmpty());
    }

    @Test
    void Add_SingleProduct_ProductAdded(){
        Employee employee = new Employee("", "", "", "", "", "");

        Order order = employee.createOrder();

        order.addProduct(p);

        assertEquals(1, order.differentProductAmount());
        assertTrue(order.getOrders().containsKey(p));
    }

    @Test
    void Add_MultipleDifferentProducts_ProductsAdded(){
        Employee employee = new Employee("", "", "", "", "", "");

        Order order = employee.createOrder();

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals(3, order.differentProductAmount());
        assertTrue(order.getOrders().containsKey(p));
        assertTrue(order.getOrders().containsKey(p1));
        assertTrue(order.getOrders().containsKey(p2));
    }

    @Test
    void Add_MultipleOfSameProduct_ProductsAdded(){
        Employee employee = new Employee("", "", "", "", "", "");

        Order order = employee.createOrder();

        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p);

        assertEquals(1, order.differentProductAmount());
        assertTrue(order.getOrders().containsKey(p));
        assertEquals(3, order.getAmountOfProduct(p));
    }

}
