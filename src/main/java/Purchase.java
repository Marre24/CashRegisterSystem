/* some methods are public to allow for testing */

public class Purchase {
    private final Order order;
    private Receipt receipt;
    private final PaymentCard card;

    public Purchase(Order order, PaymentCard card){
        this.order = order;
        this.card = card;
    }

    public void handlePayment(){
        if(card instanceof Debit){
            long cardBalance = ((Debit)card).getBalance();
            long orderTotalPrice = order.getTotalPrice();
            if(debitCardBalanceCoversPurchase(cardBalance, orderTotalPrice)){
                deductOrderTotalPriceFromCardBalance(orderTotalPrice);
            } else {
                throw new IllegalStateException("Not enough money in account");
            }
        } else if(card instanceof Credit){

        }
    }
    // Probably better to use getBalance() instead of passing data as params, but for now keep it as such because those
    // methods were not implemented at the point of testing this class.
    public boolean debitCardBalanceCoversPurchase(long cardBalance, long orderTotalPrice) {
        return cardBalance >= orderTotalPrice;
    }

    // should be private but for testing sake is public
    public void deductOrderTotalPriceFromCardBalance(long price){
        ((Debit)card).deductFromBalance(price);
    }
}


// When customer blippar kreditkort, then Purchase is created
// Has  reference to order, and a debit card.
// creates/generates receipt for this purchase at end of purchase
// "Is a finished order"

// Functionality to be tested: Withdraw order "total amount" from DebitCard ??


// createReceipt(), creates the receipt for this purchase, with info sent AND debitcard info
// 1. Tipping during purchase?
// Debit type or credit type? adds functionality: if debit and balance is < 0, cancel purchase
// if credit and balance is < 0, continue with purchase (balance can be negative for credit cards)

