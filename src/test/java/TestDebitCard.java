import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDebitCard {

    private static final int BALANCE = 100;
    private static final int LOW_BALANCE = 10;

    private static final String BANK_NAME = "Nordea";
    private static final String PAN = "1111 1111 1111 1111";
    private static final String EXPIRATION_DATE = "05/2026";
    private static final int CSC = 542;

    private final Product banana = new Product("Banana", 50, Producer.Arla);

    @Test
    void Pay_PriceGreaterThanBalance_ThrowsException(){
        DebitCard card = new DebitCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, LOW_BALANCE);
        Order order = new Order(null);

        order.addProduct(banana);

        assertThrows(
                IllegalArgumentException.class,
                () -> card.pay(order.getTotalPrice()),
                "Price is exceeds limit"
        );
    }

    @Test
    void Pay_PriceLessThanBalance_PriceDecreases(){
        DebitCard card = new DebitCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, BALANCE);
        Order order = new Order(null);

        order.addProduct(banana);

        card.pay(order.getTotalPrice());

        assertEquals(BALANCE - banana.getPrice(), card.getBalance());
    }

}
