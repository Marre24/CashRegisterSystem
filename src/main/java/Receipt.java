import java.util.*;

public class Receipt {


    private final List<Product> products;
    private String id;
    private static List<String> ids;


    public Receipt(List<Product> products){

        this.products = Collections.unmodifiableList(products);
        generateID();
    }


    public List<Product> getItems() {
        return products;
    }


    public String getID(){
        return id;
    }


    // 6 digits : int n = 100000 + rnd.nextInt(900000);
    // generate string of random 13 numbers
    private void generateID(){
        Random rnd = new Random();
        long l = 1000000000000L + rnd.nextLong(9000000000000L);
        String randomlyGenerated = Long.toString(l);
        if(getIDs().contains(randomlyGenerated)){
            generateID();
        } else {
            this.id = randomlyGenerated;
            ids.add(id);
        }
    }

    // solve by mocking or smth instead. The idea is that we fetch ID's from database primary key
    private List<String> getIDs(){
        return ids;
    }

    // add items to itself
    // remove items from itself
    // sort items alphabetically
    // utskrift, datum och tid vid utskrift
    // testbart med mock-objekt och Java interface for testing against printers
    // namnet pa kunden for langt, gor ej att skriva pa kvitto? ekvivalensklasser som poverkar fonten poa text?
    // or do nextline for a substring of the name when it reaches a certain length
}
