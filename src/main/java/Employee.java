public class Employee extends Person{

    private String title = "NO TITLE";
    private int monthlySalary = 0;

    public Employee(String firstName, String surName, String socialSecurityNr, String phoneNumber, String emailAdress, String homeAdress) {
        super(firstName, surName, socialSecurityNr, phoneNumber, emailAdress, homeAdress);
    }

    @Override
    public String toString() {
        return getFullName();
    }

    public void setMonthlySalary(int monthlySalary) {
        if (monthlySalary < 0)
            throw new IllegalArgumentException("Monthly salary should not be set to a negative number");
        this.monthlySalary = monthlySalary;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
