public class Employee extends Person{

    private ProductScanner productScanner = new ProductScanner();

    public Employee(String firstName, String surName, String socialSecurityNr, String phoneNumber, String emailAdress, String homeAdress) {
        super(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
    }

    @Override
    public String toString() {
        return getFullName();
    }


    public void scanProduct(Product product){
        if(productScanner.orderExists()){
            productScanner.scanProduct(product);
        } else {
            Order o = new Order(this);
            productScanner.setActiveOrder(o);
            productScanner.scanProduct(product);
        }
    }

    public ProductScanner getScanner(){
        return productScanner;
    }
}
