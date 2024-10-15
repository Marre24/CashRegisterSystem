import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    Product p = new Product("ProductName", 0);
    Product p1 = new Product("ProductName1", 0);
    Product p2 = new Product("ProductName2", 0);

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

}
