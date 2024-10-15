import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    @Test
    void receiptContainsCorrectValue(){
        Product expected = new Product("productName", 50L);
        List<Product> products = new ArrayList<>();
        products.add(expected);
        Receipt receipt = new Receipt(products);

        assertEquals(receipt.getItems().getFirst(), expected);
    }

}
