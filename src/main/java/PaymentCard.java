abstract public class PaymentCard {

    //When credit card balance is negative the card issuer ows the cardholder money
    protected long balance = 0;
    final protected String bank;
    final protected String pan;
    final protected String expirationDate;
    final protected int csc;
    final protected Person owner;

    public PaymentCard(Person owner, String bank, String pan, String expirationDate, int csc){
        this.owner = owner;
        this.bank = bank;
        this.pan = pan;
        this.expirationDate = expirationDate;
        this.csc = csc;
    }

    public long getBalance(){
        return balance;
    }

    protected abstract boolean canMakePurchase(long price);

    public abstract void pay(long price);

    public String getPan (){
        return this.pan;
    }

    @Override
    public String toString() {
        return "**** **** **** " + pan.substring(pan.length() - 4, pan.length());
    }
}