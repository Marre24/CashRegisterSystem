import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Purchase {

    private String OUTLINE = "**************************************************";

    private final Order order;
    private Receipt receipt;
    private final PaymentCard card;
    private final LocalDate date;
    private final LocalTime time;

    public Purchase(Order order, PaymentCard card){
        this.order = order;
        this.card = card;
        this.date = LocalDate.now();
        this.time = LocalTime.now();

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

    public String getId(){
        return order.getId();
    }

    public boolean debitCardBalanceCoversPurchase(long cardBalance, long orderTotalPrice) {
        return cardBalance >= orderTotalPrice;
    }

    public void deductOrderTotalPriceFromCardBalance(long price){
        ((Debit)card).deductFromBalance(price);
    }

    @Override
    public String toString(){
        return "Date: " + date.toString() + " Time: " + time.toString().substring(0,5) + "\n" + OUTLINE + "\n" + order.toString() + OUTLINE + "\n" + card.toString();
    }
}


