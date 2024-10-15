import java.util.ArrayList;
import java.util.List;

public class ProductScanner {

    private final List<Product> products;

    public ProductScanner(){
        products = new ArrayList<>();
    }


    public void scanProduct(Product product) {
        products.add(product);
    }

    public List<Product> getScannedItems(){
        return products;
    }
}
