import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestDiscount {

    private static final int QUOTA = 2;
    private final Discount discount = new Discount();
    static final long PRICE = 20;
    static final long DISCOUNTED_PRICE = 10;
    static final String PRODUCT_NAME = "ProductName";
    private final LocalDate validDate = LocalDate.now().plusDays(1);
    private final LocalDate testDateInvalid = LocalDate.now().minusDays(1);

    @Test
    public void AddDiscount_ProductWithoutDiscount_DiscountAdded() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(product, DISCOUNTED_PRICE, validDate);

        assertTrue(discount.hasDiscount(product));
    }

    @Test
    public void AddDiscount_ProductHasDiscount_ExceptionThrown() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(product, DISCOUNTED_PRICE, validDate);

        assertThrows(IllegalArgumentException.class,
                () -> discount.addDiscount(product, DISCOUNTED_PRICE, validDate),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void AddDiscount_WithOriginalProductPrice_ExceptionThrown() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertThrows(IllegalArgumentException.class ,
                () -> discount.addDiscount(product, PRICE, validDate),
                "Adding discount at the same price as original should throw Exception");
    }

    @Test
    public void AddDiscount_InvalidDate_ExceptionThrown() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertThrows(IllegalArgumentException.class,
                () -> discount.addDiscount(product, 1, testDateInvalid),
                "Adding discount with an invalid date should throw Exception");
    }

    @Test
    public void AddDiscount_OrderLineWithoutDiscount_DiscountAdded() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(product, QUOTA, DISCOUNTED_PRICE);

        assertTrue(discount.hasDiscount(product));
    }

    @Test
    public void AddDiscount_OrderLineHasDiscount_ExceptionThrown() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(product, QUOTA, PRICE);

        assertThrows(IllegalArgumentException.class,
                () -> discount.addDiscount(product, QUOTA, PRICE),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void GetDiscountedPrice_OrderLineNotFillingQuota_ReturnsOriginalPrice() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        discount.addDiscount(product, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(product);

        assertEquals(PRICE, discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_OrderLineWithoutDiscount_ReturnsOriginalPrice(){
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        orderLine.addProduct(product);

        assertEquals(PRICE, discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_NonDiscountedProduct_ReturnsOriginalPrice() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertEquals(PRICE, discount.getDiscountedPrice(product));
    }

    @Test
    public void GetDiscountedPrice_DiscountedProduct_ReturnsPriceWithDiscount() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(product, DISCOUNTED_PRICE, validDate);

        assertEquals(DISCOUNTED_PRICE, discount.getDiscountedPrice(product));
    }

    @Test
    public void GetDiscountedPrice_OrderLineFillsQuota_DiscountedPrice() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        discount.addDiscount(product, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(product);
        orderLine.addProduct(product);

        assertEquals(DISCOUNTED_PRICE, discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_OrderLineSurpassesQuota_OriginalPricePlusDiscountedPrice() {
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        discount.addDiscount(product, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(product);
        orderLine.addProduct(product);
        orderLine.addProduct(product);

        assertEquals(DISCOUNTED_PRICE + PRICE, discount.getDiscountedPrice(orderLine));
    }


    @Test
    public void RemoveDiscount_OrderLineHasDiscount_DiscountRemoved(){
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        orderLine.addProduct(product);
        discount.addDiscount(orderLine.getProductType(), QUOTA, DISCOUNTED_PRICE);
        discount.removeDiscount(product);

        assertFalse(discount.hasDiscount(product));
    }

    @Test
    public void RemoveDiscount_OrderLineWithoutDiscount_ExceptionThrown(){
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertThrows(IllegalArgumentException.class,
                () -> discount.removeDiscount(product),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void RemoveDiscount_ProductHasDiscount_DiscountRemoved(){
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(product, DISCOUNTED_PRICE, validDate);
        discount.removeDiscount(product);

        assertFalse(discount.hasDiscount(product));
    }

    @Test
    public void RemoveDiscount_ProductWithoutDiscount_ExceptionThrown(){
        Product product = new Product(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertThrows(IllegalArgumentException.class,
                () -> discount.removeDiscount(product),
                "Discounting a product which already has a active discount should throw Exception");
    }

}