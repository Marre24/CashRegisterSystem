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

    static InputReader input = new InputReader();

    public static void main(String[] args) {
        boolean isActive = true;
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
            case FINALIZE_ORDER:
                finalizeOrder();
            case LOGG_INTO_SCANNER:
                logIn();
            case LOGG_OUT_SCANNER:
                logOut();
            case EXIT_COMMAND:
                exit();
        }
    }

    private void createPerson() {
        String firstName = input.readLine("Write your firstname");
        if (firstName.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectName(firstName))
            createPerson();

        String surName = input.readLine("Write your surname");
        if (surName.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectName(surName))
            createPerson();

        String socialSecurityNr = input.readLine("Write your social security number");
        if (socialSecurityNr.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectSSN(socialSecurityNr))
            createPerson();

        String phoneNumber = input.readLine("Write your phone number");
        if (phoneNumber.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectPhoneNr(phoneNumber))
            createPerson();

        String emailAddress = input.readLine("Write your email address");
        if (emailAddress.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectEmailAddress(emailAddress))
            createPerson();

        String homeAddress = input.readLine("Write your home address");
        if (homeAddress.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectHomeAddress(homeAddress))
            createPerson();

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
        String firstName = input.readLine("Enter first name: ");
        if (firstName.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectName(firstName))
            createEmployee();

        String surName = input.readLine("Enter surname: ");
        if (surName.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectName(surName))
            createEmployee();

        String ssn = input.readLine("Enter SSN: ");
        if (ssn.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectSSN(ssn))
            createEmployee();

        String phoneNr = input.readLine("Enter phone number: ");
        if (phoneNr.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectPhoneNr(phoneNr))
            createEmployee();

        String emailAddress = input.readLine("Enter email address: ");
        if (emailAddress.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectEmailAddress(emailAddress))
            createEmployee();

        String homeAddress = input.readLine("Enter home address");
        if (homeAddress.toLowerCase() == "exit")
            return;
        if (!InputFormatter.isCorrectHomeAddress(homeAddress))
            createEmployee();

        Employee e = new Employee(firstName, surName, ssn, phoneNr, emailAddress, homeAddress);
        employees.add(e);
    }

    private void finalizeOrder(){
        if (activeEmployee == null){
            System.out.println("No active employee");
            return;
        }
        System.out.println(activeEmployee.getScanner().getActiveOrder().toString());
        String yn = input.readLine("Are you sure you want to finalize order? (y/n)");
        if (yn.toLowerCase() == "n"){
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
        if (findPerson() instanceof Employee employee && activeEmployee == null)
            activeEmployee = employee;
    }

    private void logOut() {
        activeEmployee = null;
    }

    private void exit(){
        System.exit(0);
    }

    private Person findPerson() {
        String socialSecurityNr = input.readLine("Write your social security number");
        if (socialSecurityNr.toLowerCase() == "exit")
            return null;

        if (!InputFormatter.isCorrectSSN(socialSecurityNr)){
            System.out.println("Social security number is not valid");
            findPerson();
        }
        for (Person person : persons)
            if (person.getSocialSecurityNr() == socialSecurityNr)
                return person;
        return null;
    }
}
