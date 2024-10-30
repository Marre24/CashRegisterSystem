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

    @Test
    void Equals_ProductsWithSameNameAndProducer_ReturnsTrue() {
        Product p1 = new Product("ProductName", 0, Producer.Arla, ProductGroup.Beverage);
        Product p2 = new Product("ProductName",1, Producer.Arla, ProductGroup.Dairy);
        assertTrue(p1.equals(p2));
    }
    @Test
    void Equals_ProductComparedWithNonProduct_NotEquals() {
        Product p1 = new Product("ProductName", 0, Producer.Arla, ProductGroup.Beverage);
        String p2 = "hi";
        assertNotEquals(p1, p2);
    }
    @Test
    void Equals_SameProducerDifferentName_NotEquals() {
        Product p1 = new Product("ProductName", 0, Producer.Arla, ProductGroup.Beverage);
        Product p2 = new Product("ProductName2",1, Producer.Arla, ProductGroup.Dairy);
        assertNotEquals(p1, p2);
    }
    @Test
    void Equals_SameNameDifferentProducer_NotEquals() {
        Product p1 = new Product("ProductName", 0, Producer.Arla, ProductGroup.Beverage);
        Product p2 = new Product("ProductName",1, Producer.Coop, ProductGroup.Dairy);
        assertNotEquals(p1, p2);
    }
}
