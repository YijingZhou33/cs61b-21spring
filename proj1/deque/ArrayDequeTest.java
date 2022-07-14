package deque;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Yijing Zhou
 */
public class ArrayDequeTest {
    @Test
    /**
     * Adds a few things to the front of ArrayDeque, checking isEmpty(), size(), get() are correct,
     * finally printing the results.
     * */
    public void addFirstTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        assertEquals(null, ad1.get(0));

        for (int i = 10; i > 0; i -= 1) {
            ad1.addFirst(i);
        }
        assertEquals(10, ad1.size());
        assertEquals((Integer) 10, ad1.get(9));
        assertEquals(null, ad1.get(10));
        assertFalse("ad1 should now contain 10 item", ad1.isEmpty());

        ad1.printDeque();
    }

    @Test
    /**
     * Adds a few things to the end of ArrayDeque
     * */
    public void addLastTest() {
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();

        for (int i = 0; i < 10; i += 1) {
            ad2.addLast(i);
        }

        assertEquals((Integer) 2, ad2.get(2));
        assertEquals(null, ad2.get(-1));

        ad2.printDeque();
    }

    @Test
    /**
     * Adds an item, then removes an item from both the start and the end of the list
     * and ensures that dll is empty afterwards.
     * */
    public void addRemoveFirstTest() {

        ArrayDeque<String> ad3 = new ArrayDeque<>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad3.isEmpty());

        ad3.addFirst("first");
        ad3.addLast("end");
        ad3.printDeque();

        ad3.removeFirst();
        assertEquals("end", ad3.get(0));
        ad3.removeLast();

        // should be empty
        assertTrue("ad3 should be empty after removal", ad3.isEmpty());

    }

    @Test
    /** Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Double>  ad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<Boolean>();

        ad1.addFirst(100);
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        Integer s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

    @Test
    /**
     * Adds a few things, then remove to check the resize (increase and decrease)
     * */
    public void resizeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for (int i = 0; i < 16; i += 1) {
            ad1.addLast(i);
        }

        int cnt = 0;
        while (cnt < 12) {
            ad1.removeLast();
            cnt += 1;
        }

        ad1.removeLast();
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigADequeTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }
    }

    @Test
    public void fillUpEmptyFillUpAgain() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for (int i = 0; i < 8; i += 1) {
            ad1.addLast(i);
        }

        for (int i = 0; i < 8; i += 1) {
            ad1.removeFirst();
        }

        assertTrue("ad1 should now be empty", ad1.isEmpty());

        for (int i = 0; i < 10; i += 1) {
            ad1.addFirst(i);
        }

        assertEquals((Integer) 4, ad1.get(5));
        assertEquals(null, ad1.get(10));
    }
}
