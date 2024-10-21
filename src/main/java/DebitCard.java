public class DebitCard {

    final private String bank;
    final private String PAN;
    final private String expirationDate;
    final private int csc;
    final private Person owner;

    public DebitCard(Person owner, String bank, String PAN, String expirationDate, int csc){
        this.owner = owner;
        this.bank = bank;
        this.PAN = PAN;
        this.expirationDate = expirationDate;
        this.csc = csc;
    }
}
