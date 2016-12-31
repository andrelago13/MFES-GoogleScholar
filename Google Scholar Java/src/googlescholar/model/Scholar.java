package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Scholar {
  private VDMSet users = SetUtil.set();
  private VDMSet papers = SetUtil.set();
  private VDMSet people = SetUtil.set();
  private User currentUser = null;

  public User getUserByEmail(final String e) {

    for (Iterator iterator_4 = users.iterator(); iterator_4.hasNext(); ) {
      User u = (User) iterator_4.next();
      if (Utils.equals(u.getEmail(), e)) {
        return u;
      }
    }
    return null;
  }

  public void login(final String e, final String p) {

    User u = getUserByEmail(e);
    Boolean andResult_5 = false;

    if (!(Utils.equals(u, null))) {
      if (u.isValidPassword(p)) {
        andResult_5 = true;
      }
    }

    if (andResult_5) {
      currentUser = u;
    }
  }

  public void logout() {

    currentUser = null;
  }

  public Boolean isLoggedIn() {

    return !(Utils.equals(currentUser, null));
  }

  public User getCurrentUser() {

    return currentUser;
  }

  public void register(final String e, final String p) {

    users = SetUtil.union(Utils.copy(users), SetUtil.set(new User(e, p)));
  }

  public VDMSet getPapersFromAuthorName(final String name) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_5 = papers.iterator(); iterator_5.hasNext(); ) {
      Paper p = (Paper) iterator_5.next();
      if (SetUtil.inSet(name, p.getAuthors())) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(p));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getPapersFromTitle(final String title) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_6 = papers.iterator(); iterator_6.hasNext(); ) {
      Paper p = (Paper) iterator_6.next();
      if (Utils.equals(title, p.getTitle())) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(p));
      }
    }
    return Utils.copy(res);
  }

  public void addPaper(final Paper paper) {

    papers = SetUtil.union(Utils.copy(papers), SetUtil.set(paper));
    currentUser.addPaper(paper);
  }

  public void removePaper(final Paper paper) {

    papers = SetUtil.diff(Utils.copy(papers), SetUtil.set(paper));
    currentUser.removePaper(paper);
  }

  public Scholar() {}

  public String toString() {

    return "Scholar{"
        + "users := "
        + Utils.toString(users)
        + ", papers := "
        + Utils.toString(papers)
        + ", people := "
        + Utils.toString(people)
        + ", currentUser := "
        + Utils.toString(currentUser)
        + "}";
  }
}
