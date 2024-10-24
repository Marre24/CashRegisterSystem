import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Receipt {

    private static ArrayList<String> ids;
    private final List<Product> products;
    private String barcode;

    public Receipt(List<Product> products){
        ids = new ArrayList<>();
        this.products = Collections.unmodifiableList(products);
        generateRandomUniqueBarcode();
    }

    public List<Product> getProducts() {
        return products;
    }
    
    public String getBarcode(){
        return barcode;
    }

    // Generate String of 13 random integers to resemble a barcode
    private void generateRandomUniqueBarcode(){
        Random rnd = new Random();
        long random13Digits = 1000000000000L + rnd.nextLong(9000000000000L);
        String randomlyGenerated = Long.toString(random13Digits);
        if(ids.contains(randomlyGenerated)){
            generateRandomUniqueBarcode(); // Re-attempt to generate ID (Technically can loop indefinitely, so can be better implemented)
        } else {
            this.barcode = randomlyGenerated;
            ids.add(barcode);
        }
    }
}
