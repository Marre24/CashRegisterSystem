import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrder {

    ProductType p = new ProductType("ProductName", 0, ProductGroup.Beverage);
    ProductType p1 = new ProductType("ProductName1",1, ProductGroup.Dairy);
    ProductType p2 = new ProductType("ProductName2", 2, ProductGroup.Candy);

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

        assertEquals("%s %d\n%s %d\n%s %d\n".formatted(p.getName(), p.getPrice(), p1.getName(), p1.getPrice(), p2.getName(), p2.getPrice()), order.toString());
    }

    @Test
    void ToString_MultipleProductsWithDuplicates_CorrectlyFormattedString(){
        Order order = new Order(null);

        order.addProduct(p);
        order.addProduct(p1);
        order.addProduct(p1);

        assertEquals("%s %d\n%s 2st*%d %d\n".formatted(p.getName(), p.getPrice(), p1.getName(), p1.getPrice(), p1.getPrice() * 2), order.toString());
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
