package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestGoogleScholar extends MyTestCase {
  public Scholar generalScn() {

    Scholar scholar = new Scholar();
    scholar.register("user1@gmail.com", "pass1");
    scholar.register("user2@gmail.com", "pass2");
    scholar.register("user3@gmail.com", "pass3");
    return scholar;
  }

  public Scholar loggedInScn() {

    {
      final Scholar s = generalScn();
      {
        s.login("user1@gmail.com", "pass1");
        return s;
      }
    }
  }

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
    IO.println("\ttestRemovePaper() starting.");
    testRemovePaper();
    IO.println("\ttestRemovePaper() ended");
  }

  public void testRegister() {

    {
      final Scholar s = generalScn();
      {
        String e = "new@gmail.com";
        String p = "newpass";
        assertEqual(s.getUserByEmail(e), null);
        s.register(e, p);
        Boolean andResult_8 = false;

        if (!(Utils.equals(s.getUserByEmail(e), null))) {
          if (s.getUserByEmail(e).isValidPassword(p)) {
            andResult_8 = true;
          }
        }

        assertTrue(andResult_8);
      }
    }
  }

  public void testLoginLogout() {

    {
      final Scholar s = generalScn();
      {
        s.login("inexistentuser@gmail.com", "pass1");
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
      final Scholar s = loggedInScn();
      {
        User u = s.getCurrentUser();
        Paper p =
            new Paper(
                "A novel approach using Alloy in domain-specific language engineering",
                2015L,
                "Alloy in domain-specific language engineering",
                "10.5220/0005228101570164",
                SetUtil.set("Moreira", "RMLM", "Paiva", "ACR"));
        s.addPaper(p);
        assertTrue(SetUtil.inSet(p, s.getPapersFromAuthorName("Moreira")));
        assertTrue(SetUtil.inSet(p, s.getPapersFromAuthorName("RMLM")));
        assertTrue(SetUtil.inSet(p, s.getPapersFromAuthorName("Paiva")));
        assertTrue(SetUtil.inSet(p, s.getPapersFromAuthorName("ACR")));
        assertTrue(SetUtil.inSet(p, u.getPapers()));
        assertTrue(
            SetUtil.inSet(
                p, s.getPapersFromTitle("Alloy in domain-specific language engineering")));
      }
    }
  }

  public void testRemovePaper() {

    {
      final Scholar s = loggedInScn();
      {
        User u = s.getCurrentUser();
        Paper p =
            new Paper(
                "A novel approach using Alloy in domain-specific language engineering",
                2015L,
                "Alloy in domain-specific language engineering",
                "10.5220/0005228101570164",
                SetUtil.set("Moreira", "RMLM", "Paiva", "ACR"));
        s.addPaper(p);
        s.removePaper(p);
        assertTrue(!(SetUtil.inSet(p, s.getPapersFromAuthorName("Moreira"))));
        assertTrue(!(SetUtil.inSet(p, s.getPapersFromAuthorName("RMLM"))));
        assertTrue(!(SetUtil.inSet(p, s.getPapersFromAuthorName("Paiva"))));
        assertTrue(!(SetUtil.inSet(p, s.getPapersFromAuthorName("ACR"))));
        assertTrue(!(SetUtil.inSet(p, u.getPapers())));
      }
    }
  }

  public TestGoogleScholar() {}

  public String toString() {

    return "TestGoogleScholar{}";
  }
}
