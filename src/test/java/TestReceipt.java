import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    private final Money money = new Money(Currency.SEK, new BigDecimal(10));

    @Test
    void receiptContainsCorrectValue(){
        ProductScanner ps = new ProductScanner();
        Product expected = new Product("productName", money);

        ps.scanProduct(expected);
        Receipt receipt = ps.createReceipt();

        assertEquals(receipt.getItems().getFirst(), expected);
    }

}
