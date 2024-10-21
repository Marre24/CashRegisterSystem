import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPurchase {


    // we are testing a method which returns true if the balance of a card is greater than the purchase total amount
    // if the balance is lesser than the purchase total amount, it must return true
    @Test
    void Purchase_BalanceIsGreaterThanPurchaseAmt_ReturnsTrue(){
        long cardBalance = 500L; // will need to be refactored to retrieve this data from DebitCard
        long orderPrice = 450L; // will need to be refactored to retrieve this data from Order
        Purchase purchase = new Purchase();
        boolean covers = purchase.cardBalanceCoversPurchase(cardBalance, orderPrice);
        assertTrue(covers);
    }

    @Test
    void Purchase_BalanceIsLessThanPurchaseAmt_ReturnsFalse(){
        long cardBalance = 450L; // will need to be refactored to retrieve this data from DebitCard
        long orderPrice = 500L; // will need to be refactored to retrieve this data from Order
        Purchase purchase = new Purchase();
        boolean covers = purchase.cardBalanceCoversPurchase(cardBalance, orderPrice);
        assertFalse(covers);
    }


}
