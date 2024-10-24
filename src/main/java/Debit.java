public class Debit extends PaymentCard {
    private long balance;

    public Debit(Person owner, String bank, String PAN, String expirationDate, int csc, long initialBalance){
        super(owner, bank, PAN, expirationDate, csc);
        this.balance = initialBalance;
    }

    // In reality this money would be transferred elsewhere. We only handle the part where the money is drawn from this account
    public void deductFromBalance(long price){
        if(balance < price){
            throw new IllegalStateException();
        } else {
            this.balance -= price;
        }
    }

    public long getBalance(){
        return balance;
    }
}
