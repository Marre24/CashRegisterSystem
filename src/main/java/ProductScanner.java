import java.util.ArrayList;

public class ProductScanner {

    private static final ArrayList<Order> ordersOnHold = new ArrayList<>();
    private Order activeOrder;
    private Employee activeEmployee;

    public ProductScanner(Employee activeEmployee) {
        this.activeEmployee = activeEmployee;
    }

    public void scanProduct(Product product) {
        activeOrder.addProduct(product);
    }

    public boolean hasActiveOrder(){
        return activeOrder != null;
    }

    public void setActiveOrder(Order order){
        this.activeOrder = order;
    }

    public Order getActiveOrder(){
        if (hasActiveOrder())
            return activeOrder;
        else return null;
    }

    public Employee getActiveEmployee() {
        return activeEmployee;
    }

    public boolean containsProduct(Product p){
        return activeOrder.containsProduct(p);
    }

    public void startNewOrder(){
        activeOrder = new Order(activeEmployee);
    }

    public void setActiveOrderOnHold(){
        ordersOnHold.add(activeOrder);
        activeOrder = null;
    }

    public ArrayList<Order> getOrdersOnHold(){
        return ordersOnHold;
    }

    public Order getOrderOnHold(String s){
        for (Order order : ordersOnHold) {
            if (order.getId().equals(s)){
                return order;
            }
        }
        return null;
    }

    public void finalizeOrder(){
        Purchase purchase = new Purchase(activeOrder);
        if (purchase.handlePayment()){
            activeOrder = null;
        }
    }
}
