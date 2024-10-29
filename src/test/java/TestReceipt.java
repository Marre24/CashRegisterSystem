import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    private final static String TEST_COMPANY = "Temp Company Name\nCompany Address\nPostcode CityName\nOrg.nr. 11111111-1111";
    private final static String TEST_RECEIPT_HEADER = "RECEIPT";
    private final Product product = new Product("Product",10, Producer.Arla, ProductGroup.Beverage);
    private final Product product2 = new Product("Product2",20, Producer.Arla, ProductGroup.Beverage);

    @Test
    void PrintReceipt_ReceiptPrintsToFile_(){
        Employee employee = new Employee("FirstName","SurName","A","A","A","A");
        Person person = new Person("B","B","B","B","B","B");
        PaymentCard paymentCard = new DebitCard(person, "b","1111-1111-1111-1111","1",1,0);
        ProductScanner productScanner = new ProductScanner();

        productScanner.setLoggedInEmployee(employee);
        productScanner.startNewOrder();
        productScanner.scanProduct(product);
        productScanner.scanProduct(product);
        productScanner.scanProduct(product2);

        Order order = productScanner.getActiveOrder();
        Purchase purchase = new Purchase(order);

        String fileDirectory = "Receipts/" + order.getId() + ".txt";

        Receipt.printReceipt(purchase, paymentCard);

        try{
            FileReader fileReader = new FileReader(fileDirectory);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder s = new StringBuilder();
            String stringFromFile;

            while((stringFromFile = bufferedReader.readLine()) != null){
                 s.append(stringFromFile).append("\n");
            }

            stringFromFile = s.substring(0, s.length()-1);
            String expected = "%s\n\n%s\n\n%s\n%s\n\nPurchase id: %s".formatted(TEST_COMPANY, TEST_RECEIPT_HEADER, purchase.toString(), paymentCard.toString(), order.getId());

            assertEquals(expected, stringFromFile);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
