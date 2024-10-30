import java.io.*;

public class Receipt {

    private static final String tempCompany = "Temp Company Name\nCompany Address\nPostcode CityName\nOrg.nr. 11111111-1111";
    private static final String receiptHeader = "RECEIPT";

    public static void printReceipt(Purchase purchase, PaymentCard card){
        try {
            File receiptFile = new File("./Receipts/" + purchase.getId() + ".txt");
            BufferedWriter receiptWriter = new BufferedWriter(new FileWriter(receiptFile));
            receiptWriter.write("%s\n\n%s\n\n%s\n%s\n\nPurchase id: %s".formatted(tempCompany, receiptHeader, purchase.toString(), card.toString(), purchase.getId()));
            receiptWriter.close();
        } catch (IOException e){
            System.out.println("Error writing receipt file");
        }
    }
}
