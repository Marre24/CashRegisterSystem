public class Person {
    private final String socialSecurityNr;
    private String firstName;
    private String surName;
    private String phoneNr;
    private String emailAdress;
    private String homeAdress;

    public Person(String firstName, String surName, String socialSecurityNr, String phoneNr, String emailAdress, String homeAdress){
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

    public String getSocialSecurityNr(){
        return this.socialSecurityNr;
    }

    public String getPhoneNr(){
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

    public void setPhoneNr(String s){
        this.phoneNr = s;
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
        return "Name: %s\nSocial Security Number: %s\nPhone Number: %s\nEmail Adress: %s\nHome Adress: %s".formatted(getFullName(), socialSecurityNr, phoneNr, emailAdress, homeAdress);
    }
}
