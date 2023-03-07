package at.ac.uibk.pm.objectorientation.coursemanagement;

/**
 * Class holds information about a person (including name and address).
 */

public class Person {

    private String firstName;
    private String lastName;
    private ContactInformation contactInformation;

    /**
     * Constructs a person using the given information.
     *
     * @param first              the person's first name.
     * @param last               the person's last name.
     * @param contactInformation the person's address.
     */
    public Person(String first, String last, ContactInformation contactInformation) {
        this.firstName = first;
        this.lastName = last;
        this.contactInformation = contactInformation;
    }

    /**
     * Returns the contact information of the person.
     *
     * @return contact information.
     */
    public ContactInformation getContactInformation() {
        return this.contactInformation;
    }

    /**
     * Sets the contact information of the person.
     *
     * @param contactInformation contact information.
     */
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Returns the first name of the person.
     *
     * @return first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the person.
     *
     * @return last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns a String representation of the person.
     *
     * @return String holding first and last name.
     */
    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}