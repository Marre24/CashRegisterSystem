public class Employee extends Person{

    private ProductScanner productScanner = new ProductScanner();

    public Employee(String firstName, String surName, String socialSecurityNr, String phoneNumber, String emailAdress, String homeAdress) {
        super(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
    }

    @Override
    public String toString() {
        return getFullName();
    }


    public void scanProduct(ProductType productType){
        if(productScanner.hasActiveOrder()){
            productScanner.scanProduct(productType);
        } else {
            Order o = new Order(this);
            productScanner.setActiveOrder(o);
            productScanner.scanProduct(productType);
        }
    }

    public ProductScanner getScanner(){
        return productScanner;
    }
}
