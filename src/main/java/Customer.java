public class Customer {
    final long socialSecurityNr;
    private String firstName;
    private String surName;
    private long phoneNr;
    private String emailAdress;
    private String homeAdress;

    public Customer(String firstName, String surName, long socialSecurityNr, long phoneNr, String emailAdress, String homeAdress){
        this.firstName = firstName;
        this.surName = surName;
        this.socialSecurityNr = socialSecurityNr;
        this.phoneNr = phoneNr;
        this.emailAdress = emailAdress;
        this.homeAdress = homeAdress;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getSurName(){
        return this.surName;
    }

    public long getSocialSecurityNr(){
        return this.socialSecurityNr;
    }

    public long getPhoneNr(){
        return this.phoneNr;
    }

    public String getEmailAdress(){
        return this.emailAdress;
    }

    public String getHomeAdress(){
        return this.homeAdress;
    }

    public void setFirstName(String s){
        this.firstName = s;
    }

    public void setSurName(String s){
        this.surName = s;
    }

    public void setPhoneNr(long l){
        this.phoneNr = l;
    }

    public void setEmailAdress(String s){
        this.emailAdress = s;
    }

    public void setHomeAdress(String s){
        this.homeAdress = s;
    }

    public String getFullName(){
        return firstName + " " + surName;
    }

    @Override
    public String toString(){
        return "Name: %s\nSocial Security Number: %d\nPhone Number: %d\nEmail Adress: %s\nHome Adress: %s".formatted(getFullName(), socialSecurityNr, phoneNr, emailAdress, homeAdress);
    }

}
