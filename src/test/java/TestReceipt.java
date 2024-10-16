import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    Product product1 = new Product("Toothbrush", 12L, ProductGroup.None);

    @Test
    void receiptContainsCorrectValue() {
        Product expected = new Product("productName", 50L, ProductGroup.Beverages);
        List<Product> products = new ArrayList<>();
        products.add(expected);
        Receipt receipt = new Receipt(products);

        assertEquals(receipt.getItems().getFirst(), expected);
    }


    @Test
    void idContainsOnlyNumbers(){
        var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String actual = receipt.getID();

    }

    @Test
    void idLengthIsThirteen(){
        var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String actual = receipt.getID();
        String stringOfLength13 = "1234567890123";
        assertEquals(stringOfLength13.length(),actual.length());
    }

    @Test
    void constructorDoesNotGenerateExistingID() {
        /*var products = new ArrayList<Product>();
        Receipt receipt = new Receipt(products);
        String actual = receipt.getID();*/
    }
}
