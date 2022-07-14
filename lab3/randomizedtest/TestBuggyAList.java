package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
    public void testThreeAddTreeRemove() {
      AListNoResizing<Integer> al1 = new AListNoResizing<>();
      BuggyAList<Integer> al2 = new BuggyAList<>();

      al1.addLast(4);
      al1.addLast(5);
      al1.addLast(6);

      al2.addLast(4);
      al2.addLast(5);
      al2.addLast(6);

      assertEquals(al1.size(), al2.size());

      assertEquals(al1.removeLast(), al2.removeLast());
      assertEquals(al1.removeLast(), al2.removeLast());
      assertEquals(al1.removeLast(), al2.removeLast());
  }

  @Test
    public void randomizedTest() {
      AListNoResizing<Integer> L = new AListNoResizing<>();
      BuggyAList<Integer> l = new BuggyAList<>();

      int N = 5000;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 4);
          if (operationNumber == 0) {
              // addLast
              int randVal = StdRandom.uniform(0, 100);
              L.addLast(randVal);
              l.addLast(randVal);
          } else if (operationNumber == 1) {
              // size
              int size = L.size();
              int size2 = l.size();
              assertEquals(size, size2);
          } else if (operationNumber == 2) {
              // getLast
              if (L.size() != 0 && l.size() != 0) {
                  int last = L.getLast();
                  int last2 = l.getLast();
                  assertEquals(last, last2);
              }
          } else if (operationNumber == 3) {
              // removeLast
              if (L.size() != 0 && l.size() != 0) {
                  int last = L.removeLast();
                  int last2 = l.removeLast();
                  assertEquals(last, last2);
              }
          }
      }
  }
}
