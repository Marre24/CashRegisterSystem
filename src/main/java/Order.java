import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Order {

    private final UUID id = UUID.randomUUID();
    private final Employee responsibleEmployee;
    private final Collection<OrderLine> orderLines = new ArrayList<>();


    public Order(Employee employee) {
        if(employee == null) {
            throw new IllegalArgumentException();
        }
        this.responsibleEmployee = employee;
    }

    public Collection<OrderLine> getOrderLines() {
        return orderLines;
    }

    //delete from line and delete in line
    public void addProduct(Product product) {
        for (OrderLine orderLine : orderLines){
            if (orderLine.getProductType().equals(product)) {
                orderLine.addProduct(product);
                return;
            }
        }
        orderLines.add(new OrderLine(product));
    }

    public void addProduct(Product product, long weight ){
        for (OrderLine orderLine : orderLines){
            if (orderLine.getProductType().equals(product)){
                orderLine.addProduct(product, weight);
                return;
            }
        }
        orderLines.add(new OrderLine(product, weight));
    }

    public int getProductAmount(Product p) {
        for (OrderLine orderLine : orderLines)
            if (orderLine.getProductType() == p)
                return orderLine.getAmountOfProduct();
        return 0;
    }
    public boolean containsProduct(Product p) {
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

    public String getResponsibleEmployeeName(){
        return responsibleEmployee.getFullName();
    }

    public long getMemberPrice() {
        return Double.valueOf(getTotalPrice() * (1 - Membership.DISCOUNT_FACTOR)).longValue();
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (OrderLine orderLine : orderLines)
            s.append(orderLine.toString()).append("\n");
        return s.substring(0, s.length() - 1);
    }
}
