import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPurchase {


    // tests debit card with initial balance of 5000 and order total price of 4950
    @Test
    void Purchase_BalanceIsGreaterThanPurchaseAmt_ReturnsTrue(){
        Person cardOwner = new Person("Alex", "Boe", "19950211-1325", "12345", "a@g.com", "NotaStrt");
        Employee employee = new Employee("John", "Doe", "20000511-1323", "12345", "e@g.com", "AlsNtsTrt");
        Order order = new Order(employee);
        Debit card = new Debit(cardOwner, "Nordea", "AKS", "2026", 5, 5000);
        Product banan = new Product("Banan", 50);
        for(int i = 0; i < 99; i++){ // order total price should become 4950
            order.addProduct(banan);
        }
        Purchase purchase = new Purchase(order, card);

        long cardBalance = card.getBalance();
        long orderTotalPrice = order.getTotalPrice();
        boolean covers = purchase.debitCardBalanceCoversPurchase(cardBalance, orderTotalPrice);
        assertTrue(covers);
    }

    // tests debit card with initial balance of 5000 and order total price of 5050
    @Test
    void Purchase_BalanceIsLessThanPurchaseAmt_ReturnsFalse(){
        Person cardOwner = new Person("Alex", "Boe", "19950211-1325", "12345", "a@g.com", "NotaStrt");
        Employee employee = new Employee("John", "Doe", "20000511-1323", "12345", "e@g.com", "AlsNtsTrt");
        Order order = new Order(employee);
        Debit card = new Debit(cardOwner, "Nordea", "AKS", "2026", 5, 5000);

        Product banan = new Product("Banan", 50);
        for(int i = 0; i < 101; i++){ // order total price should become 5050
            order.addProduct(banan);
        }
        Purchase purchase = new Purchase(order, card);
        long cardBalance = card.getBalance();
        long orderTotalPrice = order.getTotalPrice();
        boolean covers = purchase.debitCardBalanceCoversPurchase(cardBalance, orderTotalPrice);
        assertFalse(covers);
    }

    // What side effects are we testing for when a debit card is handling a payment? Success vs failure?
    @Test
    void Purchase_paymentHandledForDebitCard_ReturnsTrue(){

        Person cardOwner = new Person("Alex", "Boe", "19950211-1325", "12345", "a@g.com", "NotaStrt");
        Employee employee = new Employee("John", "Doe", "20000511-1323", "12345", "e@g.com", "AlsNtsTrt");
        Order order = new Order(employee);
        Product banan = new Product("Banan", 25);
        for(int i = 0; i < 100; i++){
            order.addProduct(banan);
        }
        Debit card = new Debit(cardOwner, "Nordea", "AKS", "2026", 5, 5000);
        Purchase purchase = new Purchase(order, card);

        boolean paymentHandled = purchase.handlePayment();
        assertTrue(paymentHandled);
    }
}
