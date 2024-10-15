import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

public class Receipt {

    private final List<Product> products;

    public Receipt(List<Product> products){
        this.products = Collections.unmodifiableList(products);
    }


    public List<Product> getItems() {
        return products;
    }
}
