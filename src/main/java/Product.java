import java.util.Arrays;
import java.util.List;

public class Product {
    private final String name;
    private final long price;
    private final Producer producer;
    private final List<ProductGroup> productGroups;

    public Product(String name, long price, Producer producer, ProductGroup... productGroups){
        this.name = name;
        this.price = price;
        this.producer = producer;
        this.productGroups = Arrays.asList(productGroups);
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public Producer getProducer(){
        return producer;
    }

    public List<ProductGroup> getProductGroups(){ return productGroups;}

    @Override
    public String toString() {
        return "Product: " +
                 name +
                ", Price tag: " + price;
    }

    public boolean isPricedByWeight(){
        return productGroups.contains(ProductGroup.PricedByWeight);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product))
            return false;
        return ((Product) obj).getName().equals(name) && ((Product)obj).getProducer() == producer;
    }
}
