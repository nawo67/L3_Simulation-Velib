package fil.l3.coo.User;

// Represents a user with a balance and an identifier.
public class User {
    private double balance;    // The balance of the user.
    private int id;             // The unique identifier of the user.
    private static int nextId = 1;  // The next available identifier for a user.

    /**
     * Constructs a user.
     *
     * @param initialBalance The initial balance of the user.
     */
    public User(double initialBalance) {
        this.balance = initialBalance;
        this.id = nextId++;
    }

    /**
     * Credits the user's balance with the specified amount.
     *
     * @param amount The amount to credit.
     */
    public void creditBalance(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("User " + id + " credited with " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Invalid credit amount.");
        }
    }

    /**
     * Gets the balance of the user.
     *
     * @return The balance of the user.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return The identifier of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Debits the user's balance with the specified amount.
     *
     * @param amount The amount to debit.
     */
    public void debitBalance(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
        } else {
            System.out.println("Invalid debit amount or insufficient funds.");
        }
    }

    /**
     * Gets a description of the user.
     *
     * @return The description of the user.
     */
    public String getDescription() {
        return "User " + id + " Balance: " + balance;
    }
}
