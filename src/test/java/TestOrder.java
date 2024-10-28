import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    Product p = new Product("ProductName", 0, Producer.Arla, ProductGroup.Beverage);
    String pExpectedOrderLineFormat = "ProductName                                      0";
    Product p1 = new Product("ProductName1",1, Producer.Arla, ProductGroup.Dairy);
    String p1ExpectedOrderLineFormat = "ProductName1                                     1";
    Product p2 = new Product("ProductName2", 2, Producer.Arla, ProductGroup.Candy);
    String p2ExpectedOrderLineFormat = "ProductName2                                     2";

    @Test
    void Constructor_EmployeeCreatesOrder_EmptyOrder(){
        Order order = new Order(null);

        assertTrue(order.getOrderLines().isEmpty());
    }

    @Test
    void Add_SingleProduct_ProductAdded(){
        Order order = new Order(null);

        order.addProduct(p);

        assertEquals(1, order.getOrderLines().size());
        assertTrue(order.containsProduct(p));
    }

    @Test
    void Add_MultipleDifferentProducts_ProductsAdded(){
        Order order = new Order(null);
        
        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);
        var orderLines = order.getOrderLines();

        assertEquals(3, orderLines.size());
        assertTrue(order.containsProduct(p));
        assertTrue(order.containsProduct(p1));
        assertTrue(order.containsProduct(p2));
    }

    @Test
    void Add_MultipleOfSameProduct_ProductsAdded(){
        Order order = new Order(null);

        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p);

        assertEquals(1, order.getOrderLines().size());
        assertTrue(order.containsProduct(p));
        assertEquals(3, order.getProductAmount(p));
    }

    //Order needs to be sorted and Product comparable
    @Test
    void ToString_MultipleProductsNoDuplicates_CorrectlyFormattedString(){
        Order order = new Order(null);


        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals(pExpectedOrderLineFormat + "\n"
                + p1ExpectedOrderLineFormat + "\n"
                + p2ExpectedOrderLineFormat, order.toString());
    }

    @Test
    void ToString_MultipleProductsWithDuplicates_CorrectlyFormattedString(){
        Order order = new Order(null);
        String p1Expected = "ProductName1 2st * 1                             2";

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p1);

        assertEquals(pExpectedOrderLineFormat + "\n"
        + p1Expected, order.toString());
    }


    @Test
    void Add_SingleProduct_TotalPriceIsCorrect(){
        Order order = new Order(null);

        order.addProduct(p);

        assertEquals(p.getPrice(), order.getTotalPrice());
    }

    @Test
    void Add_MultipleSameProducts_TotalPriceIsCorrect(){
        Order order = new Order(null);
        long expectedPrice = p.getPrice() * 3;

        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p);

        assertEquals(expectedPrice, order.getTotalPrice());
    }

    @Test
    void Add_MultipleDifferentProducts_TotalPriceIsCorrect(){
        Order order = new Order(null);
        long expectedPrice = p.getPrice() + p1.getPrice() + p2.getPrice();

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals(expectedPrice, order.getTotalPrice());
    }


    @Test
    void Add_MultipleDifferentMultipleProducts_TotalPriceIsCorrect(){
        Order order = new Order(null);
        long expectedPrice = (p.getPrice() * 3) + (p1.getPrice() * 2) + p2.getPrice();

        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals(expectedPrice, order.getTotalPrice());
    }
}
