import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private final Map<ProductType, Map.Entry<Long, LocalDate>> timeBasedDiscounts = new HashMap<>();
    private final Map<ProductType, Map.Entry<Integer, Long>> quantityDiscounts = new HashMap<>();

    public void addDiscount(ProductType product, long newPrice, LocalDate expirationDate) {
        if (hasDiscount(product))
            throw new IllegalArgumentException("Product already have an active discount");
        if (newPrice >= product.getPrice())
            throw new IllegalArgumentException("Price must be greater than or equal to the products original price");
        if (expirationDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Date can not be in the past");
        timeBasedDiscounts.put(product, new AbstractMap.SimpleEntry<>(newPrice, expirationDate));
    }

    public void addDiscount(ProductType product, int quota, long price){
        if (hasDiscount(product))
            throw new IllegalArgumentException("Product already have an active discount");
        quantityDiscounts.put(product, new AbstractMap.SimpleEntry<>(quota, price));
    }

    public long getDiscountedPrice(ProductType product){
        if (!timeBasedDiscounts.containsKey(product))
            return product.getPrice();
        return timeBasedDiscounts.get(product).getKey();
    }

    public long getDiscountedPrice(OrderLine orderLine){
        if (!quantityDiscounts.containsKey(orderLine.getProductType()))
            return orderLine.getProductType().getPrice() * orderLine.getAmountOfProduct();

        int amountOnOrderLine = orderLine.getAmountOfProduct();
        long productPrice = orderLine.getProductType().getPrice();
        long priceForQuantity = quantityDiscounts.get(orderLine.getProductType()).getValue();
        int amountToGetDiscount = quantityDiscounts.get(orderLine.getProductType()).getKey();

        return (amountOnOrderLine / amountToGetDiscount * priceForQuantity) + (amountOnOrderLine % amountToGetDiscount * productPrice);
    }

    public boolean hasDiscount(ProductType product){
        return timeBasedDiscounts.containsKey(product) || quantityDiscounts.containsKey(product);
    }

    public void removeDiscount(ProductType product){
        if (!timeBasedDiscounts.containsKey(product) && !quantityDiscounts.containsKey(product))
            throw new IllegalArgumentException();
        timeBasedDiscounts.remove(product);
        quantityDiscounts.remove(product);
    }
}
