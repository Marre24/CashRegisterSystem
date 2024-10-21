import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    private final ProductType testProductType = new ProductType("Toothbrush", 12L, ProductGroup.Beverage);

    @Test
    void barcodeIsNumeric(){
        var products = new ArrayList<ProductType>();
        Receipt receipt = new Receipt(products);
        String generatedBarcode = receipt.getBarcode();

        assertTrue(generatedBarcode.matches("[0-9]{13}"));
    }

    @Test
    void barcodeLengthIs13(){
        var products = new ArrayList<ProductType>();
        Receipt receipt = new Receipt(products);
        String receiptBarcode = receipt.getBarcode();
        int actual = receiptBarcode.length();
        String stringOfLength13 = "1234567890123";
        int expected = stringOfLength13.length();

        assertEquals(expected,actual);
    }

    @Test
    void productAddedToReceiptProducts() {
        ProductType expected = testProductType;
        List<ProductType> productTypes = new ArrayList<>();
        productTypes.add(expected);
        Receipt receipt = new Receipt(productTypes);
        ProductType actual = receipt.getProducts().getFirst();

        assertEquals(actual, expected);
    }
}
