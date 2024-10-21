import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductType {

    @Test
    public void CreateClassInstance_ProductName_ClassInstance() {
        String expectedProductName = "Milk";
        long expectedPrice = 0;
        ProductType productType = new ProductType(expectedProductName, expectedPrice, ProductGroup.Dairy);
        assertEquals(expectedProductName, productType.getName());
    }

    @Test
    public void CreateClassInstance_PriceTag_ClassInstance() {
        long expectedPriceTag = 10;
        ProductType productType = new ProductType("", expectedPriceTag, ProductGroup.Dairy);
        assertEquals(expectedPriceTag, productType.getPrice());
    }

    @Test
    public void ToString_NameAndPrice_StringRepresentation() {
        String productName = "Juice";
        long priceTag = 10;
        String expectedStringRepresentation = "Product: " + productName + ", Price tag: " + priceTag;
        ProductType productType = new ProductType(productName, priceTag, ProductGroup.Dairy);
        assertEquals(expectedStringRepresentation, productType.toString());
    }

}
