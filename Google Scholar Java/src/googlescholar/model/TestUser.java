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
    IO.println("'User' tests done.");
  }

  public void testAddRemovePaper() {

    {
      final Scholar s = TestUtils.loggedInScn();
      {
        User u = s.getCurrentUser();
        Paper p1 = new Paper("a", 2015L, "t", "doi", "author");
        Paper p2 = new Paper("a2", 2016L, "t2", "doi2", "author2");
        s.addPaper(p1);
        s.addPaper(p2);
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

  public TestUser() {}

  public String toString() {

    return "TestUser{}";
  }
}
