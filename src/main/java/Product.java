public class Product {
    private final String name;
    private final float priceTag;

    public Product (String name, float priceTag){
        this.name = name;
        this.priceTag = priceTag;
    }

    public String getName() {
        return name;
    }

    public float getPriceTag() {
        return priceTag;
    }

    @Override
    public String toString() {
        return "Product: " +
                 name +
                ", Price tag: " + priceTag;
    }
}
