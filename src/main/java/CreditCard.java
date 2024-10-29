/* A credit card is created with 0 debt and some amount of max credit is set */

public class CreditCard extends PaymentCard{

    private final long limit;

    public CreditCard(Person owner, String bank, String PAN, String expirationDate, int csc, long limit){
        super(owner, bank, PAN, expirationDate, csc);
        this.limit = limit;
    }

    @Override
    public boolean canMakePurchase(long price) {
        return limit >= balance + price;
    }

    @Override
    public void pay(long price){
        if (canMakePurchase(price)) {
            this.balance += price;
        } else {
            throw new IllegalArgumentException("Price + balance would exceed limit");
        }
    }
}
