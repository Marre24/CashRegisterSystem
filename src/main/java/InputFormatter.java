

abstract public class InputFormatter {

    static final int EXPECTED_AMOUNT_OF_PERSON_ARGUMENTS = 6;

    // String firstName, String surName, String socialSecurityNr, String phoneNr, String emailAddress, String homeAddress
    // space separated values in personInformation
    public static boolean isCorrectFormatPerson(String personInformation){
        if(hasRightAmountOfArgsForAddPerson(personInformation))
            return false;

        String[] args = personInformation.split(" ");

        String firstName = args[0];
        String surName = args[1];
        String socialSecurityNr = args[2];
        String phoneNr = args[3];
        String emailAddress = args[4];
        String homeAddress = args[5];
        return isCorrectName(firstName) && isCorrectName(surName) && isCorrectSSN(socialSecurityNr) && isCorrectPhoneNr(phoneNr) && isCorrectEmailAddress(emailAddress) && isCorrectHomeAddress(homeAddress);

    }

    public static boolean hasRightAmountOfArgsForAddPerson(String args){
        return args.split(" ").length == EXPECTED_AMOUNT_OF_PERSON_ARGUMENTS;
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
        String regex = "^0((8-[0-9]{6,8})|(7[0-9]-[0-9]{7})|([0-69][0-9]-[0-9]{5,7})|([0-69][0-9]{2}-[0-9]{5,6}))$";
        phoneNr = phoneNr.replaceAll(" ", "");
        return phoneNr.matches(regex);
    }

    static boolean isCorrectEmailAddress(String emailAddress) {
        String regex = "^[0-9a-zA-Z]+(.[0-9a-zA-Z]+)?@[a-zA-Z]+.com$";
        return emailAddress.matches(regex);
    }

    static boolean isCorrectHomeAddress(String homeAddress) {
        return homeAddress.matches("[a-zA-Z]+ [0-9]+");
    }

    static String formatAddress(String homeAddress){
        if(homeAddress == null || homeAddress.isEmpty()){
            return homeAddress;
        }
        return homeAddress.substring(0,1).toUpperCase() + homeAddress.substring(1).toLowerCase();
    }

    public static boolean socialSecurityIsEqual(String socialSecurityNr, String socialSecurityNr1) {
        return normalize(socialSecurityNr) == normalize(socialSecurityNr1);
    }

    private static int normalize(String socialSecurityNr) {
        socialSecurityNr = socialSecurityNr.replaceAll("-", "");

        if (socialSecurityNr.length() == 12)
            socialSecurityNr = socialSecurityNr.substring(2,12);

        return Integer.parseInt(socialSecurityNr);
    }
}
