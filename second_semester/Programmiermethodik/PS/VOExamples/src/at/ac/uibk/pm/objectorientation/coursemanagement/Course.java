package at.ac.uibk.pm.objectorientation.coursemanagement;

/**
 * Class provides means to administer courses and their participants.
 */
public class Course {

    private final String name;
    private final Person leader;
    private final Person[] participants;
    private int numberParticipants;

    /**
     * Constructs a new course with the given information.
     *
     * @param name     the name of the course.
     * @param leader   the person leading the course.
     * @param capacity the number of persons allowed to register to this course.
     */
    public Course(String name, Person leader, int capacity) {
        this.name = name;
        this.leader = leader;
        this.participants = new Person[capacity];
        this.numberParticipants = 0;
    }

    /**
     * Method registered a given person for the course.
     *
     * @param person the person to be registered.
     * @return true if registration was successful (capacity not reached), else false.
     */
    public boolean register(Person person) {
        if (this.numberParticipants >= this.participants.length) {
            return false;
        }
        this.participants[this.numberParticipants] = person;
        ++this.numberParticipants;
        return true;
    }

    /**
     * Method returns a list of all email addresses of all participants of the course.
     *
     * @return list of all participant's email addresses.
     */
    public String[] getEmailAddresses() {
        String[] emailAddresses = new String[this.numberParticipants];
        int count = 0;
        for (int i = 0; i < this.numberParticipants; ++i) {
            Person person = this.participants[i];
            ContactInformation contactInformation = person.getContactInformation();
            String email = contactInformation.getEmailAddress();
            if (email != null) {
                emailAddresses[count] = email;
                ++count;
            } else {
                System.err.println("Warning, participant " + person + " has no email address!");
            }
        }
        return java.util.Arrays.copyOf(emailAddresses, count);
    }

    /**
     * Returns name of the current course object.
     *
     * @return name of the course.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns name of the leader of the current course object.
     *
     * @return name of the leader of the course.
     */
    public Person getLeader() {
        return this.leader;
    }
}