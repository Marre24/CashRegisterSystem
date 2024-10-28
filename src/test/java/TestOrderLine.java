import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOrderLine {

    private final int MAX_LENGTH_ORDERLINE = 50;

    @Test
    void Add_Product_AddsProductToLine(){
        OrderLine orderLine = new OrderLine();
        Product expected = new Product("Milk", 10, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);
        int expectedProductAmount = 1;

        orderLine.addProduct(expected);

        assertEquals(expectedProductAmount, orderLine.getAmountOfProduct());
        assertEquals(expected, orderLine.getProductType());
    }

    @Test
    void Add_TwoDifferentProduct_ExceptionThrown(){
        OrderLine orderLine = new OrderLine();
        Product product = new Product("Milk", 10, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);
        Product otherProduct = new Product("Creme", 10, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);

        orderLine.addProduct(product);

        assertThrows(
                IllegalArgumentException.class,
                () -> orderLine.addProduct(otherProduct),
                "Added a product to a orderLine with another productType"
        );
    }

    @Test
    void Add_TwoOfTheSameProduct_ExceptionThrown(){
        OrderLine orderLine = new OrderLine();
        Product expected = new Product("Milk", 10, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);
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
        Product product = new Product("Milk", expectedPrice, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);

        orderLine.addProduct(product);

        assertEquals(expectedPrice, orderLine.getTotalPrice());
    }


    @Test
    void Add_SeveralProduct_PriceReturned(){
        OrderLine orderLine = new OrderLine();
        int productAmount = 10;
        long expectedPrice = 100;
        Product product = new Product("Milk", expectedPrice, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);

        for (int i = 0; i < productAmount; i++)
            orderLine.addProduct(product);

        assertEquals(expectedPrice * productAmount, orderLine.getTotalPrice());
    }

    @Test
    void Add_TwoProductWithPriceLongMax_ExceptionThrown(){
        OrderLine orderLine = new OrderLine();
        long expectedPrice = Long.MAX_VALUE;
        Product product = new Product("Milk", expectedPrice, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);

        orderLine.addProduct(product);

        assertThrows(
                IllegalArgumentException.class,
                () -> orderLine.addProduct(product),
                "Tried to get total price for two products with Long.MAX price"
        );
    }


    @Test
    void ToString_OneProduct_CorrectFormatting(){
        OrderLine orderLine = new OrderLine();
        long price = 10;
        String name = "Milk";
        Product product = new Product(name, price, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);
        String expectedString = name;
        while(expectedString.length() < MAX_LENGTH_ORDERLINE - String.valueOf(price).length()){
            expectedString = expectedString + " ";
        }
        expectedString = expectedString + price;

        orderLine.addProduct(product);

        assertEquals(expectedString, orderLine.toString());
    }

    @Test
    void ToString_SeveralProduct_CorrectFormatting(){
        OrderLine orderLine = new OrderLine();
        int productAmount = 5;
        long price = 10;
        String name = "Milk";
        Product product = new Product(name, price, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);
        String expectedString = name + " " + productAmount + "st * " + price;

        while(expectedString.length() < MAX_LENGTH_ORDERLINE - String.valueOf(price * productAmount).length()){
            expectedString = expectedString + " ";
        }
        expectedString = expectedString + (price * productAmount);

        for (int i = 0; i < productAmount; i++)
            orderLine.addProduct(product);

        assertEquals(expectedString, orderLine.toString());
    }

    @Test
    void ToString_OneProductWithLongName_ShortenedToString(){
        OrderLine orderLine = new OrderLine();
        long price = 10;
        String name = "012345678901234567890123456789123456789";
        String expectedName = "01234567890123456...";
        Product product = new Product(name, price, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);
        String expectedString = expectedName;
        while(expectedString.length() < MAX_LENGTH_ORDERLINE - String.valueOf(price).length()){
            expectedString = expectedString + " ";
        }
        expectedString = expectedString + price;

        orderLine.addProduct(product);

        assertEquals(expectedString, orderLine.toString());
    }

    @Test
    void ToString_MultipleProductsWithLongNameAndHighPrice_ShortenedToString(){
        OrderLine orderLine = new OrderLine();
        int productAmount = 30;
        long price = 1000000000;
        String name = "012345678901234567890123456789123456789";
        String expectedName = "01234567890123456...";
        Product product = new Product(name, price, Producer.Arla, ProductGroup.Beverage, ProductGroup.Dairy);
        String expectedString = expectedName + " " + productAmount + "st * " + price;
        while(expectedString.length() < MAX_LENGTH_ORDERLINE - String.valueOf(price * productAmount).length()){
            expectedString = expectedString + " ";
        }
        expectedString = expectedString + (price * productAmount);

        for (int i = 0; i < productAmount; i++)
            orderLine.addProduct(product);

        assertEquals(expectedString, orderLine.toString());
    }

}
