import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class TestPurchase {

    static final int LOW_BALANCE = 10;
    static final int HIGH_BALANCE = 100;
    @Mock
    Person mockedCardOwner;
    @Mock
    private Order mockedOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Employee employee = new Employee("John", "Doe", "20000511-1323", "12345", "e@g.com", "AlsNtsTrt");
    }

    @Test
    void HandlePayment_CostIsGreaterThanBalance_ReturnsFalse(){
        when(mockedCardOwner.isMember()).thenReturn(false);
        when(mockedOrder.getTotalPrice()).thenReturn(20L);

        DebitCard debitCard = new DebitCard(mockedCardOwner, "", "", "", 0, LOW_BALANCE);
        Purchase purchase = new Purchase(mockedOrder);

        assertFalse(purchase.handlePayment(debitCard));
        assertEquals(LOW_BALANCE, debitCard.getBalance());
    }


    @Test
    void HandlePayment_CustomerIsMember_BalanceIsDeductedWithDiscount(){
        when(mockedCardOwner.isMember()).thenReturn(true);
        when(mockedOrder.getMemberPrice()).thenReturn(10L);

        DebitCard debitCard = new DebitCard(mockedCardOwner, "", "", "", 0, HIGH_BALANCE);
        Purchase purchase = new Purchase(mockedOrder);
        purchase.handlePayment(debitCard);

        long memberPrice = mockedOrder.getMemberPrice();

        assertEquals(HIGH_BALANCE - memberPrice, debitCard.getBalance());
    }

    @Test
    void HandlePayment_CustomerIsNotMember_BalanceIsDeductedWithStandardPrice(){
        when(mockedCardOwner.isMember()).thenReturn(false);
        when(mockedOrder.getTotalPrice()).thenReturn(20L);

        DebitCard debitCard = new DebitCard(mockedCardOwner, "", "", "", 0, HIGH_BALANCE);
        Purchase purchase = new Purchase(mockedOrder);
        purchase.handlePayment(debitCard);

        long standardPrice = mockedOrder.getTotalPrice();

        assertEquals(HIGH_BALANCE - standardPrice, debitCard.getBalance());
    }

}
