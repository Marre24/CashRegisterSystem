import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;

public class TestPurchase {
    private Person cardOwner;
    private Employee employee;
    private Order order;

    @Mock
    Person person;
    @Mock
    private Order mockedOrder;

    @InjectMocks
    private Purchase purchase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        cardOwner = new Person("Alex", "Boe", "19950211-1325", "12345", "a@g.com", "NotaStrt");
        employee = new Employee("John", "Doe", "20000511-1323", "12345", "e@g.com", "AlsNtsTrt");
        order = new Order(employee);
    }

    // tests debit card with initial balance of 5000 and order total price of 4950
    @Test
    void Purchase_BalanceIsGreaterThanPurchaseAmt_ReturnsTrue(){
        DebitCard card = new DebitCard(cardOwner, "Nordea", "AKS", "2026", 5, 5000);
        Product banan = new Product("Banan", 50, Producer.Arla);
        for(int i = 0; i < 99; i++){ // order total price should become 4950
            order.addProduct(banan);
        }
        Purchase purchase = new Purchase(order);
        long cardBalance = card.getBalance();
        long orderTotalPrice = order.getTotalPrice();
        boolean covers = purchase.debitCardBalanceCoversPurchase(cardBalance, orderTotalPrice);
        assertTrue(covers);
    }

    // tests debit card with initial balance of 5000 and order total price of 5050
    @Test
    void Purchase_BalanceIsLessThanPurchaseAmt_ReturnsFalse(){
        DebitCard card = new DebitCard(cardOwner, "Nordea", "AKS", "2026", 5, 5000);
        Product banan = new Product("Banan", 50, Producer.Arla);
        for(int i = 0; i < 101; i++){ // order total price should become 5050
            order.addProduct(banan);
        }
        Purchase purchase = new Purchase(order);
        long cardBalance = card.getBalance();
        long orderTotalPrice = order.getTotalPrice();
        boolean covers = purchase.debitCardBalanceCoversPurchase(cardBalance, orderTotalPrice);
        assertFalse(covers);
    }

    @Test
    void Purchase_WithMemberPrice_BalanceIsDeductedWithDiscount(){
        long initialBalance = 100L;
        when(person.isMember()).thenReturn(true);
        when(mockedOrder.getMemberPrice()).thenReturn(10L);
        when(mockedOrder.getTotalPrice()).thenReturn(20L);

        DebitCard debitCard = new DebitCard(person, "", "", "", 0, initialBalance);
        Purchase purchase = new Purchase(order);
        purchase.handlePayment(debitCard);

        long cardBalance = debitCard.getBalance();
        long memberPrice = order.getMemberPrice();

        assertEquals(initialBalance - memberPrice, cardBalance);
    }

    @Test
    void Purchase_NotMember_BalanceIsDeductedWithStandardPrice(){
        long initialBalance = 100L;
        when(person.isMember()).thenReturn(false);
        when(mockedOrder.getMemberPrice()).thenReturn(10L);
        when(mockedOrder.getTotalPrice()).thenReturn(20L);

        DebitCard debitCard = new DebitCard(person, "", "", "", 0, initialBalance);
        Purchase purchase = new Purchase(order);
        purchase.handlePayment(debitCard);

        long cardBalance = debitCard.getBalance();
        long standardPrice = order.getTotalPrice();

        assertEquals(initialBalance - standardPrice, cardBalance);
    }

}
