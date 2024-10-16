import java.util.HashMap;
import java.util.Map;

public class Order {

    private final Employee responsibleEmployee;
    private final Map<Product, Integer> products = new HashMap<>();


    public Order(Employee employee) {
        this.responsibleEmployee = employee;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int amountOfDifferentProducts() {
        return products.size();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        if (products.containsKey(p))
            products.put(p,products.get(p) + 1);
        else
            products.put(p, 1);
    }

    public int getAmount(Product p) {
        return products.get(p);
    }

    public boolean containsProduct(Product p) {
        return products.containsKey(p);
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (Product p : products.keySet()){
            int amount = products.get(p);
            s.append(amount).append(" x ").append(p.getName()).append(" ").append(p.getPrice()).append("\n");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        return s.toString();
    }
}
