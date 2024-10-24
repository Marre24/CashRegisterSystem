/* A credit card is created with 0 debt and some amount of max credit is set */

public class Credit extends PaymentCard{

    private static final long INITIAL_DEBT = 0;

    private final long maxCredit;
    private long currentDebt;
    public Credit(Person owner, String bank, String PAN, String expirationDate, int csc, long maxCredit){
        super(owner, bank, PAN, expirationDate, csc);
        currentDebt = INITIAL_DEBT;
        this.maxCredit = maxCredit;
    }


    public boolean canMakePurchase(long orderTotalPrice) {
        return maxCredit >= currentDebt + orderTotalPrice;
    }

    public long getDebt(){
        return currentDebt;
    }

    public void addDebt(long debt){
        if(debt + currentDebt > maxCredit){
            throw new IllegalStateException();
        } else {
            this.currentDebt += debt;
        }
    }
}
