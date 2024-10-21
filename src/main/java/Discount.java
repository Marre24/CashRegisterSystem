import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Discount {

    private Map<ProductType, Map.Entry<Long, LocalDate>> temporaryDiscounts = new HashMap<>();
    private Map<ProductType, Map.Entry<Integer, Integer>> quantityDiscounts = new HashMap<>();

    public void addDiscount(ProductType product, long newPrice, LocalDate expirationDate) {
        if (newPrice >= product.getPrice())
            throw new IllegalArgumentException("Price must be greater than or equal to the products original price");
        if (temporaryDiscounts.containsKey(product))
            throw new IllegalStateException("Product already have an active discount");
        if (expirationDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Date can not be in the past");
        temporaryDiscounts.put(product, new AbstractMap.SimpleEntry<>(newPrice, expirationDate));
    }

    public void addDiscount(ProductType product, int buyThisAmount, int price){
        quantityDiscounts.put(product, new AbstractMap.SimpleEntry<>(buyThisAmount, price));
    }

    public long getDiscountedPrice(ProductType product){
        if (!temporaryDiscounts.containsKey(product))
            return product.getPrice();
        return temporaryDiscounts.get(product).getKey();
    }

    public long getDiscountedPrice(OrderLine orderLine){
        if (!quantityDiscounts.containsKey(orderLine.getProductType()))
            return orderLine.getProductType().getPrice() * orderLine.getAmountOfProduct();
        long productPrice = orderLine.getProductType().getPrice();
        int amountToGetDiscount = quantityDiscounts.get(orderLine.getProductType()).getKey();
        int amountOnOrderLine = orderLine.getAmountOfProduct();
        int priceForQuantity = quantityDiscounts.get(orderLine.getProductType()).getValue();
        return (amountOnOrderLine / amountToGetDiscount * priceForQuantity) + (amountOnOrderLine % amountToGetDiscount * productPrice);
    }
}
