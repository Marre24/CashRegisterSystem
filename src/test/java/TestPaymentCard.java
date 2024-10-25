import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaymentCard {

    @Test
    void ToString_OnPaymentCard_LastNumbersInPANShowing(){
        PaymentCard card = new CreditCard(null, "", "1111 1111 1111 3333", "", 132, 0);


        assertEquals("**** **** **** 3333", card.toString());
    }

}
