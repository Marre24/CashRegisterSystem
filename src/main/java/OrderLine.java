public class OrderLine {

    private int MAX_LENGTH_NAME = 20;
    private int MAX_LENGTH_ORDERLINE = 50;

    public OrderLine(){
    }

    public OrderLine(Product product){
        addProduct(product);
    }

    private Product product = null;
    private int amountOfProduct = 0;

    public void addProduct(Product newProduct) {
        if (newProduct == null)
            throw new IllegalArgumentException("Tried to add null to a orderLine");

        if (this.product == null){
            this.product = newProduct;
            amountOfProduct++;
            return;
        }

        if (this.product != newProduct)
            throw new IllegalArgumentException("Tried to add a productType to a orderLine with another productType");


        if (Long.MAX_VALUE - getTotalPrice() < product.getPrice())
            throw new IllegalArgumentException();

        amountOfProduct++;
    }

    public Product getProductType() {
        return product;
    }

    public int getAmountOfProduct() {
        return amountOfProduct;
    }

    public long getTotalPrice() {
        if (product.getProductGroups().contains(ProductGroup.pricedByWeight))
        return product.getPrice() * amountOfProduct;
    }

    @Override
    public String toString() {
        String name = getProductType().getName();
        if (name.length() > MAX_LENGTH_NAME){
            name = name.substring(0, MAX_LENGTH_NAME-3) + "...";
        }
        if (amountOfProduct > 1) {
            String multipleProductsPrice = amountOfProduct + "st * " + product.getPrice();
            name = name + " " + multipleProductsPrice;
        }
        while (name.length() < MAX_LENGTH_ORDERLINE - String.valueOf(getTotalPrice()).length()){
            name = name + " ";
        }
        name = name + getTotalPrice();
        return name;
    }
}
