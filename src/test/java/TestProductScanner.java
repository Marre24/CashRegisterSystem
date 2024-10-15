import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProductScanner {


    private final Money money = new Money(Currency.SEK, new BigDecimal(10));
    // actual, expected
    // test method for scanning product. Assumes Receipt class, and its constructor working
    @Test
    void productScannerIsUpdatedOnProductScanned(){
        ProductScanner productScanner = new ProductScanner();
        Product expectedProductScanned = new Product("someProduct", money);
        productScanner.scanProduct(expectedProductScanned);

        var actual = productScanner.getScannedItems().getFirst();

        assertEquals(actual, expectedProductScanned);
    }
}

// ProductScanner should hold the scanned data in its own data structure, and when a customer is done, ps.writeReceipt() creates
// a new Receipt with the held data, and ps clears its data.
