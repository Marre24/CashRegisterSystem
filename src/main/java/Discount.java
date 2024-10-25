import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public abstract class Discount {

    private static final Map<Product, Map.Entry<Long, LocalDate>> timeBasedDiscounts = new HashMap<>();
    private static final Map<Product, Map.Entry<Integer, Long>> quantityDiscounts = new HashMap<>();

    public static void addDiscount(Product product, long newPrice, LocalDate expirationDate) {
        if (hasDiscount(product))
            throw new IllegalArgumentException("Product already have a discount");
        if (newPrice >= product.getPrice())
            throw new IllegalArgumentException("Discounted price must be less than the products original price");
        if (expirationDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Date can not be in the past");
        timeBasedDiscounts.put(product, new AbstractMap.SimpleEntry<>(newPrice, expirationDate));
    }

    public static void addDiscount(Product product, int quota, long price){
        if (hasDiscount(product))
            throw new IllegalArgumentException("Product already have an active discount");
        quantityDiscounts.put(product, new AbstractMap.SimpleEntry<>(quota, price));
    }

    public static long getDiscountedPrice(Product product){
        if (!timeBasedDiscounts.containsKey(product))
            return product.getPrice();
        return timeBasedDiscounts.get(product).getKey();
    }

    public static long getDiscountedPrice(OrderLine orderLine){
        if (!quantityDiscounts.containsKey(orderLine.getProductType()))
            return orderLine.getProductType().getPrice() * orderLine.getAmountOfProduct();

        int amountOnOrderLine = orderLine.getAmountOfProduct();
        long productPrice = orderLine.getProductType().getPrice();
        long priceForQuantity = quantityDiscounts.get(orderLine.getProductType()).getValue();
        int quota = quantityDiscounts.get(orderLine.getProductType()).getKey();

        return (amountOnOrderLine / quota * priceForQuantity) + (amountOnOrderLine % quota * productPrice);
    }

    public  static boolean hasDiscount(Product product){
        return timeBasedDiscounts.containsKey(product) || quantityDiscounts.containsKey(product);
    }

    public static void removeDiscount(Product product){
        if (!timeBasedDiscounts.containsKey(product) && !quantityDiscounts.containsKey(product))
            throw new IllegalArgumentException();
        timeBasedDiscounts.remove(product);
        quantityDiscounts.remove(product);
    }
}
