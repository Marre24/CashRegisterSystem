import java.util.ArrayList;

public class ProductScanner {

    private static final ArrayList<Order> ordersOnHold = new ArrayList<>();
    private Order activeOrder;
    private Employee loggedInEmployee;

    public void scanProduct(Product product) {
        activeOrder.addProduct(product);
    }

    public void scanProduct(Product product, long weight) {
        activeOrder.addProduct(product, weight);
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
        return null;
    }

    public void setLoggedInEmployee(Employee employee){
        if (loggedInEmployee != null)
            loggedInEmployee.logOut();
        loggedInEmployee = employee;
    }

    public Employee getLoggedInEmployee() {
        return loggedInEmployee;
    }

    public boolean containsProduct(Product p){
        return activeOrder.containsProduct(p);
    }

    public void startNewOrder(){
        // TODO: 2024-10-29 cant start new order without loggedInEmployee
        activeOrder = new Order(loggedInEmployee);
    }

    public void setActiveOrderOnHold(){
        ordersOnHold.add(activeOrder);
        activeOrder = null;
    }

    public Order getOrderOnHold(String s){
        for (Order order : ordersOnHold) {
            if (order.getId().equals(s)){
                return order;
            }
        }
        return null;
    }

    public void finalizeOrder(PaymentCard card){
        Purchase purchase = new Purchase(activeOrder);
        if (purchase.handlePayment(card)){
            Receipt.printReceipt(purchase, card);
            activeOrder = null;
        }
    }
}
