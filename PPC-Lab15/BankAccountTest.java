

/* These are imports you may or may not need depending
on where and how you are running the tests */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import ppc.lab.BankAccount;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Starts each test with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    /** 1. Add an @AfterEach annotation and method to delete the current bank account to make it available for garbage collection */
    @AfterEach
    void cleanUp() {
        account = null;
    }

    @Test
    void testDeposit() {
    /** 2. Adeposit $50 and check that the balance is 150 */
        account.deposit(50);
        assertEquals(150, account.getBalance(), "Should be 150.");
    }

    @Test
    void testWithdraw() {
    /** 3. withdraw $40 and check that the balance is $60; remember that each test is done on a fresh instance of bank account */
        account.withdraw(40);
        assertEquals(60, account.getBalance(), "Should be 60.");
    }

    @Test
    void testInvalidDeposit() {
    /** 4. Deposit a negative amount and check if an exception is thrown */
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1), "Can't deposit a negative amount.");
    }

    @Test
    void testOverdraft() {
    /** 5. Verify that Withdrawing more than the current balance
    throws an exception */
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(110), "Can't withdraw more than current balance.");
    }

    @Test
    /** 6. Add a test to check that an Exception is thrown when
    trying to create a new bankaccout with a negaive initial balance */
    void testInvalidInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1), "Can't have a negative initial balance.");
    }

    @Test
    void testTransfer() {
        BankAccount recip = new BankAccount(100);
        account.transfer(recip, 40);
        assertEquals(60, account.getBalance(), "1. Should be 60.");
        assertEquals(140, recip.getBalance(), "2. Should be 140.");
    }
    
}
