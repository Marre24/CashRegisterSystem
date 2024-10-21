import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Discount {

    private Map<ProductType, Map.Entry<Long, LocalDate>> temporaryDiscounts = new HashMap<>();

    public void addDiscount(ProductType product, long newPrice, LocalDate expirationDate) {
        if (newPrice >= product.getPrice())
            throw new IllegalArgumentException("Price must be greater than or equal to the products original price");
        if (temporaryDiscounts.containsKey(product))
            throw new IllegalStateException("Product already have an active discount");
        if (expirationDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Date can not be in the past");
        temporaryDiscounts.put(product, new AbstractMap.SimpleEntry<>(newPrice, expirationDate));
    }

    public long getDiscountedPrice(ProductType product){
        return temporaryDiscounts.get(product).getKey();
    }
}
