package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestUtils extends MyTestCase {
  public static Scholar generalScn() {

    Scholar scholar = new Scholar();
    scholar.register("user1@gmail.com", "pass1");
    scholar.register("user2@gmail.com", "pass2");
    scholar.register("user3@gmail.com", "pass3");
    return scholar;
  }

  public static Scholar loggedInScn() {

    {
      final Scholar s = generalScn();
      {
        s.login("user1@gmail.com", "pass1");
        return s;
      }
    }
  }

  public void testAll() {

    IO.println("Starting 'Utils' tests.");
    IO.println("\ttestHIndex() starting.");
    testHIndex();
    IO.println("\ttestHIndex() ended.");
    IO.println("\ttestMin() starting.");
    testMin();
    IO.println("\ttestMin() ended.");
    IO.println("\ttestsort() starting.");
    testSort();
    IO.println("\ttestsort() ended.");
    IO.println("'Utils' tests done.");
  }

  public void testHIndex() {

    VDMSeq a1 = SeqUtil.seq(10L, 8L, 5L, 4L, 3L);
    VDMSeq a2 = SeqUtil.seq(25L, 8L, 5L, 3L, 3L);
    assertEqual(4L, ScholarUtils.GetHIndex(Utils.copy(a1)));
    assertEqual(3L, ScholarUtils.GetHIndex(Utils.copy(a2)));
  }

  public void testMin() {

    assertEqual(4L, ScholarUtils.Min(4L, 50L));
    assertEqual(3L, ScholarUtils.Min(50L, 3L));
  }

  public void testSort() {

    assertEqual(
        SeqUtil.seq(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L),
        Sort.sort(SeqUtil.seq(5L, 1L, 3L, 8L, 2L, 7L, 4L, 6L)));
    assertEqual(
        SeqUtil.seq(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L),
        Sort.sort(SeqUtil.seq(8L, 2L, 5L, 1L, 4L, 3L, 6L, 7L)));
  }

  public TestUtils() {}

  public String toString() {

    return "TestUtils{}";
  }
}
