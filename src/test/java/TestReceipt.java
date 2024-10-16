import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    private final Product testProduct = new Product("Toothbrush", 12L, ProductGroup.Beverage);

    @Test
    void barcodeIsNumeric(){
        var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String generatedBarcode = receipt.getBarcode();

        assertTrue(generatedBarcode.matches("[0-9]{13}"));
    }

    @Test
    void barcodeLengthIs13(){
        var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String receiptBarcode = receipt.getBarcode();
        int actual = receiptBarcode.length();
        String stringOfLength13 = "1234567890123";
        int expected = stringOfLength13.length();

        assertEquals(expected,actual);
    }

    @Test
    void productAddedToReceiptProducts() {
        Product expected = testProduct;
        List<Product> products = new ArrayList<>();
        products.add(expected);
        Receipt receipt = new Receipt(products);
        Product actual = receipt.getProducts().getFirst();

        assertEquals(actual, expected);
    }

    /*@Test
    void constructorDoesNotGenerateExistingID() {
        var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String barcode = receipt.getID();

    }*/
}
