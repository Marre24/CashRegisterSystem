public class Person {
    private final String socialSecurityNr;
    private String firstName;
    private String surName;
    private String phoneNr;
    private String emailAddress;
    private String homeAddress;

    public Person(String firstName, String surName, String socialSecurityNr, String phoneNr, String emailAddress, String homeAddress){
        this.firstName = firstName;
        this.surName = surName;
        this.socialSecurityNr = socialSecurityNr;
        this.phoneNr = phoneNr;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
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

    public String getEmailAddress(){
        return this.emailAddress;
    }

    public String getHomeAddress(){
        return this.homeAddress;
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

    public void setEmailAddress(String s){
        this.emailAddress = s;
    }

    public void setHomeAddress(String s){
        this.homeAddress = s;
    }

    public String getFullName(){
        return firstName + " " + surName;
    }

    @Override
    public String toString(){
        return "Name: %s\nSocial Security Number: %s\nPhone Number: %s\nEmail Address: %s\nHome Address: %s".formatted(getFullName(), socialSecurityNr, phoneNr, emailAddress, homeAddress);
    }
}
