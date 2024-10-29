import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestDiscount {

    private static final int QUOTA = 2;
    static final long PRICE = 20;
    static final long DISCOUNTED_PRICE = 10;
    static final String PRODUCT_NAME = "ProductName";
    private final LocalDate validDate = LocalDate.now().plusDays(1);
    private final LocalDate testDateInvalid = LocalDate.now().minusDays(1);

    private final Product product = new Product(PRODUCT_NAME, PRICE, Producer.Arla, ProductGroup.Beverage);

    @Test
    public void AddDiscount_ProductWithoutDiscount_DiscountAdded() {
        Discount.addDiscount(product, DISCOUNTED_PRICE, validDate);

        assertTrue(Discount.hasDiscount(product));
    }

    @Test
    public void AddDiscount_ProductHasDiscount_ExceptionThrown() {

        Discount.addDiscount(product, DISCOUNTED_PRICE, validDate);

        assertThrows(IllegalArgumentException.class,
                () -> Discount.addDiscount(product, DISCOUNTED_PRICE, validDate),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void AddDiscount_WithOriginalProductPrice_ExceptionThrown() {

        assertThrows(IllegalArgumentException.class ,
                () -> Discount.addDiscount(product, PRICE, validDate),
                "Adding discount at the same price as original should throw Exception");
    }

    @Test
    public void AddDiscount_InvalidDate_ExceptionThrown() {

        assertThrows(IllegalArgumentException.class,
                () -> Discount.addDiscount(product, 1, testDateInvalid),
                "Adding discount with an invalid date should throw Exception");
    }

    @Test
    public void AddDiscount_OrderLineWithoutDiscount_DiscountAdded() {

        Discount.addDiscount(product, QUOTA, DISCOUNTED_PRICE);

        assertTrue(Discount.hasDiscount(product));
    }

    @Test
    public void AddDiscount_OrderLineHasDiscount_ExceptionThrown() {

        Discount.addDiscount(product, QUOTA, PRICE);

        assertThrows(IllegalArgumentException.class,
                () -> Discount.addDiscount(product, QUOTA, PRICE),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void GetDiscountedPrice_OrderLineNotFillingQuota_ReturnsOriginalPrice() {
        OrderLine orderLine = new OrderLine();

        Discount.addDiscount(product, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(product);

        assertEquals(PRICE, Discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_OrderLineWithoutDiscount_ReturnsOriginalPrice(){
        OrderLine orderLine = new OrderLine();

        orderLine.addProduct(product);

        assertEquals(PRICE, Discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_NonDiscountedProduct_ReturnsOriginalPrice() {

        assertEquals(PRICE, Discount.getDiscountedPrice(product));
    }

    @Test
    public void GetDiscountedPrice_DiscountedProduct_ReturnsPriceWithDiscount() {

        Discount.addDiscount(product, DISCOUNTED_PRICE, validDate);

        assertEquals(DISCOUNTED_PRICE, Discount.getDiscountedPrice(product));
    }

    @Test
    public void GetDiscountedPrice_OrderLineFillsQuota_DiscountedPrice() {
        OrderLine orderLine = new OrderLine();

        Discount.addDiscount(product, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(product);
        orderLine.addProduct(product);

        assertEquals(DISCOUNTED_PRICE, Discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_OrderLineSurpassesQuota_OriginalPricePlusDiscountedPrice() {
        OrderLine orderLine = new OrderLine();

        Discount.addDiscount(product, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(product);
        orderLine.addProduct(product);
        orderLine.addProduct(product);

        assertEquals(DISCOUNTED_PRICE + PRICE, Discount.getDiscountedPrice(orderLine));
    }


    @Test
    public void RemoveDiscount_OrderLineHasDiscount_DiscountRemoved(){
        OrderLine orderLine = new OrderLine();

        orderLine.addProduct(product);
        Discount.addDiscount(orderLine.getProductType(), QUOTA, DISCOUNTED_PRICE);
        Discount.removeDiscount(product);

        assertFalse(Discount.hasDiscount(product));
    }

    @Test
    public void RemoveDiscount_OrderLineWithoutDiscount_ExceptionThrown(){

        assertThrows(IllegalArgumentException.class,
                () -> Discount.removeDiscount(product),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void RemoveDiscount_ProductHasDiscount_DiscountRemoved(){
        Discount.addDiscount(product, DISCOUNTED_PRICE, validDate);
        Discount.removeDiscount(product);

        assertFalse(Discount.hasDiscount(product));
    }

    @Test
    public void RemoveDiscount_ProductWithoutDiscount_ExceptionThrown(){
        assertThrows(IllegalArgumentException.class,
                () -> Discount.removeDiscount(product),
                "Discounting a product which already has a active discount should throw Exception");
    }

}