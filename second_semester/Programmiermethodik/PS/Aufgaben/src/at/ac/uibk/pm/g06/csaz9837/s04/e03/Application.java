package at.ac.uibk.pm.g06.csaz9837.s04.e03;

/*
* It must be at least 16 characters long. -
• It must contain at least two numbers. +
• It must contain at least three lowercase characters.
• It must contain at least two uppercase characters.
• It must contain at least two of the following characters: ?,!,%,&,=,[,],+,-. If a illegal character is entered
  an Exception is thrown.
• It cannot contain the first name nor the last name nor the day, month or year of birth of the user
* ][]a3bscfkjsdfhk1
*/
public class Application {
    public static void main(String[] args) {
        try {
            Birthday birthday = new Birthday(20, 04, 1970);
            User user1 = new User("Jack", "Sparrow", birthday, "abcDE[0dasdasdas378");
            PasswordChecker checker = new PasswordChecker(user1);
            System.out.println(checker.checkPassword(user1, user1.getPassword()));
        } catch (NullPointerException e) {
            System.out.println("Invalid password type null is not allowed!");
            //e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Character!");
            //e.printStackTrace();
        }
    }
}
