

abstract public class InputFormatter {


    // String firstName, String surName, String socialSecurityNr, String phoneNr, String emailAddress, String homeAddress
    // space separated values in personInformation
    public static boolean isCorrectFormatPerson(String personInformation){
        String[] args = personInformation.split(" ");
        int expectedAmountOfArguments = 6;
        if(args.length != expectedAmountOfArguments){
            return false;
        }

        String firstName = args[0];
        String surName = args[1];
        String socialSecurityNr = args[2];
        String phoneNr = args[3];
        String emailAddress = args[4];
        String homeAddress = args[5];
        return isCorrectName(firstName) && isCorrectName(surName) && isCorrectSSN(socialSecurityNr) && isCorrectPhoneNr(phoneNr) && isCorrectEmailAddress(emailAddress) && isCorrectHomeAddress(homeAddress);

    }

    static boolean isCorrectName(String firstName) {
        String regex = "^[a-zA-Z]{1,30}$";
        return firstName.matches(regex);
    }

    static boolean isCorrectSSN(String ssn){
        String regex = "^([0-9]{4}|[0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])-[0-9]{4}$";
        return ssn.matches(regex);
    }

    static boolean isCorrectPhoneNr(String phoneNr) {
        return true;
    }

    static boolean isCorrectEmailAddress(String emailAddress) {
        return true;
    }

    static boolean isCorrectHomeAddress(String homeAddress) {
        return homeAddress.matches("[a-zA-Z]+ [0-9]+");
    }
}
