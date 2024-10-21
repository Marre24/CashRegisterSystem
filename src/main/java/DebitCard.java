/* Data class */
// possibly rename to "PaymentCard" and then subclass by "Debit" and "Credit"
public class DebitCard {

    final private String bank;
    final private String PAN;
    final private String expirationDate;
    final private int csc;
    final private Person owner;
    private long balance;
    // add credit/debit type

    public DebitCard(Person owner, String bank, String PAN, String expirationDate, int csc, long balance){
        this.owner = owner;
        this.bank = bank;
        this.PAN = PAN;
        this.expirationDate = expirationDate;
        this.csc = csc;
        this.balance = balance;
    }
}
