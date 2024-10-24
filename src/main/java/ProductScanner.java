import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProductScanner {

    private static ArrayList<Order> ordersOnHold = new ArrayList<>();
    private Order activeOrder;
    private Employee activeEmployee;

    public ProductScanner(Employee activeEmployee) {
        this.activeEmployee = activeEmployee;
    }

    public void scanProduct(ProductType productType) {
        activeOrder.addProduct(productType);
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
    } //Check if null

    public Employee getActiveEmployee() {
        return activeEmployee;
    }

    public boolean containsProduct(ProductType p){
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
}
