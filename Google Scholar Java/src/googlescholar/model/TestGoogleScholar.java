package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestGoogleScholar extends MyTestCase {
  public void testAll() {

    IO.println("Starting 'Scholar' tests.");
    IO.println("\ttestRegister() starting.");
    testRegister();
    IO.println("\ttestRegister() ended.");
    IO.println("\ttestLoginLogout() starting.");
    testLoginLogout();
    IO.println("\ttestLoginLogout() ended.");
    IO.println("'Scholar' tests done.");
    IO.println("\ttestAddPaper() starting.");
    testAddPaper();
    IO.println("\ttestAddPaper() ended");
    IO.println("\ttestInvariant() starting.");
    testInvariant();
    IO.println("\ttestInvariant() ended");
    IO.println("'Scholar' tests done.");
  }

  public void testRegister() {

    {
      final Scholar s = TestUtils.generalScn();
      {
        String e = "new@gmail.com";
        String p = "newpass";
        assertEqual(s.getUserByEmail(e), null);
        s.register(e, p);
        Boolean andResult_11 = false;

        if (!(Utils.equals(s.getUserByEmail(e), null))) {
          if (s.getUserByEmail(e).isValidPassword(p)) {
            andResult_11 = true;
          }
        }

        assertTrue(andResult_11);
      }
    }
  }

  public void testLoginLogout() {

    {
      final Scholar s = TestUtils.generalScn();
      {
        s.login("inexistentuser@gmail.com", "pass1");
        assertTrue(!(s.isLoggedIn()));
        s.login("user1@gmail.com", "wrongpass");
        assertTrue(!(s.isLoggedIn()));
        s.login("user1@gmail.com", "pass1");
        assertTrue(s.isLoggedIn());
        s.logout();
        assertTrue(!(s.isLoggedIn()));
      }
    }
  }

  public void testAddPaper() {

    {
      final Scholar s = TestUtils.loggedInScn();
      {
        User u = s.getCurrentUser();
        Paper p =
            new Paper(
                "A novel approach using Alloy in domain-specific language engineering",
                2015L,
                "Alloy in domain-specific language engineering",
                "10.5220/0005228101570164",
                SetUtil.set("Moreira", "RMLM", "Paiva", "ACR"),
                null);
        s.addPaper(p);
        assertTrue(SetUtil.inSet(p, s.getPapersFromAuthorName("Moreira")));
        assertTrue(SetUtil.inSet(p, s.getPapersFromAuthorName("RMLM")));
        assertTrue(SetUtil.inSet(p, s.getPapersFromAuthorName("Paiva")));
        assertTrue(SetUtil.inSet(p, s.getPapersFromAuthorName("ACR")));
        assertTrue(SetUtil.inSet(p, s.getPapers()));
        Boolean existsExpResult_1 = false;
        VDMSet set_2 = u.getPapers();
        for (Iterator iterator_2 = set_2.iterator();
            iterator_2.hasNext() && !(existsExpResult_1);
            ) {
          Paper up = ((Paper) iterator_2.next());
          existsExpResult_1 =
              Utils.equals(
                  up.getAbstract(),
                  "A novel approach using Alloy in domain-specific language engineering");
        }
        assertTrue(!(existsExpResult_1));

        assertTrue(
            SetUtil.inSet(
                p, s.getPapersFromTitle("Alloy in domain-specific language engineering")));
        assertTrue(SetUtil.inSet(p, s.getPapersFromDOI("10.5220/0005228101570164")));
      }
    }
  }

  public void testInvariant() {

    {
      final Scholar s = TestUtils.loggedInScn();
      {
        User u = s.getCurrentUser();
        Paper p =
            new Paper(
                "A novel approach using Alloy in domain-specific language engineering",
                2015L,
                "Alloy in domain-specific language engineering",
                "10.5220/0005228101570164",
                SetUtil.set("Moreira", "RMLM", "Paiva", "ACR"),
                null);
        Paper p2 = p.cg_clone(true);
        s.addPaper(p);
        u.addPaper(p2);
        assertEqual(SetUtil.set(p), s.getPapers());
      }
    }
  }

  public TestGoogleScholar() {}

  public String toString() {

    return "TestGoogleScholar{}";
  }
}
