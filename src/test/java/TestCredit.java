import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCredit {


    private Employee employee;
    private Person cardOwner;
    private Order order;

    @BeforeEach
    void setUp() {
        cardOwner = new Person("Alex", "Boe", "19950211-1325", "12345", "a@g.com", "NotaStrt");
        employee = new Employee("John", "Doe", "20000511-1323", "12345", "e@g.com", "AlsNtsTrt");
        order = new Order(employee);
    }

    // Max credit is set to 2525. Order has price 2500
    @Test
    void Credit_canMakePaymentIfPricePlusDebtLessThanMaxCredit_ReturnsTrue(){
        Product banan = new Product("Banan", 25);
        for(int i = 0; i < 100; i++){
            order.addProduct(banan);
        }
        Credit card = new Credit(cardOwner, "Nordea", "AKS", "2026", 5, 2525);
        assertTrue(card.canMakePurchase(order.getTotalPrice()));

    }

    // Max credit is set to 2475. Order has price 2500
    @Test
    void Credit_canNotMakePaymentIfPricePlusDebtGreaterThanMaxCredit_ReturnsFalse(){
        Product banan = new Product("Banan", 25);
        for(int i = 0; i < 100; i++){
            order.addProduct(banan);
        }
        Credit card = new Credit(cardOwner, "Nordea", "AKS", "2026", 5, 2475);
        assertFalse(card.canMakePurchase(order.getTotalPrice()));

    }

    // debt always starts out at 0. Max credit 5000, price (debt) is 2500, and we assert 2500 equals card.getDebt() after addDebt(debt)
    @Test
    void debtIsAddedOnPayment(){
        Credit card = new Credit(cardOwner, "Nordea", "AKS", "2026", 5, 5000);
        Product banan = new Product("Banan", 25);
        for(int i = 0; i < 100; i++){
            order.addProduct(banan);
        }

        long debt = order.getTotalPrice();
        card.addDebt(debt);
        assertEquals(debt, card.getDebt());
    }

    // If attempting to add debt results in current debt being greater than max credit, assert IllegalStateException thrown
    @Test
    void debtCanNotExceedMaxCredit(){
        Credit card = new Credit(cardOwner, "Nordea", "AKS", "2026", 5, 2500);
        Product banan = new Product("Banan", 25);
        for(int i = 0; i < 101; i++){
            order.addProduct(banan);
        }
        assertThrows(IllegalStateException.class, () -> {
            card.addDebt(order.getTotalPrice());
        });
    }
}
