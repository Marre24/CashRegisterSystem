import java.math.BigDecimal;

public class Product {
    private final String name;
    private final long price;
    private final ProductGroup productGroup;

    public Product (String name, long price, ProductGroup productGroup ){
        this.name = name;
        this.price = price;
        this.productGroup = productGroup;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public ProductGroup getProductGroup(){ return productGroup;}

    @Override
    public String toString() {
        return "Product: " +
                 name +
                ", Price tag: " + price;
    }
}
