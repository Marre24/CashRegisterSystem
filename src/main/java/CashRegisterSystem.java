import java.util.ArrayList;

public class CashRegisterSystem {

    final static String CREATE_PERSON = "add person";
    final static String REMOVE_PERSON = "remove person";
    final static String REMOVE_MEMBERSHIP = "remove membership";
    final static String CREATE_EMPLOYEE = "create employee";
    final static String ADD_MEMBERSHIP = "add membership";
    final static String SCAN_PRODUCT = "scan product";
    final static String FINALIZE_ORDER = "finalize order";
    final static String LOGG_INTO_SCANNER = "logg into scanner";
    final static String LOGG_OUT_SCANNER = "logg out";
    final static String EXIT_COMMAND = "exit";
    final static String WELCOME_MESSAGE = "Welcome to CashRegisterSystem these are the available commands:\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s".formatted(
            CREATE_PERSON,
            REMOVE_PERSON,
            REMOVE_MEMBERSHIP,
            ADD_MEMBERSHIP,
            CREATE_EMPLOYEE,
            SCAN_PRODUCT,
            FINALIZE_ORDER,
            LOGG_INTO_SCANNER,
            LOGG_OUT_SCANNER,
            EXIT_COMMAND);

    final ArrayList<Person> persons = new ArrayList<>();
    final ArrayList<Employee> employees = new ArrayList<>();
    final ArrayList<Product> products = new ArrayList<>();
    final ArrayList<PaymentCard> paymentCards = new ArrayList<>();

    Employee activeEmployee = null;
    private final ProductScanner productScanner = new ProductScanner();

    static boolean isActive = true;
    static final InputReader INPUT = new InputReader();

    public static void main(String[] args) {
        CashRegisterSystem cashRegisterSystem = new CashRegisterSystem();
        System.out.println(WELCOME_MESSAGE);
        while (isActive){
            cashRegisterSystem.handleCommand();
        }
    }

    private void handleCommand() {
        switch (INPUT.readLine("write your command").toLowerCase()){
            case CREATE_PERSON:
                createPerson();
                break;
            case REMOVE_PERSON:
                removePerson();
                break;
            case ADD_MEMBERSHIP:
                addMembership();
                break;
            case REMOVE_MEMBERSHIP:
                removeMembership();
                break;
            case CREATE_EMPLOYEE:
                createEmployee();
                break;
            case SCAN_PRODUCT:
                scanProduct();
                break;
            case FINALIZE_ORDER:
                finalizeOrder();
                break;
            case LOGG_INTO_SCANNER:
                logIn();
                break;
            case LOGG_OUT_SCANNER:
                logOut();
                break;
            case EXIT_COMMAND:
                exit();
                break;
            default:
                System.out.println("Could not recognize the command");
                break;
        }
    }

    private void createPerson() {

        String firstName = getFirstOrSurName("Firstname");
        String surName = getFirstOrSurName("Surname");
        String socialSecurityNr = getSocialSecurityNumber();
        String phoneNumber = getPhoneNumber();
        String emailAddress = getEmailAddress();
        String homeAddress = getHomeAddress();

        Person p = new Person(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);


        if (persons.contains(p)){
            System.out.println("Person already exists");
            return;
        }

        for (Employee employee : employees)
            if (employee.equals(p)){
                System.out.println("Employee already exists");
                return;
            }

        persons.add(p);
    }

    private void removePerson() {
        Person p = findPerson();

        if (p == null){
            System.out.println("Person not found");
            return;
        }

        persons.remove(p);
    }

    private void addMembership() {
        Person personToAddMembershipTo = findPerson();
        if (personToAddMembershipTo == null){
            System.out.println("Person not found");
            return;
        }

        if(!personToAddMembershipTo.isMember()){
            Membership membership = new Membership(personToAddMembershipTo);
            personToAddMembershipTo.addMembership(membership);
        }
    }

    private void removeMembership() {
        Person person = findPerson();
        if (person == null){
            System.out.println("Person not found");
            return;
        }

        if(person.isMember()){
            person.removeMembership();
        }
    }

    private void createEmployee(){
        String firstName = getFirstOrSurName("Firstname");
        String surName = getFirstOrSurName("Surname");
        String socialSecurityNr = getSocialSecurityNumber();
        String phoneNumber = getPhoneNumber();
        String emailAddress = getEmailAddress();
        String homeAddress = getHomeAddress();

        Employee e = new Employee(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress);
        if (persons.contains(e) || employees.contains(e)){
            System.out.println("Person already exists");
            return;
        }
        employees.add(e);
        System.out.println("The employee " + e.getFullName() + " was created");
    }

