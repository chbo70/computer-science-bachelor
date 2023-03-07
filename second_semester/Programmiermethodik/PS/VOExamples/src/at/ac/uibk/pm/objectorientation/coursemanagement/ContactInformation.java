package at.ac.uibk.pm.objectorientation.coursemanagement;

/**
 * Class provides contact information.
 */
public class ContactInformation {

    private Address address;
    private String phoneNumber;
    private String emailAddress;

    /**
     * Returns postal address.
     *
     * @return postal address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets postal address.
     *
     * @param address the postal address.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns email address.
     *
     * @return email address.
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the telephone number.
     *
     * @return the telephone number.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets telephone number.
     *
     * @param phoneNumber the telephone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}