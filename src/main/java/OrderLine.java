import java.math.BigInteger;

public class OrderLine {

    private ProductType productType = null;
    private int amountOfProduct = 0;

    public void addProduct(ProductType newProductType) {
        if (newProductType == null)
            throw new IllegalArgumentException("Tried to add null to a orderLine");

        if (this.productType == null){
            this.productType = newProductType;
            amountOfProduct++;
            return;
        }

        if (this.productType != newProductType)
            throw new IllegalArgumentException("Tried to add a productType to a orderLine with another productType");


        if (Long.MAX_VALUE - getTotalPrice() < productType.getPrice())
            throw new IllegalArgumentException();

        amountOfProduct++;
    }

    public ProductType getProductType() {
        return productType;
    }

    public int getAmountOfProduct() {
        return amountOfProduct;
    }

    public long getTotalPrice() {
        return productType.getPrice() * amountOfProduct;
    }

    @Override
    public String toString() {
        if (amountOfProduct > 1)
            return getProductType().getName() + " " + amountOfProduct + "st*" + productType.getPrice() + " " + getTotalPrice();
        return getProductType().getName() + " " + getTotalPrice();
    }
}
