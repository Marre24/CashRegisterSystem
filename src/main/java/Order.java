import java.util.HashMap;
import java.util.Map;

public class Order {

    private final Employee responsibleEmployee;
    private final Map<ProductType, Integer> products = new HashMap<>();


    public Order(Employee employee) {
        this.responsibleEmployee = employee;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int amountOfDifferentProducts() {
        return products.size();
    }

    public Map<ProductType, Integer> getProducts() {
        return products;
    }

    public void addProduct(ProductType p) {
        if (products.containsKey(p))
            products.put(p,products.get(p) + 1);
        else
            products.put(p, 1);
    }

    public int getAmount(ProductType p) {
        return products.get(p);
    }

    public boolean containsProduct(ProductType p) {
        return products.containsKey(p);
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (ProductType p : products.keySet()){
            int amount = products.get(p);
            s.append(amount).append(" x ").append(p.getName()).append(" ").append(p.getPrice()).append("\n");
        }
        s = new StringBuilder(s.substring(0, s.length() - 1));
        return s.toString();
    }
}
