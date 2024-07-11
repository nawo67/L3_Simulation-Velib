package fil.l3.coo;

import static org.junit.Assert.*;
import org.junit.Test;

import fil.l3.coo.User.User;

public class UserTest {

    @Test
    public void testCreditBalance() {
        User user = new User(100.0);
        user.creditBalance(50.0);
        assertEquals(150.0, user.getBalance(), 0.001);
    }

    @Test
    public void testCreditBalanceWithInvalidAmount() {
        User user = new User(100.0);
        user.creditBalance(-50.0);
        assertEquals(100.0, user.getBalance(), 0.001);
    }

    @Test
    public void testDebitBalance() {
        User user = new User(100.0);
        user.debitBalance(50.0);
        assertEquals(50.0, user.getBalance(), 0.001);
    }

    @Test
    public void testDebitBalanceWithInvalidAmount() {
        User user = new User(100.0);
        user.debitBalance(-50.0);
        assertEquals(100.0, user.getBalance(), 0.001);
    }

    @Test
    public void testDebitBalanceWithInsufficientFunds() {
        User user = new User(100.0);
        user.debitBalance(150.0);
        assertEquals(100.0, user.getBalance(), 0.001);
    }

    @Test
    public void testUsersHaveUniqueId() {
        User user1 = new User(100.0);
        User user2 = new User(50.0);
        assertNotEquals(user1.getId(), user2.getId());
    }
}