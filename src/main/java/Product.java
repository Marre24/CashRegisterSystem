import java.util.Arrays;
import java.util.List;

public class Product {
    private final String name;
    private final long price;
    private final List<ProductGroup> productGroups;

    public Product (String name, long price, ProductGroup... productGroups){
        this.name = name;
        this.price = price;
        this.productGroups = Arrays.asList(productGroups);
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public List<ProductGroup> getProductGroups(){ return productGroups;}

    @Override
    public String toString() {
        return "Product: " +
                 name +
                ", Price tag: " + price;
    }
}
