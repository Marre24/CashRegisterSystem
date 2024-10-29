import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockTestClass {

    @Mock
    private Product product;

    @InjectMocks
    private Order order;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTotalPrice(){
        Long hej = (long) Math.random();
        when(product.getPrice()).thenReturn(hej);
        order.addProduct(product);
        Long longen = order.getTotalPrice();
        assertEquals(hej, longen);
    }

}
