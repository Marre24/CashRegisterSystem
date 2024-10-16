import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    private final Product testProduct = new Product("Toothbrush", 12L, ProductGroup.Beverage);

    @Test
    void barcodeContainsOnlyNumbers(){
        var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String generatedBarcode = receipt.getID();
        assertTrue(generatedBarcode.matches("[0-9]{13}"));

    }

    @Test
    void barcodeLengthIsThirteen(){
        var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String actual = receipt.getID();
        String stringOfLength13 = "1234567890123";
        assertEquals(stringOfLength13.length(),actual.length());
    }

    @Test
    void productAddedToReceiptProducts() {
        Product expected = testProduct;
        List<Product> products = new ArrayList<>();
        products.add(expected);
        Receipt receipt = new Receipt(products);

        assertEquals(receipt.getProducts().getFirst(), expected);
    }

    /*@Test
    void constructorDoesNotGenerateExistingID() {
        var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String actual = receipt.getID();
    }*/
}
