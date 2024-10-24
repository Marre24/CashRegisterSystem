import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDebit {


    // Initial balance 2500, order price 2525
    @Test
    void Debit_deductionResultingInNegativeBalance_ThrowsException(){
        Employee employee = new Employee("John", "Doe", "20000511-1323", "12345", "e@g.com", "AlsNtsTrt");
        Person cardOwner = new Person("Alex", "Boe", "19950211-1325", "12345", "a@g.com", "NotaStrt");
        Debit card = new Debit(cardOwner, "Nordea", "AKS", "2026", 5, 2500);
        Order order = new Order(employee);
        Product banan = new Product("Banan", 25);
        for(int i = 0; i < 101; i++){
            order.addProduct(banan);
        }
        long price = order.getTotalPrice();
        assertThrows(IllegalStateException.class, () -> {
            card.deductFromBalance(price);
        });
    }
}
