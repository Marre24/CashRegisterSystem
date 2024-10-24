public class OrderLine {

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
        return product.getPrice() * amountOfProduct;
    }

    @Override
    public String toString() {
        if (getProductType().getName().length() > 30){
            String newName = getProductType().getName().substring(0, 30) + "...";
            if (amountOfProduct > 1)
                return newName + " " + amountOfProduct + "st*" + product.getPrice() + " " + getTotalPrice();
            return newName + " " + getTotalPrice();
        }
        if (amountOfProduct > 1)
            return getProductType().getName() + " " + amountOfProduct + "st*" + product.getPrice() + " " + getTotalPrice();
        return getProductType().getName() + " " + getTotalPrice();
    }
}
