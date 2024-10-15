import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestProductScanner {


    // actual, expected
    /* Since we changed so the product scanner does not actually store the products itself, this test became redundant
    @Test
    void productScannerIsUpdatedOnProductScanned(){
        ProductScanner productScanner = new ProductScanner();
        Product expectedProductScanned = new Product("someProduct", 1, ProductGroup.AlcoholicBeverages);
        productScanner.scanProduct(expectedProductScanned);

        Product actual = productScanner.getOrder().getProducts().getFirst();

        assertEquals(actual, expectedProductScanned);
    } */

    /* ProductScanner is not responsible for creating a receipt anymore
    @Test
    void receiptIsCreated(){
        ProductScanner ps = new ProductScanner();
        Receipt receipt = ps.createReceipt();
        assertNotEquals(receipt, null);
    }*/


}
