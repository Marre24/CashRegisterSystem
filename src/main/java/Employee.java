public class Employee extends Person{

    private ProductScanner productScanner;

    public Employee(String firstName, String surName, String socialSecurityNr, String phoneNumber, String emailAdress, String homeAdress) {
        super(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
    }

    @Override
    public String toString() {
        return getFullName();
    }

    public void logIntoScanner(){
        productScanner = new ProductScanner(this);
    }

    public void scanProduct(Product product){
        if(productScanner == null){
            throw new IllegalArgumentException("Employee is not logged into any scanner");
        }
        if(productScanner.hasActiveOrder()){
            productScanner.scanProduct(product);
        } else {
            productScanner.startNewOrder();
            productScanner.scanProduct(product);
        }
    }

    public ProductScanner getScanner(){
        return productScanner;
    }

    public void finalizeOrder(){
        productScanner.finalizeOrder();
    }
}
