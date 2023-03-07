package at.ac.uibk.pm.g06.csaz9837.s02.e02;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<BankAccount> managedBankAccounts = new ArrayList<BankAccount>();
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        BankingSystem bankingSystem = new BankingSystem(managedBankAccounts, transactionList);

        Customer darthVader = new Customer("Vader", "Darth", Customer.creditRating.low);
        Customer padmeAmidala = new Customer("Amidala", "Padme", Customer.creditRating.low);
        BankAccount bankAccountDV = new BankAccount("DEATHSTAR 1233 3242 3123 4200", darthVader, 30000);
        BankAccount bankAccountPA = new BankAccount("NABOO 0071 2345 2344 6900", padmeAmidala, 500000);

        bankingSystem.createNewBankAccount(bankAccountDV);
        bankingSystem.createNewBankAccount(bankAccountPA);

        Transaction transaction = new Transaction("007", bankAccountDV, bankAccountPA, 20000, Transaction.status.success);
        Transaction transaction2 = new Transaction("008", bankAccountDV, bankAccountPA, 20000, Transaction.status.success);
        Transaction transaction3 = new Transaction("009", bankAccountDV, bankAccountPA, 1000000, Transaction.status.success);

        bankAccountDV.printBalance();
        bankingSystem.Transfer(transaction);
        bankAccountDV.printBalance();
        bankingSystem.Transfer(transaction2);
        bankAccountDV.printBalance();
        bankingSystem.Transfer(transaction3);

        bankingSystem.printTransactions();
        bankingSystem.printBankaccounts();

    }
}
