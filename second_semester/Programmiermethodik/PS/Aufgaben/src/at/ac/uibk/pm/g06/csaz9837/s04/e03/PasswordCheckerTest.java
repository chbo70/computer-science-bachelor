package at.ac.uibk.pm.g06.csaz9837.s04.e03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {
    //Exceptions
    @Test
    void invalidBirthdayTest(){
        Birthday birthday = new Birthday(0,0,000);
        User user1 = new User("Naruto","Uzumaki",birthday, "fhksf87&A+S");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    user1.getBirthday().getDayOfBirth();
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    user1.getBirthday().getMonthOfBirth();
                });
        assertThrows(IllegalArgumentException.class,
                () -> {
                    user1.getBirthday().getYearOfBirth();
                });
    }
    @Test
    void invalidFirstName(){//invalid first name if name is empty or contains special characters as same as numbers
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("*dsd", "Hiddleston", birthday,"dasdaf3!");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    user1.getFirstName();
                });
    }
    @Test
    void invalidLastName(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Jupit*er", birthday,"djAsdh&-");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    user1.getLastName();
                });
    }
    @Test
    void isThePasswordEmpty(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Tom", "Hiddleston", birthday,"");
        assertThrows(IllegalArgumentException.class,
                () -> {
                    user1.getPassword();
                });
    }
    @Test
    void invalidCharacters(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Tom", "Hiddleston", birthday,"dadAS765)!");
        PasswordChecker checker = new PasswordChecker(user1);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    checker.doesContainTwoChar(user1.getPassword());
                });
    }
    //Rules of Password
    @Test
    void isPasswordLongEnough(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Hardy", birthday,"djAsdAdfa3454h&-");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(user1.getPassword().length() >= 16, checker.isLongEnough(user1.getPassword()));
    }
    @Test
    void doesPasswordContainAtLeastTwoChar(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Vader", birthday,"djAsdsd32h&-");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(user1.getPassword().replaceAll("[^0-9]","").length() >= 2, checker.doesContainTwoNumber(user1.getPassword()));
    }
    @Test
    void doesPasswordContainAtLeastThreeLowerCase(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Oden", birthday,"djAsdsd32h&-");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(user1.getPassword().replaceAll("[^a-z]","").length() >= 3, checker.doesContainThreeLowerCase(user1.getPassword()));
    }
    @Test
    void doesPasswordContainAtLeastTwoUpperCase(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Baden", birthday,"djAsdsFd32h&-");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(user1.getPassword().replaceAll("[^A-Z]","").length() >= 2, checker.doesContainTwoUpperCase(user1.getPassword()));
    }
    @Test
    void doesPasswordContainAtLeastTwoSpecialChar(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Rabe", birthday,"djAsdsd32h&-");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(user1.getPassword().replaceAll("[a-zA-Z0-9\\[?!%&=+\\]-]","").length() >= 2, checker.doesContainTwoChar(user1.getPassword()));
    }
    @Test
    void doesPasswordContainUserInformation(){ //true if no user Information is used as password
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Marley", birthday,"BobMarley241980");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(!user1.getPassword().contains(user1.getFirstName()), checker.doesContainUser(user1.getPassword()));
        assertEquals(!user1.getPassword().contains(user1.getLastName()), checker.doesContainUser(user1.getPassword()));
        assertEquals(!user1.getPassword().contains(user1.getBirthday().getDayOfBirth() + ""), checker.doesContainUser(user1.getPassword()));
        assertEquals(!user1.getPassword().contains(user1.getBirthday().getMonthOfBirth() + ""), checker.doesContainUser(user1.getPassword()));
        assertEquals(!user1.getPassword().contains(user1.getBirthday().getYearOfBirth() + ""), checker.doesContainUser(user1.getPassword()));
    }
    //Password Strength
    @Test
    void tooWeakTest(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Marley", birthday,"a2Bob");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(PasswordStrength.TOO_WEAK, checker.checkPassword(user1, user1.getPassword()));
    }
    @Test
    void weakTest(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Marley", birthday,"a2FBob");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(PasswordStrength.WEAK, checker.checkPassword(user1, user1.getPassword()));
    }
    @Test
    void mediumTest(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Marley", birthday,"a233FBob");
        User user2 = new User("Bob", "Marley", birthday,"aMarley33FBo]-kkkkkkkkkkkb");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(PasswordStrength.MEDIUM, checker.checkPassword(user1, user1.getPassword()));
        //ExtraBoundarie Check
        assertEquals(PasswordStrength.MEDIUM, checker.checkPassword(user2,user2.getPassword()));
    }
    @Test
    void strongTest(){
        Birthday birthday = new Birthday(24,2,1980);
        User user1 = new User("Bob", "Marley", birthday,"a433-FF]]]ss6876s]]]]");
        PasswordChecker checker = new PasswordChecker(user1);
        assertEquals(PasswordStrength.STRONG, checker.checkPassword(user1, user1.getPassword()));
    }

}