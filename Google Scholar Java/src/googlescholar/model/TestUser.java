package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestUser extends MyTestCase {
  public void testAll() {

    IO.println("Starting 'User' tests.");
    IO.println("\ttestAddRemovePaper() starting.");
    testAddRemovePaper();
    IO.println("\ttestAddRemovePaper() ended.");
    IO.println("\ttestChanges() starting.");
    testChanges();
    IO.println("\ttestChanges() ended.");
    IO.println("\ttestHIndex() starting.");
    testHIndex();
    IO.println("\ttestHIndex() ended.");
    IO.println("\ttestI10Index() starting.");
    testI10Index();
    IO.println("\ttestI10Index() ended.");
    IO.println("'User' tests done.");
  }

  public void testAddRemovePaper() {

    {
      final Scholar s = TestUtils.loggedInScn();
      {
        User u = s.getCurrentUser();
        Paper p1 = new Paper("a", 2015L, "t", "doi", "author", null);
        Paper p2 = new Paper("a2", 2016L, "t2", "doi2", "author2", null);
        u.addPaper(p1);
        u.addPaper(p2);
        assertEqual(SetUtil.set(p1, p2), u.getPapers());
        u.removePaper(p1);
        assertEqual(SetUtil.set(p2), u.getPapers());
        u.removePaper(p2);
        assertEqual(SetUtil.set(), u.getPapers());
      }
    }
  }

  public void testChanges() {

    {
      final Scholar s = TestUtils.loggedInScn();
      {
        User u = s.getCurrentUser();
        u.changeEmail("newmail@gmail.com");
        u.changePassword("newpassword");
        assertEqual("newmail@gmail.com", u.getEmail());
        assertTrue(u.isValidPassword("newpassword"));
      }
    }
  }

  public void testHIndex() {

    testHIndex_1();
    testHIndex_2();
  }

  private void testHIndex_1() {

    User u = new User();
    Paper p1 = new Paper("", 0L, "", "", "", null);
    Paper p2 = new Paper("", 0L, "", "", "", null);
    Paper p3 = new Paper("", 0L, "", "", "", null);
    Paper p4 = new Paper("", 0L, "", "", "", null);
    Paper p5 = new Paper("", 0L, "", "", "", null);
    Paper p_1 = new Paper("", 0L, "", "", "", null);
    Paper p_2 = new Paper("", 0L, "", "", "", null);
    Paper p_3 = new Paper("", 0L, "", "", "", null);
    Paper p_4 = new Paper("", 0L, "", "", "", null);
    Paper p_5 = new Paper("", 0L, "", "", "", null);
    Paper p_6 = new Paper("", 0L, "", "", "", null);
    Paper p_7 = new Paper("", 0L, "", "", "", null);
    Paper p_8 = new Paper("", 0L, "", "", "", null);
    Paper p_9 = new Paper("", 0L, "", "", "", null);
    Paper p_10 = new Paper("", 0L, "", "", "", null);
    Paper p_11 = new Paper("", 0L, "", "", "", null);
    VDMSet papers =
        SetUtil.set(p1, p2, p3, p4, p5, p_1, p_2, p_3, p_4, p_5, p_6, p_7, p_8, p_9, p_10, p_11);
    u.addPaper(p1);
    u.addPaper(p2);
    u.addPaper(p3);
    u.addPaper(p4);
    u.addPaper(p5);
    p_3.addCitation(p1);
    p_5.addCitation(p1);
    p_7.addCitation(p1);
    p_10.addCitation(p1);
    p_11.addCitation(p1);
    p_1.addCitation(p2);
    p_2.addCitation(p2);
    p_3.addCitation(p2);
    p_4.addCitation(p2);
    p_5.addCitation(p2);
    p_6.addCitation(p2);
    p_7.addCitation(p2);
    p_8.addCitation(p2);
    p_9.addCitation(p2);
    p_10.addCitation(p2);
    p_1.addCitation(p3);
    p_5.addCitation(p3);
    p_11.addCitation(p3);
    p_2.addCitation(p4);
    p_3.addCitation(p4);
    p_5.addCitation(p4);
    p_6.addCitation(p4);
    p_7.addCitation(p4);
    p_9.addCitation(p4);
    p_10.addCitation(p4);
    p_11.addCitation(p4);
    p_1.addCitation(p5);
    p_2.addCitation(p5);
    p_4.addCitation(p5);
    p_5.addCitation(p5);
    assertEqual(4L, u.getHIndex(Utils.copy(papers)));
  }

  private void testHIndex_2() {

    User u = new User();
    Paper p1 = new Paper("", 0L, "", "", "", null);
    Paper p2 = new Paper("", 0L, "", "", "", null);
    Paper p3 = new Paper("", 0L, "", "", "", null);
    Paper p4 = new Paper("", 0L, "", "", "", null);
    Paper p5 = new Paper("", 0L, "", "", "", null);
    Paper p_1 = new Paper("", 0L, "", "", "", null);
    Paper p_2 = new Paper("", 0L, "", "", "", null);
    Paper p_3 = new Paper("", 0L, "", "", "", null);
    Paper p_4 = new Paper("", 0L, "", "", "", null);
    Paper p_5 = new Paper("", 0L, "", "", "", null);
    Paper p_6 = new Paper("", 0L, "", "", "", null);
    Paper p_7 = new Paper("", 0L, "", "", "", null);
    Paper p_8 = new Paper("", 0L, "", "", "", null);
    Paper p_9 = new Paper("", 0L, "", "", "", null);
    Paper p_10 = new Paper("", 0L, "", "", "", null);
    Paper p_11 = new Paper("", 0L, "", "", "", null);
    Paper p_12 = new Paper("", 0L, "", "", "", null);
    Paper p_13 = new Paper("", 0L, "", "", "", null);
    Paper p_14 = new Paper("", 0L, "", "", "", null);
    Paper p_15 = new Paper("", 0L, "", "", "", null);
    Paper p_16 = new Paper("", 0L, "", "", "", null);
    Paper p_17 = new Paper("", 0L, "", "", "", null);
    Paper p_18 = new Paper("", 0L, "", "", "", null);
    Paper p_19 = new Paper("", 0L, "", "", "", null);
    Paper p_20 = new Paper("", 0L, "", "", "", null);
    Paper p_21 = new Paper("", 0L, "", "", "", null);
    Paper p_22 = new Paper("", 0L, "", "", "", null);
    Paper p_23 = new Paper("", 0L, "", "", "", null);
    Paper p_24 = new Paper("", 0L, "", "", "", null);
    Paper p_25 = new Paper("", 0L, "", "", "", null);
    Paper p_26 = new Paper("", 0L, "", "", "", null);
    Paper p_27 = new Paper("", 0L, "", "", "", null);
    Paper p_28 = new Paper("", 0L, "", "", "", null);
    Paper p_29 = new Paper("", 0L, "", "", "", null);
    VDMSet papers =
        SetUtil.set(
            p1, p2, p3, p4, p5, p_1, p_2, p_3, p_4, p_5, p_6, p_7, p_8, p_9, p_10, p_11, p_12, p_13,
            p_14, p_15, p_16, p_17, p_18, p_19, p_20, p_21, p_22, p_23, p_24, p_25, p_26, p_27,
            p_28, p_29);
    u.addPaper(p1);
    u.addPaper(p2);
    u.addPaper(p3);
    u.addPaper(p4);
    u.addPaper(p5);
    p_24.addCitation(p1);
    p_26.addCitation(p1);
    p_27.addCitation(p1);
    p_28.addCitation(p1);
    p_29.addCitation(p1);
    p_3.addCitation(p2);
    p_5.addCitation(p2);
    p_10.addCitation(p2);
    p_11.addCitation(p2);
    p_13.addCitation(p2);
    p_15.addCitation(p2);
    p_20.addCitation(p2);
    p_21.addCitation(p2);
    p_7.addCitation(p3);
    p_10.addCitation(p3);
    p_15.addCitation(p3);
    p_1.addCitation(p4);
    p_2.addCitation(p4);
    p_4.addCitation(p4);
    p_5.addCitation(p4);
    p_6.addCitation(p4);
    p_8.addCitation(p4);
    p_9.addCitation(p4);
    p_10.addCitation(p4);
    p_11.addCitation(p4);
    p_12.addCitation(p4);
    p_13.addCitation(p4);
    p_14.addCitation(p4);
    p_16.addCitation(p4);
    p_17.addCitation(p4);
    p_18.addCitation(p4);
    p_19.addCitation(p4);
    p_20.addCitation(p4);
    p_21.addCitation(p4);
    p_22.addCitation(p4);
    p_23.addCitation(p4);
    p_24.addCitation(p4);
    p_25.addCitation(p4);
    p_26.addCitation(p4);
    p_27.addCitation(p4);
    p_29.addCitation(p4);
    p_1.addCitation(p5);
    p_3.addCitation(p5);
    p_4.addCitation(p5);
    assertEqual(3L, u.getHIndex(Utils.copy(papers)));
  }

  public void testI10Index() {

    User u = new User();
    Paper p1 = new Paper("", 0L, "", "", "", null);
    Paper p2 = new Paper("", 0L, "", "", "", null);
    Paper p3 = new Paper("", 0L, "", "", "", null);
    Paper p4 = new Paper("", 0L, "", "", "", null);
    Paper p5 = new Paper("", 0L, "", "", "", null);
    Paper p_1 = new Paper("", 0L, "", "", "", null);
    Paper p_2 = new Paper("", 0L, "", "", "", null);
    Paper p_3 = new Paper("", 0L, "", "", "", null);
    Paper p_4 = new Paper("", 0L, "", "", "", null);
    Paper p_5 = new Paper("", 0L, "", "", "", null);
    Paper p_6 = new Paper("", 0L, "", "", "", null);
    Paper p_7 = new Paper("", 0L, "", "", "", null);
    Paper p_8 = new Paper("", 0L, "", "", "", null);
    Paper p_9 = new Paper("", 0L, "", "", "", null);
    Paper p_10 = new Paper("", 0L, "", "", "", null);
    Paper p_11 = new Paper("", 0L, "", "", "", null);
    Paper p_12 = new Paper("", 0L, "", "", "", null);
    Paper p_13 = new Paper("", 0L, "", "", "", null);
    Paper p_14 = new Paper("", 0L, "", "", "", null);
    VDMSet papers =
        SetUtil.set(
            p1, p2, p3, p4, p5, p_1, p_2, p_3, p_4, p_5, p_6, p_7, p_8, p_9, p_10, p_11, p_12, p_13,
            p_14);
    u.addPaper(p1);
    u.addPaper(p2);
    u.addPaper(p3);
    u.addPaper(p4);
    u.addPaper(p5);
    p_1.addCitation(p1);
    p_2.addCitation(p1);
    p_5.addCitation(p1);
    p_6.addCitation(p1);
    p_7.addCitation(p1);
    p_9.addCitation(p1);
    p_12.addCitation(p1);
    p_1.addCitation(p2);
    p_2.addCitation(p2);
    p_3.addCitation(p2);
    p_5.addCitation(p2);
    p_6.addCitation(p2);
    p_7.addCitation(p2);
    p_8.addCitation(p2);
    p_10.addCitation(p2);
    p_12.addCitation(p2);
    p_13.addCitation(p2);
    p_14.addCitation(p2);
    p_1.addCitation(p3);
    p_2.addCitation(p3);
    p_3.addCitation(p3);
    p_4.addCitation(p3);
    p_5.addCitation(p3);
    p_6.addCitation(p3);
    p_7.addCitation(p3);
    p_8.addCitation(p3);
    p_9.addCitation(p3);
    p_11.addCitation(p3);
    p_12.addCitation(p3);
    p_13.addCitation(p3);
    p_14.addCitation(p3);
    p_3.addCitation(p4);
    p_4.addCitation(p4);
    p_5.addCitation(p4);
    p_7.addCitation(p4);
    p_8.addCitation(p4);
    p_9.addCitation(p4);
    p_10.addCitation(p4);
    p_11.addCitation(p4);
    p_13.addCitation(p4);
    p_1.addCitation(p5);
    p_2.addCitation(p5);
    p_4.addCitation(p5);
    p_5.addCitation(p5);
    p_7.addCitation(p5);
    p_9.addCitation(p5);
    p_10.addCitation(p5);
    p_12.addCitation(p5);
    p_13.addCitation(p5);
    p_14.addCitation(p5);
    assertEqual(3L, u.getI10Index(Utils.copy(papers)));
  }

  public TestUser() {}

  public String toString() {

    return "TestUser{}";
  }
}
