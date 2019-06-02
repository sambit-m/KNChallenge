package m.sambit.kisannetworkchallenge.model;

public class Contacts {

    private String firstName;
    private String lastName;
    private String phone;

    public Contacts(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName+" "+lastName;
    }

    public String getPhone() {
        return phone;
    }
}
