import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Order {

    private final UUID id = UUID.randomUUID();
    private final Employee responsibleEmployee;
    private final Collection<OrderLine> orderLines = new ArrayList<>();


    public Order(Employee employee) {
        this.responsibleEmployee = employee;
    }

    public Collection<OrderLine> getOrderLines() {
        return orderLines;
    }

    //delete from line and delete in line
    public void addProduct(ProductType p) {
        for (OrderLine orderLine : orderLines)
            if (orderLine.getProductType() == p){
                orderLine.addProduct(p);
                return;
            }
        orderLines.add(new OrderLine(p));
    }

    public int getProductAmount(ProductType p) {
        for (OrderLine orderLine : orderLines)
            if (orderLine.getProductType() == p)
                return orderLine.getAmountOfProduct();
        return 0;
    }
    public boolean containsProduct(ProductType p) {
        for (OrderLine orderLine : orderLines)
            if (orderLine.getProductType() == p)
                return true;
        return false;
    }

    public long getTotalPrice(){
        long price = 0;
        for (OrderLine orderLine : orderLines)
            price += orderLine.getTotalPrice();
        return price;
    }

    public String getId(){
        return id.toString();
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (OrderLine orderLine : orderLines)
            s.append(orderLine.toString()).append("\n");
        return s.toString();
    }
}
