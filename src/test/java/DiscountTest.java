import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscountTest {

    private Discount discount = new Discount();
    private ProductType testProduct1 = new ProductType("Product", 2, ProductGroup.Beverage);
    private ProductType testProduct2 = new ProductType("Product1", 3, ProductGroup.Beverage);
    private ProductType testProduct3 = new ProductType("Product2", 4, ProductGroup.Beverage);
    private LocalDate testDateValid = LocalDate.now().plusDays(1);
    private LocalDate testDateInvalid = LocalDate.now().minusDays(1);

    @Test
    public void testTemporaryDiscount1() {
        discount.addDiscount(testProduct1, 1, testDateValid);
        assertEquals(1, discount.getDiscountedPrice(testProduct1));
    }

    @Test
    public void testTemporaryDiscount2() {
        assertThrows(IllegalArgumentException.class , () -> discount.addDiscount(testProduct1, 2, testDateValid), "Adding discount at the same price as original should throw Exception");
    }

    @Test
    public void testTemporaryDiscount3() {
        discount.addDiscount(testProduct1, 1, testDateValid);
        assertThrows(IllegalStateException.class, () -> discount.addDiscount(testProduct1, 1, testDateValid), "Adding discount to already discounted product should throw Exception");
    }

    @Test
    public void testTemporaryDiscount4() {
        assertThrows(IllegalArgumentException.class, () -> discount.addDiscount(testProduct1, 1, testDateInvalid), "Adding discount with an invalid date should throw Exception");
    }

    @Test
    public void testQuantityDiscount1() {
        discount.addDiscount(testProduct1, 3,2);
        assertEquals(3, discount.get);
    }
}