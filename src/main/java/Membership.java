public class Membership  {

    Person owner;
    //DebitCard debitCard; null, the owner of the debitCard is the same as the owner of the membership
    public final static double DISCOUNT_FACTOR = 0.05;

    public Membership(Person person){
        if (person.isMember()){
            throw new IllegalArgumentException("Customer is already a member");
        } else{
            person.addMembership(this);
            this.owner = person;
        }
    }

    public Person getOwner(){
        return this.owner;
    }
}
