import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import ppc.lab.Computations;

public class ComputationsTest {
    
    @Test
    void testFibonacci() {
        assertEquals(0, Computations.fibonacci(0));
        assertEquals(1, Computations.fibonacci(1));
        assertEquals(1, Computations.fibonacci(2));
        assertEquals(2, Computations.fibonacci(3));
        assertEquals(55, Computations.fibonacci(10));
    }

    @Test
    void testFibonacciNegative() {
        assertThrows(IllegalArgumentException.class,
            () -> Computations.fibonacci(-1)
        );
    }

    @Test 
    void testIsPrime() {
        // Prime cases
        assertTrue(Computations.isPrime(2));
        assertTrue(Computations.isPrime(3));
        assertTrue(Computations.isPrime(5));
        assertTrue(Computations.isPrime(7));
        assertTrue(Computations.isPrime(47));
        assertTrue(Computations.isPrime(97));
        
        // Non-prime/Composite cases
        assertFalse(Computations.isPrime(1));
        assertFalse(Computations.isPrime(4));
        assertFalse(Computations.isPrime(6));
        assertFalse(Computations.isPrime(8));
        assertFalse(Computations.isPrime(51));
        assertFalse(Computations.isPrime(129));
        assertFalse(Computations.isPrime(-2));
        assertFalse(Computations.isPrime(-13));
    }

    @Test
    void testIsEven() {
        // Even cases
        assertTrue(Computations.isEven(0));
        assertTrue(Computations.isEven(2));
        assertTrue(Computations.isEven(50));
        assertTrue(Computations.isEven(1758));
        assertTrue(Computations.isEven(Integer.MAX_VALUE-1));
        assertTrue(Computations.isEven(Integer.MIN_VALUE));
        assertTrue(Computations.isEven(-2));
        assertTrue(Computations.isEven(-564));

        // Non-even/Odd cases
        assertFalse(Computations.isEven(1));
        assertFalse(Computations.isEven(3));
        assertFalse(Computations.isEven(47));
        assertFalse(Computations.isEven(7455));
        assertFalse(Computations.isEven(Integer.MAX_VALUE));
        assertFalse(Computations.isEven(Integer.MIN_VALUE+1));
        assertFalse(Computations.isEven(-1));
        assertFalse(Computations.isEven(-949));
    }    

    @Test
    void testIsOdd() {
        // Odd cases
        assertTrue(Computations.isOdd(1));
        assertTrue(Computations.isOdd(3));
        assertTrue(Computations.isOdd(47));
        assertTrue(Computations.isOdd(7455));
        assertTrue(Computations.isOdd(Integer.MAX_VALUE));
        assertTrue(Computations.isOdd(Integer.MIN_VALUE+1));
        assertTrue(Computations.isOdd(-1));
        assertTrue(Computations.isOdd(-949));

         // Non-odd/Even cases
        assertFalse(Computations.isOdd(0));
        assertFalse(Computations.isOdd(2));
        assertFalse(Computations.isOdd(50));
        assertFalse(Computations.isOdd(1758));
        assertFalse(Computations.isOdd(Integer.MAX_VALUE-1));
        assertFalse(Computations.isOdd(Integer.MIN_VALUE));
        assertFalse(Computations.isOdd(-2));
        assertFalse(Computations.isOdd(-564));
    }

    @Test
    void testToCelsius() {
        assertEquals(0, Computations.toCelsius(32), 0.1);
        assertEquals(100, Computations.toCelsius(212), 0.1);
        assertEquals(50, Computations.toCelsius(122), 0.1);
        assertEquals(12, Computations.toCelsius(53.6), 0.1);
        assertEquals(-15, Computations.toCelsius(5), 0.1);
        assertEquals(-21, Computations.toCelsius(-5.8), 0.1);
    }

    @Test
    void testToFahrenheit() {
        assertEquals(32, Computations.toFahrenheit(0), 0.1);
        assertEquals(212, Computations.toFahrenheit(100), 0.1);
        assertEquals(122, Computations.toFahrenheit(50), 0.1);
        assertEquals(53.6, Computations.toFahrenheit(12), 0.1);
        assertEquals(5, Computations.toFahrenheit(-15), 0.1);
        assertEquals(-5.8, Computations.toFahrenheit(-21), 0.1);
    }

}
