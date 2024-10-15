import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    @Test
    void receiptContainsCorrectValue(){
<<<<<<< HEAD
        Product expected = new Product("productName", 50L);
        List<Product> products = new ArrayList<>();
        products.add(expected);
        Receipt receipt = new Receipt(products);
=======
        ProductScanner ps = new ProductScanner();
        Product expected = new Product("productName", 1);

        ps.scanProduct(expected);
        Receipt receipt = ps.createReceipt();
>>>>>>> 18fd0575122ac85e338555c5b08d891f80525569

        assertEquals(receipt.getItems().getFirst(), expected);
    }

}
