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

    @Test
    void Add_OneProduct_PriceReturned(){
        OrderLine orderLine = new OrderLine();
        long expectedPrice = 10;
        ProductType productType = new ProductType("Milk", expectedPrice, ProductGroup.Beverage, ProductGroup.Dairy);

        orderLine.addProduct(productType);

        assertEquals(expectedPrice, orderLine.getTotalPrice());
    }


    @Test
    void Add_SeveralProduct_PriceReturned(){
        OrderLine orderLine = new OrderLine();
        int productAmount = 10;
        long expectedPrice = 100;
        ProductType productType = new ProductType("Milk", expectedPrice, ProductGroup.Beverage, ProductGroup.Dairy);

        for (int i = 0; i < productAmount; i++)
            orderLine.addProduct(productType);

        assertEquals(expectedPrice * productAmount, orderLine.getTotalPrice());
    }

    @Test
    void Add_TwoProductWithPriceLongMax_ExceptionThrown(){
        OrderLine orderLine = new OrderLine();
        long expectedPrice = Long.MAX_VALUE;
        ProductType productType = new ProductType("Milk", expectedPrice, ProductGroup.Beverage, ProductGroup.Dairy);

        orderLine.addProduct(productType);

        assertThrows(
                IllegalArgumentException.class,
                () -> orderLine.addProduct(productType),
                "Tried to get total price for two products with Long.MAX price"
        );
    }


    @Test
    void ToString_OneProduct_CorrectFormatting(){
        OrderLine orderLine = new OrderLine();
        long price = 10;
        String name = "Milk";
        ProductType productType = new ProductType(name, price, ProductGroup.Beverage, ProductGroup.Dairy);
        String expectedString = name + " " + price;

        orderLine.addProduct(productType);

        assertEquals(expectedString, orderLine.toString());
    }

    @Test
    void ToString_SeveralProduct_CorrectFormatting(){
        OrderLine orderLine = new OrderLine();
        int productAmount = 5;
        long price = 10;
        String name = "Milk";
        ProductType productType = new ProductType(name, price, ProductGroup.Beverage, ProductGroup.Dairy);
        String expectedString = name + " " + productAmount + "st*" + price + " " + price * productAmount;

        for (int i = 0; i < productAmount; i++)
            orderLine.addProduct(productType);

        assertEquals(expectedString, orderLine.toString());
    }
}
