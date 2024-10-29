import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

public class MockTestClass {

    @Mock
    private Product product;

    @InjectMocks
    private Order order = new Order(mock(Employee.class));

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        order.addProduct(product);
    }

    @Test
    void testGetTotalPrice(){
        when(product.getPrice()).thenReturn(1l);
        assertEquals(1,order.getTotalPrice());
    }
}
