import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import ppc.lab.Misc;

public class OtherTests {

    @BeforeEach
    void setUp() {
        Misc.resetAll();
    }
    
    @Test
    void testArrayLessTwenty() {
        Misc.randomizeIntArr(10, 1, 20);
        for (int x: Misc.getIntArr()) {
            assertFalse(x < 20, "Should not be less than 20");
        }
    }

    @Test
    void testSameCharacters() {
        Misc.setStrOne("arm");
        Misc.setStrTwo("ram");

        HashSet<Character> hs1 = new HashSet<>();
        HashSet<Character> hs2 = new HashSet<>();
        for (char c: Misc.getStrOne().toCharArray())
            hs1.add(c);
        for (char c: Misc.getStrTwo().toCharArray())
            hs2.add(c);

        assertTrue(hs1.equals(hs2));
    }

    @Test 
    void testDummy() {
        assertTrue(true);
    }

    // 4. Yes, all other test methods will be executed even if the first one fails.

}
