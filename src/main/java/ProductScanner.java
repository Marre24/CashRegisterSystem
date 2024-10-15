public class ProductScanner {
    private Order activeOrder = null;

    public ProductScanner(){
    }

    public void scanProduct(Product product) {
        activeOrder.addProduct(product);
    }

    public boolean orderExists(){
        return activeOrder != null;
    }

    public void setActiveOrder(Order order){
        this.activeOrder = order;
    }

    public Order getOrder(){
        return activeOrder;
    }

    public boolean containsKey(Product p){
        return activeOrder.containsProduct(p);
    }
}
