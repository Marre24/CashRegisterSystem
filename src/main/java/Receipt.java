import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Receipt {

    private String tempCompany = "Temp Company Name\nCompany Address\nPostcode CityName\nOrg.nr. 11111111-1111";
    private String receiptHeader = "RECEIPT";
    private Purchase purchase;

    public Receipt(Purchase purchase) {
        this.purchase = purchase;
    }

    public void printReceipt(){
        try {
            File receiptFile = new File("./Receipts/" + purchase.getId() + ".txt");
            BufferedWriter receiptWriter = new BufferedWriter(new FileWriter(receiptFile));
            receiptWriter.write("%s\n\n%s\n\n%s\n\nPurchase id: %s".formatted(tempCompany, receiptHeader, purchase.toString(), purchase.getId()));
            receiptWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
