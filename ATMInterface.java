import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Class to represent the user's bank account
class BankAccount {
    private double balance;
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance >= 0 ? initialBalance : 0; // Ensure non-negative initial balance
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

// Class to represent the ATM Machine
class ATMMachine {
    private BankAccount account;
    private Scanner scanner;
    private List<String> transactionHistory;

    public ATMMachine(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
        this.transactionHistory = new ArrayList<>();
    }

    // Display the ATM menu
    private void displayMenu() {
        System.out.printf("\n=== ATM Menu (Account: %s, Balance: $%.2f) ===%n",
                account.getAccountNumber(), account.getBalance());
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transaction History");
        System.out.println("5. Exit");
        System.out.print("Choose an option (1-5): ");
    }

    // Main method to run the ATM interface
    public void start() {
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    deposit();
                    break;
                case "3":
                    withdraw();
                    break;
                case "4":
                    showTransactionHistory();
                    break;
                case "5":
                    System.out.println("Thank you for using the ATM. ComeAgain!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        }
    }

    // Check balance method
    private void checkBalance() {
        double balance = account.getBalance();
        System.out.printf("Current balance: $%.2f%n", balance);
        transactionHistory.add(String.format("Checked balance: $%.2f", balance));
    }

    // Deposit method
    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive value.");
                transactionHistory.add("Failed deposit: Invalid amount (non-positive)");
                return;
            }
            account.deposit(amount);
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f%n",
                    amount, account.getBalance());
            transactionHistory.add(String.format("Deposited: $%.2f, New balance: $%.2f",
                    amount, account.getBalance()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            transactionHistory.add("Failed deposit: Invalid input (non-numeric)");
        }
    }

    // Withdraw method
    private void withdraw() {
        System.out.printf("Current balance: $%.2f%n", account.getBalance());
        System.out.print("Enter amount to withdraw: $");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Invalid amount. Please enter a positive value.");
                transactionHistory.add("Failed withdrawal: Invalid amount (non-positive)");
                return;
            }
            if (account.withdraw(amount)) {
                System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f%n",
                        amount, account.getBalance());
                transactionHistory.add(String.format("Withdrew: $%.2f, New balance: $%.2f",
                        amount, account.getBalance()));
            } else {
                System.out.printf("Cannot withdraw $%.2f. Insufficient balance or invalid amount.%n",
                        amount);
                transactionHistory.add(String.format("Failed withdrawal: $%.2f (Insufficient funds, Balance: $%.2f)",
                        amount, account.getBalance()));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            transactionHistory.add("Failed withdrawal: Invalid input (non-numeric)");
        }
    }

    // Show transaction history using enhanced for loop
    private void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("\n=== Transaction History ===");
            int index = 1;
            for (String transaction : transactionHistory) {
                System.out.printf("%d. %s%n", index++, transaction);
            }
        }
    }
}

// Main class to test the ATM system
public class ATMInterface {
    public static void main(String[] args) {
        // Create a bank account with initial balance of $0 for testing
        BankAccount account = new BankAccount("123456789", 0.00);
        // Create ATM instance and start
        ATMMachine atm = new ATMMachine(account);
        System.out.println("Welcome to the ATM!");
        atm.start();
    }
}