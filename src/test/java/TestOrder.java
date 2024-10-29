import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    Product p = new Product("ProductName", 0, Producer.Arla, ProductGroup.Beverage);
    Product p1 = new Product("ProductName1",1, Producer.Arla, ProductGroup.Dairy);
    Product p2 = new Product("ProductName2", 2, Producer.Arla, ProductGroup.Candy);
    final static String TO_STRING_FOR_P = "ProductName                                      0";
    final static String TO_STRING_FOR_P1 = "ProductName1                                     1";
    final static String TO_STRING_FOR_P2 = "ProductName2                                     2";
    final static String TO_STRING_FOR_TWO_P = "ProductName 2st * 0                              0";
    final static String TO_STRING_FOR_TWO_P1 = "ProductName1 2st * 1                             2";

    Employee employee = new Employee("", "", "", "", "", "");

    @Test
    void Constructor_EmployeeCreatesOrder_EmptyOrder(){
        Order order = new Order(employee);

        assertTrue(order.getOrderLines().isEmpty());
    }

    @Test
    void Constructor_ResponsibleEmployeeIsNull_ExceptionThrown(){
        assertThrows(IllegalArgumentException.class, () -> new Order(null), "Employee was null");
    }

    @Test
    void AddProduct_SingleProduct_ProductAdded(){
        Order order = new Order(employee);
        int expectedAmountOfOrderLines = 1;

        order.addProduct(p);

        assertEquals(expectedAmountOfOrderLines, order.getOrderLines().size());
        assertTrue(order.containsProduct(p));
    }

    @Test
    void AddProduct_MultipleDifferentProducts_ProductsAdded(){
        Order order = new Order(employee);
        int expectedAmountOfOrderLines = 3;
        
        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);
        var orderLines = order.getOrderLines();

        assertEquals(expectedAmountOfOrderLines, orderLines.size());
        assertTrue(order.containsProduct(p));
        assertTrue(order.containsProduct(p1));
        assertTrue(order.containsProduct(p2));
    }

    @Test
    void AddProduct_MultipleOfSameProduct_ProductsAdded(){
        Order order = new Order(employee);
        int expectedAmountOfOrderLines = 1;
        int expectedAmountOfProducts = 3;

        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p);

        assertEquals(expectedAmountOfOrderLines, order.getOrderLines().size());
        assertTrue(order.containsProduct(p));
        assertEquals(expectedAmountOfProducts, order.getProductAmount(p));
    }

    @Test
    void TotalPrice_SingleProduct_TotalPriceIsCorrect(){
        Order order = new Order(employee);

        order.addProduct(p);

        assertEquals(p.getPrice(), order.getTotalPrice());
    }

    @Test
    void TotalPrice_MultipleSameProducts_TotalPriceIsCorrect(){
        Order order = new Order(employee);
        int amountOfProducts = 3;
        long expectedPrice = p.getPrice() * amountOfProducts;

        for (int i = 0; i < amountOfProducts; i++)
            order.addProduct(p);

        assertEquals(expectedPrice, order.getTotalPrice());
    }

    @Test
    void TotalPrice_MultipleDifferentProducts_TotalPriceIsCorrect(){
        Order order = new Order(employee);
        long expectedPrice = p.getPrice() + p1.getPrice() + p2.getPrice();

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals(expectedPrice, order.getTotalPrice());
    }

    @Test
    void TotalPrice_MultipleDifferentMultipleProducts_TotalPriceIsCorrect(){
        Order order = new Order(employee);
        long expectedPrice = (p.getPrice() * 3) + (p1.getPrice() * 2) + p2.getPrice();

        order.addProduct(p);
        order.addProduct(p);
        order.addProduct(p);

        order.addProduct(p1);
        order.addProduct(p1);

        order.addProduct(p2);

        assertEquals(expectedPrice, order.getTotalPrice());
    }

    @Test
    void ToString_MultipleDifferentProducts_CorrectlyFormattedString(){
        Order order = new Order(employee);

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p2);

        assertEquals(TO_STRING_FOR_P + "\n" + TO_STRING_FOR_P1 + "\n" + TO_STRING_FOR_P2, order.toString());
    }

    @Test
    void ToString_TwoOfSameProduct_CorrectlyFormattedString(){
        Order order = new Order(employee);

        order.addProduct(p);
        order.addProduct(p);

        assertEquals(TO_STRING_FOR_TWO_P, order.toString());
    }

    @Test
    void ToString_MultipleProductsWithDuplicates_CorrectlyFormattedString(){
        Order order = new Order(employee);

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p1);

        assertEquals(TO_STRING_FOR_P + "\n" + TO_STRING_FOR_TWO_P1, order.toString());
    }
}
