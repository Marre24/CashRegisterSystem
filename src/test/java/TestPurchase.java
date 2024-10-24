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
        ProductType banan = new ProductType("Banan", 50);
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

        ProductType banan = new ProductType("Banan", 50);
        for(int i = 0; i < 101; i++){ // order total price should become 5050
            order.addProduct(banan);
        }
        Purchase purchase = new Purchase(order, card);
        long cardBalance = card.getBalance();
        long orderTotalPrice = order.getTotalPrice();
        boolean covers = purchase.debitCardBalanceCoversPurchase(cardBalance, orderTotalPrice);
        assertFalse(covers);
    }

    // test Purchase's handlePayment method in multiply different ways (credit, Debit). Testing those methods transitively
    /*
    @Test
    void Purchase_paymentHandledForCreditCard_ReturnsTrue(){

        Person cardOwner = new Person("Alex", "Boe", "19950211-1325", "12345", "a@g.com", "NotaStrt");
        Employee employee = new Employee("John", "Doe", "20000511-1323", "12345", "e@g.com", "AlsNtsTrt");
        Order order = new Order(employee);
        ProductType banan = new ProductType("Banan", 25);
        for(int i = 0; i < 100; i++){
            order.addProduct(banan);
        }
        Credit card = new Credit(cardOwner, "Nordea", "AKS", "2026", 5, 2525);
        Purchase purchase = new Purchase(order, card);
        purchase.handlePayment();
        assertTrue(card.canMakePurchase(order.getTotalPrice()));

    }*/
}
