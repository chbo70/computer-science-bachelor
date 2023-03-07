package at.ac.uibk.pm.g06.csaz9837.s04.e03;

/*
* It must be at least 16 characters long.
• It must contain at least two numbers.
• It must contain at least three lowercase characters.
• It must contain at least two uppercase characters.
• It must contain at least two of the following characters: ?,!,%,&,=,[,],+,-. If a illegal character is entered
  an Exception is thrown.
• It cannot contain the first name nor the last name nor the day, month or year of birth of the user
*/
public class PasswordChecker {
    private final User user;

    public PasswordChecker(User user) {
        this.user = user;
    }

    public PasswordStrength checkPassword(User user, String password) throws IllegalArgumentException {
        try {
            if (strengthCounter(password) < 2) {
                return PasswordStrength.TOO_WEAK;
            } else if (strengthCounter(password) == 2) {
                return PasswordStrength.WEAK;
            } else if (strengthCounter(password) > 2 && strengthCounter(password) < 5) {
                return PasswordStrength.MEDIUM;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal Character in password!");
            e.printStackTrace();
        }
        return PasswordStrength.STRONG;
    }

    public int strengthCounter(String password) {
        int counter = 0;
        if (isLongEnough(password)) {
            ++counter;
        }
        if (doesContainTwoNumber(password)) {
            ++counter;
        }
        if (doesContainThreeLowerCase(password)) {
            ++counter;
        }
        if (doesContainTwoUpperCase(password)) {
            ++counter;
        }
        if (doesContainTwoChar(password)) {
            ++counter;
        }
        if (doesContainUser(password)) {
            ++counter;
        }
        return counter;
    }

    public boolean isLongEnough(String password) {
        return password.length() >= 16;
    }

    public boolean doesContainTwoNumber(String password) {
        String changedPassword = password.replaceAll("[^0-9]", "");
        return changedPassword.length() >= 2;
    }

    public boolean doesContainThreeLowerCase(String password) {
        String changedPassword = password.replaceAll("[^a-z]", "");
        return changedPassword.length() >= 3;
    }

    public boolean doesContainTwoUpperCase(String password) {
        String changedPassword = password.replaceAll("[^A-Z]", "");
        return changedPassword.length() >= 2;
    }

    public boolean doesContainTwoChar(String password) {
        String changedPassword = password.replaceAll("[^\\[?!%&=+\\]-]", "");
        if (password.replaceAll("[a-zA-Z0-9\\[?!%&=+\\]-]", "").length() > 0) {
            throw new IllegalArgumentException();
        }
        return changedPassword.length() >= 3;
    }

    public boolean doesContainUser(String password) {
        if (password.contains(user.getFirstName())) {
            return false;
        }
        if (password.contains(user.getLastName())) {
            return false;
        }
        if (password.contains(user.getBirthday().getDayOfBirth() + "")) {
            return false;
        }
        if (password.contains(user.getBirthday().getMonthOfBirth() + "")) {
            return false;
        }
        if (password.contains(user.getBirthday().getYearOfBirth() + "")) {
            return false;
        }
        return true;
    }


}
