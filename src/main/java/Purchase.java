public class Purchase {
    private final Order order;
    private Receipt receipt;
    private final PaymentCard card;

    public Purchase(Order order, PaymentCard card){
        this.order = order;
        this.card = card;
    }

    public boolean handlePayment(){
        if(card instanceof Debit){
            long cardBalance = ((Debit)card).getBalance();
            long orderTotalPrice = order.getTotalPrice();
            if(debitCardBalanceCoversPurchase(cardBalance, orderTotalPrice)){
                deductOrderTotalPriceFromCardBalance(orderTotalPrice);
                return true;
            }

        }
        return false;
        /*
        if(card instanceof Credit){

        }
        return false;*/
    }

    public boolean debitCardBalanceCoversPurchase(long cardBalance, long orderTotalPrice) {
        return cardBalance >= orderTotalPrice;
    }

    public void deductOrderTotalPriceFromCardBalance(long price){
        ((Debit)card).deductFromBalance(price);
    }
}

