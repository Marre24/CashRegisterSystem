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

    ArrayList<Person> persons = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<PaymentCard> paymentCards = new ArrayList<>();
    Employee activeEmployee = null;

    static boolean isActive = true;
    static InputReader input = new InputReader();

    public static void main(String[] args) {
        CashRegisterSystem cashRegisterSystem = new CashRegisterSystem();
        System.out.println(WELCOME_MESSAGE);
        while (isActive){
            cashRegisterSystem.handleCommand();
        }
    }

    private void handleCommand() {
        switch (input.readLine("write your command").toLowerCase()){
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

        persons.add(new Person(firstName, surName, socialSecurityNr, phoneNumber, emailAddress, homeAddress));
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
        employees.add(e);
    }

    private void finalizeOrder(){
        if (activeEmployee == null){
            System.out.println("No active employee");
            return;
        }
        System.out.println(activeEmployee.getScanner().getActiveOrder().toString());
        String yn = input.readLine("Are you sure you want to finalize order? (y/n)");
        if (yn.equalsIgnoreCase("n")){
            return;
        }
        String cardPan = input.readLine("Write card PAN");
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
        String productName = input.readLine("Write name of product");
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
        activeEmployee.scanProduct(scannedProduct);
    }

    private void logIn() {
        if (findPerson() instanceof Employee employee && activeEmployee == null){
            activeEmployee = employee;
            activeEmployee.logIntoScanner();
        }
    }

    private void logOut() {
        activeEmployee = null;
    }

    private void exit(){
        isActive = false;
    }

    private String getFirstOrSurName(String nameType) {
        String name = null;
        while (name == null){
            String firstName = input.readLine("Write your " + nameType);
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
            String tempSsn = input.readLine("Write your social security number");
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
            String temp = input.readLine("Write your phone number");
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
            String temp = input.readLine("Write your email address");
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
            String temp = input.readLine("Write your home address");
            if (temp.equalsIgnoreCase("exit"))
                return null;
            if (InputFormatter.isCorrectHomeAddress(temp))
                homeAddress = temp;
        }
        return homeAddress;
    }

    private Person findPerson() {
        String socialSecurityNr = input.readLine("Write your social security number");
        if (socialSecurityNr.equalsIgnoreCase("exit"))
            return null;

        if (!InputFormatter.isCorrectSSN(socialSecurityNr)){
            System.out.println("Social security number is not valid");
            findPerson();
        }
        for (Person person : persons)
            if (InputFormatter.socialSecurityIsEqual(person.getSocialSecurityNr(), socialSecurityNr))
                return person;
        return null;
    }
}
