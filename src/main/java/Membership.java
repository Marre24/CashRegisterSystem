public class Membership  {

    private final Person owner;
    public final static double DISCOUNT_FACTOR = 0.05;

    public Membership(Person person){
        if (person == null)
            throw new IllegalArgumentException("Customer is null");

        if (person.isMember())
            throw new IllegalArgumentException("Customer is already a member");

        person.addMembership(this);
        this.owner = person;
    }

    public Person getOwner(){
        return this.owner;
    }
}
