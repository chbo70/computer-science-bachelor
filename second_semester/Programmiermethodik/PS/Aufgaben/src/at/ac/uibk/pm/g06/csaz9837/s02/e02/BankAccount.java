package at.ac.uibk.pm.g06.csaz9837.s02.e02;

public class BankAccount {
    private String IBAN;
    private Customer owner;
    private int balance;


    public BankAccount(String IBAN, Customer owner, int balance) {
        this.IBAN = IBAN;
        this.owner = owner;
        this.balance = balance;
    }

    //low = -10000 (at most)
    //medium = -50000 (at most)
    //high = -100000 (at most)

    public boolean withdrawChecker(int withdraw){
        int currentBalance = this.balance - withdraw;
        switch (owner.getCustomerRating()){
            case low:
                if (currentBalance < -10000){
                    return false;
                }
                break;
            case medium:
                if(currentBalance < -50000){
                    return false;
                }
                break;
            case high:
                if (currentBalance < -100000){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = this.balance + balance;
    }

    public void withDrawer(int withdraw) {
        this.balance = this.balance - withdraw;

    }
    public void printBalance(){
        System.out.println(balance);
    }
    @Override
    public String toString() {
        return IBAN;
    }
}
