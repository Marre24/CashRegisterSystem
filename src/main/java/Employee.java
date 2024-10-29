public class Employee extends Person{

    private ProductScanner activeScanner = null;

    public Employee(String firstName, String surName, String socialSecurityNr, String phoneNumber, String emailAddress, String homeAddress) {
        super(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
    }

    @Override
    public String toString() {
        return getFullName();
    }

    public void logIntoScanner(ProductScanner scanner){
        this.activeScanner = scanner;
        this.activeScanner.setLoggedInEmployee(this);
    }

    public void logOut(){
        activeScanner = null;
    }

    public void scanProduct(Product product){
        if(activeScanner == null){
            throw new IllegalArgumentException("Employee is not logged into any scanner");
        }
        if(activeScanner.hasActiveOrder()){
            activeScanner.scanProduct(product);
        } else {
            activeScanner.startNewOrder();
            activeScanner.scanProduct(product);
        }
    }

    public ProductScanner getActiveScanner(){
        return activeScanner;
    }

    public void finalizeOrder(PaymentCard card){
        activeScanner.finalizeOrder(card);
    }
}
