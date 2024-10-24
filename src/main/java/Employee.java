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

    public void scanProduct(ProductType productType){
        if(productScanner == null){
            throw new IllegalArgumentException("Employee is not logged into any scanner");
        }
        if(productScanner.hasActiveOrder()){
            productScanner.scanProduct(productType);
        } else {
            productScanner.startNewOrder();
            productScanner.scanProduct(productType);
        }
    }

    public ProductScanner getScanner(){
        return productScanner;
    }
}
