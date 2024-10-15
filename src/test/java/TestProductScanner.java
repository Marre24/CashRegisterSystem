import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductScanner {


    private final Money money = new Money(Currency.SEK, new BigDecimal(10));
    // actual, expected
    // test method for scanning product. Assumes Receipt class, and its constructor working
    @Test
    void productScannerIsUpdatedOnProductScanned(){
        ProductScanner productScanner = new ProductScanner();
        Product expectedProductScanned = new Product("someProduct", 1);
        productScanner.scanProduct(expectedProductScanned);

        Product actual = productScanner.getScannedItems().getFirst();

        assertEquals(actual, expectedProductScanned);
    }

    @Test
    void receiptIsCreated(){
        ProductScanner ps = new ProductScanner();
        Receipt receipt = ps.createReceipt();
        assertNotEquals(receipt, null);
    }


}
