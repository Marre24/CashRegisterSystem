public class DebitCard extends PaymentCard {

    public DebitCard(Person owner, String bank, String PAN, String expirationDate, int csc, long initialBalance){
        super(owner, bank, PAN, expirationDate, csc);
        this.balance = initialBalance;
    }

    @Override
    public boolean canMakePurchase(long price) {
        return balance >= price;
    }

    @Override
    public void pay(long price){
        if (!canMakePurchase(price))
            throw new IllegalArgumentException("Balance could not cover the price");
        this.balance -= price;
    }
}
