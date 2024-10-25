public class Purchase {
    private final Order order;
    private Receipt receipt;
    private final PaymentCard card;

    public Purchase(Order order, PaymentCard card){
        this.order = order;
        this.card = card;
    }

    public void handlePayment(){
        if(card instanceof DebitCard){
            long cardBalance = ((DebitCard)card).getBalance();
            long orderTotalPrice = order.getTotalPrice();
            if(debitCardBalanceCoversPurchase(cardBalance, orderTotalPrice)){
                deductOrderTotalPriceFromCardBalance(orderTotalPrice);
            } else {
                throw new IllegalStateException("Not enough money in account");
            }
        } else if(card instanceof CreditCard){

        }
    }

    public boolean debitCardBalanceCoversPurchase(long cardBalance, long orderTotalPrice) {
        return cardBalance >= orderTotalPrice;
    }

    public void deductOrderTotalPriceFromCardBalance(long price){
        card.pay(price);
    }
}

