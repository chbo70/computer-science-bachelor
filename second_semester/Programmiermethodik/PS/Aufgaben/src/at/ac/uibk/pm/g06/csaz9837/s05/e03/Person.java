package at.ac.uibk.pm.g06.csaz9837.s05.e03;

public class Person {

    private String firstname;
    private String lastname;
    private Address address;
    private String dateOfBirth;
    private String[] hobbies;

    public Person(String firstname, String lastname, Address address, String dateOfBirth, String[] hobbies) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.hobbies = hobbies;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Name: \t  " + this.firstname + " " + this.lastname + "\n");
        output.append("Address: " + this.address + "\n");
        output.append("Birthday: " + this.dateOfBirth + "\n");
        output.append("Hobbies:  ");
        for (int i = 0; i < this.hobbies.length; i++) {
            output.append(this.hobbies[i]);
            if (i == this.hobbies.length - 1){
                break;
            } else{
                output.append(", ");
            }
        }
        return output.toString();
    }

}
