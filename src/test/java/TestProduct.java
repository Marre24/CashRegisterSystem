import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestProduct {

    @Test
    public void CreateClassInstance_ProductName_ClassInstance() {
        String expectedProductName = "Milk";
        Product product = new Product(expectedProductName, new Money(Currency.SEK, new BigDecimal(0)));
        assertEquals(expectedProductName, product.getName());
    }

    @Test
    public void CreateClassInstance_PriceTag_ClassInstance() {
        Money expectedPriceTag = new Money(Currency.SEK, new BigDecimal(10));
        Product product = new Product("", expectedPriceTag);
        assertEquals(expectedPriceTag, product.getPrice());
    }

    @Test
    public void ToString_NameAndPrice_StringRepresentation() {
        String productName = "Juice";
        Money priceTag = new Money(Currency.SEK, new BigDecimal(10));
        String expectedStringRepresentation = "Product: " + productName + ", Price tag: " + priceTag;
        Product product = new Product(productName, priceTag);
        assertEquals(expectedStringRepresentation, product.toString());
    }

}
