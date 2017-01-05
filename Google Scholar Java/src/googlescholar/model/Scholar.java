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

    for (Iterator iterator_5 = users.iterator(); iterator_5.hasNext(); ) {
      User u = (User) iterator_5.next();
      if (Utils.equals(u.getEmail(), e)) {
        return u;
      }
    }
    return null;
  }

  public void login(final String e, final String p) {

    User u = getUserByEmail(e);
    Boolean andResult_9 = false;

    if (!(Utils.equals(u, null))) {
      if (u.isValidPassword(p)) {
        andResult_9 = true;
      }
    }

    if (andResult_9) {
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
    for (Iterator iterator_6 = papers.iterator(); iterator_6.hasNext(); ) {
      Paper p = (Paper) iterator_6.next();
      if (SetUtil.inSet(name, p.getAuthors())) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(p));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet getPapersFromTitle(final String title) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_7 = papers.iterator(); iterator_7.hasNext(); ) {
      Paper p = (Paper) iterator_7.next();
      if (Utils.equals(title, p.getTitle())) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(p));
      }
    }
    return Utils.copy(res);
  }

  public void addPaper(final Paper paper) {

    papers = SetUtil.union(Utils.copy(papers), SetUtil.set(paper));
  }

  public VDMSet getPapers() {

    return Utils.copy(papers);
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
