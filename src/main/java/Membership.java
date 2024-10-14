public class Membership  {

    static long nextId = 0;

    Customer member;
    private long points = 0;
    private long id;

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

    public long getPoints(){
        return this.points;
    }

    public long getId(){
        return this.id;
    }
}
