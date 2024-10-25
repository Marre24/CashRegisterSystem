import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestReceipt {

    private String testCompany = "Temp Company Name\nCompany Address\nPostcode CityName\nOrg.nr. 11111111-1111";
    private String testReceiptHeader = "RECEIPT";

    @Test
    void PrintReceipt_ReceiptPrintsToFile_(){
        Employee employee = new Employee("A","A","A","A","A","A");
        Person person = new Person("B","B","B","B","B","B");
        ProductScanner productScanner = new ProductScanner(employee);
        productScanner.startNewOrder();
        Product product = new Product("Product",10, ProductGroup.Beverage);
        productScanner.scanProduct(product);
        Order order = productScanner.getActiveOrder();
        String fileDirectory = "Receipts/" + order.getId() + ".txt";
        PaymentCard paymentCard = new Debit(person, "b","b","1",1,0);
        Purchase purchase = new Purchase(order, paymentCard);
        Receipt receipt = new Receipt(purchase);
        receipt.printReceipt();
        try{
            FileReader fileReader = new FileReader(fileDirectory);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder s = new StringBuilder();
            String st = "";
            while((st = bufferedReader.readLine()) != null){
                 s.append(st + "\n");
            }
            st = s.substring(0, s.length()-1);
            assertEquals("%s\n\n%s\n\n%s".formatted(testCompany, testReceiptHeader, purchase.toString()), st.toString());
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
