import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrderLine {

    @Test
    void Add_Product_AddsProductToLine(){
        OrderLine orderLine = new OrderLine();
        ProductType expected = new ProductType("Milk", 10, ProductGroup.Beverage, ProductGroup.Dairy);
        int expectedProductAmount = 1;

        orderLine.addProduct(expected);

        assertEquals(expectedProductAmount, orderLine.getAmountOfProduct());
        assertEquals(expected, orderLine.getProductType());
    }

    @Test
    void Add_TwoDifferentProduct_ExceptionThrown(){
        OrderLine orderLine = new OrderLine();
        ProductType productType = new ProductType("Milk", 10, ProductGroup.Beverage, ProductGroup.Dairy);
        ProductType otherProductType = new ProductType("Creme", 10, ProductGroup.Beverage, ProductGroup.Dairy);

        orderLine.addProduct(productType);

        assertThrows(
                IllegalArgumentException.class,
                () -> orderLine.addProduct(otherProductType),
                "Added a product to a orderLine with another productType"
        );
    }

    @Test
    void Add_TwoOfTheSameProduct_ExceptionThrown(){
        OrderLine orderLine = new OrderLine();
        ProductType expected = new ProductType("Milk", 10, ProductGroup.Beverage, ProductGroup.Dairy);
        int expectedProductAmount = 2;

        orderLine.addProduct(expected);
        orderLine.addProduct(expected);

        assertEquals(expectedProductAmount, orderLine.getAmountOfProduct());
        assertEquals(expected, orderLine.getProductType());
    }


    @Test
    void Add_Null_ExceptionThrown(){
        OrderLine orderLine = new OrderLine();

        assertThrows(
                IllegalArgumentException.class,
                () -> orderLine.addProduct(null),
                "Added a product to a orderLine with another productType"
        );
    }


}
