package at.ac.uibk.pm.g06.csaz9837.s04.e03;

public class Birthday {
    private final int dayOfBirth;
    private final int monthOfBirth;
    private final int yearOfBirth;

    public Birthday(int dayOfBirth, int monthOfBirth, int yearOfBirth) {
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        if (dayOfBirth < 1 || monthOfBirth < 1 || yearOfBirth < 1) {
            throw new IllegalArgumentException();
        }
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
}
