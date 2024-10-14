import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestProduct {

    @Test
    public void CreateClassInstance_ProductName_ClassInstance() {
        String expectedProductName = "Milk";
        Product product = new Product(expectedProductName, 0);
        assertEquals(expectedProductName, product.getName());
    }

    @Test
    public void CreateClassInstance_PriceTag_ClassInstance() {
        float expectedPriceTag = 10;
        Product product = new Product("",expectedPriceTag);
        assertEquals(expectedPriceTag, product.getPrice());
    }



}
