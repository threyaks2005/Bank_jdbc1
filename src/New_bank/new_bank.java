package New_bank;
import java.util.*;

class Account {
    private String userName, accountType; // camelCase
    private int pin;
    private double balance;

    public Account(String userName, int pin, String accountType) { // PascalCase
        this.userName = userName;
        this.pin = pin;
        this.balance = 0;
        this.accountType = accountType;
    }

    public boolean authentication(int inputPin) {
        return this.pin == inputPin;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public double getInterest() {
        if (this.accountType.equalsIgnoreCase("savings")) {
            return this.balance * 0.1;
        }
        return this.balance * 0.15;
    }

    public String getDetails() {
        return "User Name: " + this.getUserName() + "\tBalance: " + this.getBalance() + "\tAccount Type: " + this.getAccountType();
    }

    public String deposit(double amount) {
        if (amount < 0) {
            return "Invalid amount";
        }
        this.balance += amount;
        return "Deposited successfully";
    }

    public String withdraw(double amount) {
        if (amount < 0) {
            return "Invalid Amount";
        }
        if (amount > this.balance) {
            return "Insufficient funds";
        }
        this.balance -= amount;
        return "Withdrawn successfully";
    }
}

class Banking {
    private LinkedHashMap<String, Account> accounts = new LinkedHashMap<>();
    private Account user = null;

    public String createAccount(String userName, int pin, String accountType) {
        if (accounts.containsKey(userName)) {
            return "Account already exists!";
        }

        Account a = new Account(userName, pin, accountType);
        accounts.put(userName, a);
        return "Account is successfully created";
    }

    public String login(String userName, int pin) {
        if (!accounts.containsKey(userName)) {
            return "Account doesn't exist";
        }
        if (accounts.get(userName).authentication(pin)) {
            user = accounts.get(userName);
            return "Logged in....";
        }
        return "Wrong pin";
    }

    public String logout() {
        if (user != null) {
            user = null;
            return "Logged out successfully";
        }
        return "Account is not yet logged in";
    }

    public String deposit(double amount) {
        if (user != null) {
            return user.deposit(amount);
        }
        return "Login First then Try";
    }

    public String withdraw(double amount) {
        if (user != null) {
            return user.withdraw(amount);
        }
        return "Login First then Try";
    }

    public String checkBalance() {
        if (user != null) {
            return "Balance: " + user.getBalance();
        }
        return "Login First then try";
    }
}

public class new_bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banking bank = new Banking();
        boolean run = true;

        while (run) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the Name, Pin, Account Type:");
                    String username = sc.next();
                    int pin = sc.nextInt();
                    String accountType = sc.next();
                    System.out.println(bank.createAccount(username, pin, accountType));
                    break;

                case 2:
                    System.out.println("Enter login credentials:");
                    username = sc.next();
                    int loginPin = sc.nextInt();
                    System.out.println(bank.login(username, loginPin));
                    break;

                case 3:
                    System.out.println(bank.logout());
                    break;

                case 4:
                    System.out.println("Enter amount to be deposited:");
                    double amount = sc.nextDouble();
                    System.out.println(bank.deposit(amount));
                    break;

                case 5:
                    System.out.println("Enter amount to withdraw:");
                    double withdrawAmount = sc.nextDouble();
                    System.out.println(bank.withdraw(withdrawAmount));
                    break;

                case 6:
                    System.out.println(bank.checkBalance());
                    break;

                case 7:
                    run = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.. Choose Again");
            }
        }
        sc.close();
    }
}
