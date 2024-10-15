public class Membership  {

    static long nextId = 0;

    Customer member;

    private long id;
    private final double priceAfterDiscount = 0.95;

    public Membership(Customer c){
        if (c.isMember()){
            throw new IllegalArgumentException("Customer is already a member");
        } else{
            c.setMemberStatus(true);
            this.member = c;
            this.id = nextId;
            nextId ++;
        }
    }

    public Customer getMember(){
        return this.member;
    }

    public long getId(){
        return this.id;
    }

    public void setNextId(long reset){
        nextId = reset;
    }

    public double getPriceAfterDiscount(){ return this.priceAfterDiscount;}
}
