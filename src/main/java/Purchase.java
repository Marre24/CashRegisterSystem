import java.time.LocalDate;
import java.time.LocalTime;

public class Purchase {

    private String OUTLINE = "--------------------------------------------------";

    private final Order order;
    private final LocalDate date;
    private final LocalTime time;

    public Purchase(Order order){
        this.order = order;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public boolean handlePayment(PaymentCard card){
        try{
            if(card.getOwner().isMember())
                card.pay(order.getMemberPrice());
            else
                card.pay(order.getTotalPrice());

            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public String getId(){
        return order.getId();
    }

    public boolean debitCardBalanceCoversPurchase(long cardBalance, long orderTotalPrice) {
        return cardBalance >= orderTotalPrice;
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
                "\n\n" + "Sold by: " + order.getResponsibleEmployeeName();
    }
}


