import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Purchase {

    private String OUTLINE = "--------------------------------------------------";

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

    public String getId(){
        return order.getId();
    }

    public boolean debitCardBalanceCoversPurchase(long cardBalance, long orderTotalPrice) {
        return cardBalance >= orderTotalPrice;
    }

    public void deductOrderTotalPriceFromCardBalance(long price){
        card.pay(price);
    }

    public String getTotalLine(){
        String total = "Total Price                                       ";
        String totalAmount = order.getTotalPrice() + " SEK";
        total = total.substring(0, total.length() - totalAmount.length());
        total = total + totalAmount;
        return total;
    }

    @Override
    public String toString(){
        return "Date: " + date.toString() +
                " Time: " + time.toString().substring(0,5) +
                "\n" + OUTLINE + "\n" + order.toString() +
                "\n" + OUTLINE + "\n" + getTotalLine() +
                "\n\n" + "Sold by: " + order.getResponsibleEmployeeName() +
                "\n" + card.toString();
    }
}


