/* Data class */
// possibly rename to "PaymentCard" and then subclass by "Debit" and "Credit"
abstract public class PaymentCard {

    final private String bank;
    final private String PAN;
    final private String expirationDate;
    final private int csc;
    final private Person owner;
    private long balance;
    // add credit/debit type

    public PaymentCard(Person owner, String bank, String PAN, String expirationDate, int csc){
        this.owner = owner;
        this.bank = bank;
        this.PAN = PAN;
        this.expirationDate = expirationDate;
        this.csc = csc;
    }
}

// rename to Card, remove balance as such
// Subclass with Debit and Credit
// Debit has balance, but credit max amount (that can be put in to debt effectively)
// Thus the testing for Debit remains the same as currently in PurchaseTest, but, for Credit, we instead test
// if the purchase is less than max amount