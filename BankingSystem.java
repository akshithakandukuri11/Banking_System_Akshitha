import java.util.ArrayList;
import java.util.Scanner;

// Bank Account class
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolder + ", Balance: " + balance;
    }
}

// Bank class
class Bank {
    private ArrayList<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void createAccount(String accountNumber, String accountHolder, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, accountHolder, initialBalance);
        accounts.add(account);
        System.out.println("Account successfully created for " + accountHolder);
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void viewAllAccounts() {
        System.out.println("All Accounts:");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }
}

// Main class
public class BankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter Account Holder Name: ");
                    scanner.nextLine(); // Consume newline
                    String accountHolder = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double initialBalance = scanner.nextDouble();
                    bank.createAccount(accountNumber, accountHolder, initialBalance);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    BankAccount account = bank.findAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter Amount to Deposit: ");
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    account = bank.findAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter Amount to Withdraw: ");
                        double amount = scanner.nextDouble();
                        account.withdraw(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    account = bank.findAccount(accountNumber);
                    if (account != null) {
                        System.out.println(account);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    bank.viewAllAccounts();
                    break;

                case 6:
                    System.out.println("Exiting the Banking System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
