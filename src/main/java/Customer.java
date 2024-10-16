public class Customer extends Person {
    private boolean member = false;

    public Customer(String firstName, String surName, String socialSecurityNr, String phoneNr, String emailAddress, String homeAddress){
        super(firstName, surName, socialSecurityNr, phoneNr, emailAddress, homeAddress);
    }

    public void setMemberStatus(boolean b){
        this.member = b;
    }

    public boolean isMember(){
        return this.member;
    }

    @Override
    public String toString(){
        return super.toString() + "\nIs member: " + this.member;
    }
}