    private void finalizeOrder(){
        if (activeEmployee == null){
            System.out.println("No active employee");
            return;
        }
        System.out.println(activeEmployee.getActiveScanner().getActiveOrder().toString());
        String yn = INPUT.readLine("Are you sure you want to finalize order? (y/n)");
        if (yn.equalsIgnoreCase("n")){
            return;
        }
        String cardPan = INPUT.readLine("Write card PAN");
        PaymentCard card = null;
        for (PaymentCard paymentCard : paymentCards){
            if (paymentCard.getPan().equals(cardPan)) {
                card = paymentCard;
                break;
            }
        }
        if (card == null){
            System.out.println("Payment card not found");
            return;
        }
        activeEmployee.finalizeOrder(card);
    }

    private void scanProduct(){
        if (activeEmployee == null){
            System.out.println("No active employee");
            return;
        }
        String productName = INPUT.readLine("Write name of product");
        Product scannedProduct = null;
        for (Product product : products){
            if(product.getName().equals(productName)){
                scannedProduct = product;
                break;
            }
        }
        if (scannedProduct == null){
            System.out.println("Product not found");
            return;
        }
        if (scannedProduct.isPricedByWeight()){
            Long weight = INPUT.readLong("Enter weight of product (grams)");
            activeEmployee.scanProduct(scannedProduct, weight);
            System.out.println(weight + " grams of " + scannedProduct.getName() + " was added to order");
        }
        activeEmployee.scanProduct(scannedProduct);
        System.out.println(scannedProduct.getName() + " was added to order");
    }

    private void logIn() {
        Employee employee = findEmployee();

        if (employee == null){
            System.out.println("Could not find employee");
            return;
        }

        if (employees.contains(employee) && activeEmployee == null){
            activeEmployee = employee;
            activeEmployee.logIntoScanner(productScanner);
            System.out.println(activeEmployee.getFullName() + " logged in");
        }
    }

    private void logOut() {
        Employee employee = activeEmployee;
        activeEmployee = null;
        System.out.println(employee.getFullName() + " logged out");
    }

    private void exit(){
        isActive = false;
    }

    private String getFirstOrSurName(String nameType) {
        String name = null;
        while (name == null){
            String firstName = INPUT.readLine("Write your " + nameType);
            if (firstName.equalsIgnoreCase("exit"))
                return null;
            if (InputFormatter.isCorrectName(firstName))
                name = firstName;
        }
        return name;
    }

    private String getSocialSecurityNumber() {
        String ssn = null;
        while (ssn == null){
            String tempSsn = INPUT.readLine("Write your social security number");
            if (tempSsn.equalsIgnoreCase("exit"))
                return null;
            if (InputFormatter.isCorrectSSN(tempSsn))
                ssn = tempSsn;
        }
        return ssn;
    }

    private String getPhoneNumber() {
        String phoneNumber = null;
        while (phoneNumber == null){
            String temp = INPUT.readLine("Write your phone number");
            if (temp.equalsIgnoreCase("exit"))
                return null;
            if (InputFormatter.isCorrectPhoneNr(temp))
                phoneNumber = temp;
        }
        return phoneNumber;
    }

    private String getEmailAddress() {
        String email = null;
        while (email == null){
            String temp = INPUT.readLine("Write your email address");
            if (temp.equalsIgnoreCase("exit"))
                return null;
            if (InputFormatter.isCorrectEmailAddress(temp))
                email = temp;
        }
        return email;
    }

    private String getHomeAddress() {
        String homeAddress = null;
        while (homeAddress == null){
            String temp = INPUT.readLine("Write your home address");
            if (temp.equalsIgnoreCase("exit"))
                return null;
            if (InputFormatter.isCorrectHomeAddress(temp))
                homeAddress = temp;
        }
        return homeAddress;
    }

    private Person findPerson() {
        String socialSecurityNr = getSocialSecurityNumber();

        for (Person person : persons)
            if (InputFormatter.socialSecurityIsEqual(person.getSocialSecurityNr(), socialSecurityNr))
                return person;
        return null;
    }

    private Employee findEmployee() {
        String socialSecurityNr = getSocialSecurityNumber();

        for (Employee employee : employees)
            if (InputFormatter.socialSecurityIsEqual(employee.getSocialSecurityNr(), socialSecurityNr))
                return employee;

        System.out.println("No employee with that social security number exists");
        return null;
    }
}
