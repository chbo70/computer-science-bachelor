package at.ac.uibk.pm.g06.csaz9837.s02.e02;


public class Transaction {
    private String transactionId;
    private BankAccount sourceBankAccount;
    private BankAccount targetBankAccount;
    private int transactionAmount;
    enum status {failure, success};
    private status currentStatus;

    public Transaction(String transactionId, BankAccount sourceBankAccount, BankAccount targetBankAccount, int transactionAmount, status currentStatus) {
        this.transactionId = transactionId;
        this.sourceBankAccount = sourceBankAccount;
        this.targetBankAccount = targetBankAccount;
        this.transactionAmount = transactionAmount;
        this.currentStatus = currentStatus;
    }

    public void setCurrentStatus(status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public BankAccount getSourceBankAccount() {
        return sourceBankAccount;
    }

    public BankAccount getTargetBankAccount() {
        return targetBankAccount;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public status getCurrentStatus() {
        return currentStatus;
    }

    @Override
    public String toString() {
        return "\nTransaction "+ transactionId + " :\n" +
                "   IBAN source-account = " + sourceBankAccount +
                "| IBAN target-account = " + targetBankAccount +
                "| transaction-amount = " + transactionAmount + " cents" +
                "| transaction-status = " + currentStatus ;
    }
}
