public class Purchase {
    private Order order;
    private Receipt receipt;
    private DebitCard card;

    public Purchase(){

    }

    public Receipt createReceipt(){
        return null;
    }

    // Probably better to use getBalance() instead of passing data as params, but for now keep it as such because those
    // methods were not implemented at the point of testing this class.
    public boolean cardBalanceCoversPurchase(long cardBalance, long orderBalance) {
        return cardBalance >= orderBalance;
    }
}


// When customer blippar kreditkort, then Purchase is created
// Has  reference to order, and a debit card.
// creates/generates receipt for this purchase at end of purchase
// "Is a finished order"

// Functionality to be tested: Withdraw order "total amount" from DebitCard ??
// createReceipt(), creates the receipt for this purchase, with info sent AND debitcard info
// tipping?
// Debit type or credit type? adds functionality: if debit and balance is < 0, cancel purchase
// if credit and balance is < 0, continue with purchase (balance can be negative for credit cards)