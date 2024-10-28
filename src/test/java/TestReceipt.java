import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    private String testCompany = "Temp Company Name\nCompany Address\nPostcode CityName\nOrg.nr. 11111111-1111";
    private String testReceiptHeader = "RECEIPT";
    private Product product = new Product("Product",10, Producer.Arla, ProductGroup.Beverage);
    private Product product2 = new Product("Product2",20, Producer.Arla, ProductGroup.Beverage);

    @Test
    void PrintReceipt_ReceiptPrintsToFile_(){
        Employee employee = new Employee("FirstName","SurName","A","A","A","A");
        Person person = new Person("B","B","B","B","B","B");
        ProductScanner productScanner = new ProductScanner(employee);
        productScanner.startNewOrder();
        productScanner.scanProduct(product);
        productScanner.scanProduct(product);
        productScanner.scanProduct(product2);
        Order order = productScanner.getActiveOrder();
        String fileDirectory = "Receipts/" + order.getId() + ".txt";
        PaymentCard paymentCard = new DebitCard(person, "b","1111-1111-1111-1111","1",1,0);
        Purchase purchase = new Purchase(order, paymentCard);
        Receipt.printReceipt(purchase);
        try{
            FileReader fileReader = new FileReader(fileDirectory);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder s = new StringBuilder();
            String st = "";
            while((st = bufferedReader.readLine()) != null){
                 s.append(st + "\n");
            }
            st = s.substring(0, s.length()-1);
            assertEquals("%s\n\n%s\n\n%s\n\nPurchase id: %s".formatted(testCompany, testReceiptHeader, purchase.toString(), order.getId()), st.toString());
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
