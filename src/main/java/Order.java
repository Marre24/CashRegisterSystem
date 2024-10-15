import java.util.HashMap;
import java.util.Map;

public class Order {


    // maintain list of products



    private final Employee responsibleEmployee;

    private final Map<Product, Integer> products = new HashMap<>();

    public Order(Employee employee) {
        this.responsibleEmployee = employee;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int differentProductAmount() {
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

    public int getAmountOfProduct(Product p) {
        return products.get(p);
    }


    public boolean containsProduct(Product p) {
        return products.containsKey(p);
    }
    @Override
    public String toString(){
        String s = "";
        for (Product p : products.keySet()){
            int a = products.get(p);
            s = s + a + " x " + p.getName() + " " + p.getPrice() + "\n";
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }
}
