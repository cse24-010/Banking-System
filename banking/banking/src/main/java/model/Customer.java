package main.java.model;

public class Customer {
    private String customerId;
    private String firstName;
    private String surname;
    private String address;
    private String employmentInfo;

    public Customer(String customerId, String firstName, String surname, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
    }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public String getEmploymentInfo() { return employmentInfo; }

    public void setEmploymentInfo(String employmentInfo) {
        this.employmentInfo = employmentInfo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}