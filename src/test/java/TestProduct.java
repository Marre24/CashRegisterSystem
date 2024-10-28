import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestProduct {

    @Test
    public void CreateClassInstance_ProductName_ClassInstance() {
        String expectedProductName = "Milk";
        long expectedPrice = 0;
        Product product = new Product(expectedProductName, expectedPrice, Producer.Arla, ProductGroup.Dairy);
        assertEquals(expectedProductName, product.getName());
    }

    @Test
    public void CreateClassInstance_PriceTag_ClassInstance() {
        long expectedPriceTag = 10;
        Product product = new Product("", expectedPriceTag, Producer.Arla, ProductGroup.Dairy);
        assertEquals(expectedPriceTag, product.getPrice());
    }

    @Test
    public void ToString_NameAndPrice_StringRepresentation() {
        String productName = "Juice";
        long priceTag = 10;
        String expectedStringRepresentation = "Product: " + productName + ", Price tag: " + priceTag;
        Product product = new Product(productName, priceTag, Producer.Arla, ProductGroup.Dairy);
        assertEquals(expectedStringRepresentation, product.toString());
    }

}
