package at.ac.uibk.pm.g06.csaz9837.s02.e02;
import java.util.ArrayList;

public class BankingSystem {
    private ArrayList <BankAccount>listBA;
    private ArrayList <Transaction> listTA;

    public BankingSystem(ArrayList<BankAccount> listBA, ArrayList<Transaction> listTA) {
        this.listBA = listBA;
        this.listTA = listTA;
    }
    public void createNewBankAccount(BankAccount Account){
        listBA.add(Account);
    }

    public void Transfer(Transaction transaction){
        if (transaction.getSourceBankAccount().withdrawChecker(transaction.getTransactionAmount())) {
            transaction.getSourceBankAccount().withDrawer(transaction.getTransactionAmount());
            transaction.getTargetBankAccount().setBalance(transaction.getTransactionAmount());
            transaction.setCurrentStatus(Transaction.status.success);
        }
        else {
            transaction.setCurrentStatus(Transaction.status.failure);
        }
        listTA.add(transaction);
    }
    public void printTransactions(){
        for (Transaction transaction: listTA) {
            System.out.print(transaction + "\n");
        }
    }
    public void printBankaccounts(){
        for (BankAccount bankaccount: listBA) {
            System.out.print("\n" + bankaccount);
        }
    }

    @Override
    public String toString() {
        return "BankingSystem{" +
                "listTA=" + listTA +
                '}';
    }
}
