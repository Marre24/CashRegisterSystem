public class ProductScanner {
    private Order activeOrder = null;

    public void scanProduct(Product product) {
        activeOrder.addProduct(product);
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

    public boolean containsProduct(Product p){
        return activeOrder.containsProduct(p);
    }
}
