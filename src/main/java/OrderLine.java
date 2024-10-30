public class OrderLine {

    private static final int MAX_LENGTH_NAME = 20;
    private static final int MAX_LENGTH_ORDER_LINE = 50;

    public OrderLine(){
    }

    public OrderLine(Product product){
        addProduct(product);
    }

    public OrderLine(Product product, long weightOfProduct){
        addProduct(product, weightOfProduct);
    }

    private Product product = null;
    private int amountOfProduct = 0;
    private long weightOfProduct = 0;

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

    public void addProduct(Product newProduct, long weight){
        if (newProduct == null)
            throw new IllegalArgumentException("Tried to add null to a orderLine");

        if (this.product == null){
            this.product = newProduct;
            amountOfProduct++;
            weightOfProduct = weight;
            return;
        }

        if (this.product != newProduct)
            throw new IllegalArgumentException("Tried to add a productType to a orderLine with another productType");

        if (Long.MAX_VALUE - getTotalPrice() < product.getPrice())
            throw new IllegalArgumentException();

        weightOfProduct += weight;
    }

    public Product getProductType() {
        return product;
    }

    public int getAmountOfProduct() {
        return amountOfProduct;
    }

    public long getTotalPrice() {
        if (product.isPricedByWeight()){
            long retPrice = product.getPrice() * weightOfProduct/1000;
            if ((product.getPrice() * weightOfProduct)%1000 >= 500){
                retPrice += 1;
            }
            if (retPrice < 1){
                return 1l;
            }
            return retPrice;
        }
        return product.getPrice() * amountOfProduct;
    }

    @Override
    public String toString() {
        StringBuilder name = new StringBuilder(getProductType().getName());
        if (name.length() > MAX_LENGTH_NAME){
            name = new StringBuilder(name.substring(0, MAX_LENGTH_NAME - 3) + "...");
        }

        if (amountOfProduct > 1) {
            String multipleProductsPrice = amountOfProduct + "st * " + product.getPrice();
            name.append(" ").append(multipleProductsPrice);
        }

        if (product.isPricedByWeight()){
            double formattedWeight = weightOfProduct;
            String multipleProductsPrice = "%s * %dkr/kg".formatted(String.format("%.3f", formattedWeight/1000), product.getPrice());
            name.append(" ").append(multipleProductsPrice);
        }

        while (name.length() < MAX_LENGTH_ORDER_LINE - String.valueOf(getTotalPrice()).length()){
            name.append(" ");
        }
        name.append(getTotalPrice());
        return name.toString();
    }
}
