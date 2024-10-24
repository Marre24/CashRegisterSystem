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
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(productType, DISCOUNTED_PRICE, validDate);

        assertTrue(discount.hasDiscount(productType));
    }

    @Test
    public void AddDiscount_ProductHasDiscount_ExceptionThrown() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(productType, DISCOUNTED_PRICE, validDate);

        assertThrows(IllegalArgumentException.class,
                () -> discount.addDiscount(productType, DISCOUNTED_PRICE, validDate),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void AddDiscount_WithOriginalProductPrice_ExceptionThrown() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertThrows(IllegalArgumentException.class ,
                () -> discount.addDiscount(productType, PRICE, validDate),
                "Adding discount at the same price as original should throw Exception");
    }

    @Test
    public void AddDiscount_InvalidDate_ExceptionThrown() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertThrows(IllegalArgumentException.class,
                () -> discount.addDiscount(productType, 1, testDateInvalid),
                "Adding discount with an invalid date should throw Exception");
    }

    @Test
    public void AddDiscount_OrderLineWithoutDiscount_DiscountAdded() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(productType, QUOTA, DISCOUNTED_PRICE);

        assertTrue(discount.hasDiscount(productType));
    }

    @Test
    public void AddDiscount_OrderLineHasDiscount_ExceptionThrown() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(productType, QUOTA, PRICE);

        assertThrows(IllegalArgumentException.class,
                () -> discount.addDiscount(productType, QUOTA, PRICE),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void GetDiscountedPrice_OrderLineNotFillingQuota_ReturnsOriginalPrice() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        discount.addDiscount(productType, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(productType);

        assertEquals(PRICE, discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_OrderLineWithoutDiscount_ReturnsOriginalPrice(){
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        orderLine.addProduct(productType);

        assertEquals(PRICE, discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_NonDiscountedProduct_ReturnsOriginalPrice() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertEquals(PRICE, discount.getDiscountedPrice(productType));
    }

    @Test
    public void GetDiscountedPrice_DiscountedProduct_ReturnsPriceWithDiscount() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(productType, DISCOUNTED_PRICE, validDate);

        assertEquals(DISCOUNTED_PRICE, discount.getDiscountedPrice(productType));
    }

    @Test
    public void GetDiscountedPrice_OrderLineFillsQuota_DiscountedPrice() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        discount.addDiscount(productType, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(productType);
        orderLine.addProduct(productType);

        assertEquals(DISCOUNTED_PRICE, discount.getDiscountedPrice(orderLine));
    }

    @Test
    public void GetDiscountedPrice_OrderLineSurpassesQuota_OriginalPricePlusDiscountedPrice() {
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        discount.addDiscount(productType, QUOTA, DISCOUNTED_PRICE);
        orderLine.addProduct(productType);
        orderLine.addProduct(productType);
        orderLine.addProduct(productType);

        assertEquals(DISCOUNTED_PRICE + PRICE, discount.getDiscountedPrice(orderLine));
    }


    @Test
    public void RemoveDiscount_OrderLineHasDiscount_DiscountRemoved(){
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);
        OrderLine orderLine = new OrderLine();

        orderLine.addProduct(productType);
        discount.addDiscount(orderLine.getProductType(), QUOTA, DISCOUNTED_PRICE);
        discount.removeDiscount(productType);

        assertFalse(discount.hasDiscount(productType));
    }

    @Test
    public void RemoveDiscount_OrderLineWithoutDiscount_ExceptionThrown(){
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertThrows(IllegalArgumentException.class,
                () -> discount.removeDiscount(productType),
                "Discounting a product which already has a active discount should throw Exception");
    }

    @Test
    public void RemoveDiscount_ProductHasDiscount_DiscountRemoved(){
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        discount.addDiscount(productType, DISCOUNTED_PRICE, validDate);
        discount.removeDiscount(productType);

        assertFalse(discount.hasDiscount(productType));
    }

    @Test
    public void RemoveDiscount_ProductWithoutDiscount_ExceptionThrown(){
        ProductType productType = new ProductType(PRODUCT_NAME, PRICE, ProductGroup.Beverage);

        assertThrows(IllegalArgumentException.class,
                () -> discount.removeDiscount(productType),
                "Discounting a product which already has a active discount should throw Exception");
    }

}