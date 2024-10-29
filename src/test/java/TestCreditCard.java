import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreditCard {

    private static final int LIMIT = 100;
    private static final int LIMIT_MID= 50;
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
    void Pay_PricePlusBalanceExceedsLimit_ExceptionThrown(){
        CreditCard card = new CreditCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, LOW_LIMIT);
        Order order = new Order(null);

        order.addProduct(banana);

        assertThrows(
                IllegalArgumentException.class,
                () -> card.pay(order.getTotalPrice()),
                "Price plus balance exceeds limit"
        );
    }

    @Test
    void Pay_PricePlusBalanceLessThanLimit_BalanceIncreased(){
        CreditCard card = new CreditCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, LIMIT);
        Order order = new Order(null);

        order.addProduct(banana);
        card.pay(order.getTotalPrice());

        assertEquals(order.getTotalPrice(), card.getBalance());
    }

    @Test
    void Pay_PricePlusBalanceEqualToLimit_BalanceIncreased(){
        CreditCard card = new CreditCard(null, BANK_NAME, PAN, EXPIRATION_DATE, CSC, LIMIT_MID);
        Order order = new Order(null);

        order.addProduct(banana);
        card.pay(order.getTotalPrice());

        assertEquals(order.getTotalPrice(), card.getBalance());
    }
}
