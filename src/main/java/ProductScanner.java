public class ProductScanner {
    private Order activeOrder = null;

    public void scanProduct(ProductType productType) {
        activeOrder.addProduct(productType);
    }

    public boolean hasActiveOrder(){
        return activeOrder != null;
    }

    public void setActiveOrder(Order order){
        this.activeOrder = order;
    }

    public Order getOrder(){
        return activeOrder;
    } //Check if null

    public boolean containsProduct(ProductType p){
        return activeOrder.containsProduct(p);
    }
}
