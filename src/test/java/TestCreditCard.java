import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreditCard {

    private static final int LIMIT = 100;
    private static final int LOW_LIMIT = 10;

    private static final String BANK_NAME = "Nordea";
    private static final String PAN = "1111 1111 1111 1111";
    private static final String EXPIRATION_DATE = "05/2026";
    private static final int CSC = 542;

    private final Product banana = new Product("Banana", 50, Producer.Arla);

    @Test
    void GetBalance_OnCreate_ReturnsZero(){
        CreditCard card = new CreditCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, LIMIT);

        assertEquals(0, card.getBalance());
    }

    @Test
    void GetBalance_AfterTransactedPurchase_PriceHasBeenAddedToBalance(){
        CreditCard card = new CreditCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, LIMIT);
        Order order = new Order(null);

        long price = order.getTotalPrice();
        card.pay(price);

        assertEquals(price, card.getBalance());
    }

    @Test
    void Pay_PriceExceedsLimit_ExceptionThrown(){
        CreditCard card = new CreditCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, LOW_LIMIT);
        Order order = new Order(null);

        order.addProduct(banana);

        assertThrows(
                IllegalArgumentException.class,
                () -> card.pay(order.getTotalPrice()),
                "Price is exceeds limit"
        );
    }

    @Test
    void Pay_PriceLessThanLimit_PriceReduced(){
        CreditCard card = new CreditCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, LIMIT);
        Order order = new Order(null);

        order.addProduct(banana);
        card.pay(order.getTotalPrice());

        assertEquals(order.getTotalPrice(), card.getBalance());
    }
}
