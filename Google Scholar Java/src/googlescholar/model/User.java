package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public String email = SeqUtil.toStr(SeqUtil.seq());
  public String password = SeqUtil.toStr(SeqUtil.seq());
  public VDMSet papers = SetUtil.set();

  public void cg_init_User_1(final String e, final String p) {

    email = e;
    password = p;
    return;
  }

  public User(final String e, final String p) {

    cg_init_User_1(e, p);
  }

  public String getEmail() {

    return email;
  }

  public void changeEmail(final String newEmail) {

    email = newEmail;
  }

  public Boolean isValidPassword(final String p) {

    return isMatchingPassword(p, password);
  }

  public void changePassword(final String newPassword) {

    password = newPassword;
  }

  public void addPaper(final Paper paper) {

    papers = SetUtil.union(Utils.copy(papers), SetUtil.set(paper));
  }

  public void removePaper(final Paper paper) {

    papers = SetUtil.diff(Utils.copy(papers), SetUtil.set(paper));
  }

  public VDMSet getPapers() {

    return Utils.copy(papers);
  }

  public Number getHIndex(final VDMSet allPapers) {

    VDMSeq scores = SeqUtil.seq();
    for (Iterator iterator_8 = papers.iterator(); iterator_8.hasNext(); ) {
      Paper p = (Paper) iterator_8.next();
      scores =
          SeqUtil.conc(Utils.copy(scores), SeqUtil.seq(p.getNumCitedBy(Utils.copy(allPapers))));
    }
    scores = Sort.sort(Utils.copy(scores));
    return ScholarUtils.GetHIndex(Utils.copy(scores));
  }

  public Number getI10Index(final VDMSet allPapers) {

    Number result = 0L;
    for (Iterator iterator_9 = papers.iterator(); iterator_9.hasNext(); ) {
      Paper p = (Paper) iterator_9.next();
      if (p.getNumCitedBy(Utils.copy(allPapers)).longValue() >= 10L) {
        result = result.longValue() + 1L;
      }
    }
    return result;
  }

  public User() {}

  public static Boolean isMatchingPassword(final String p1, final String p2) {

    return Utils.equals(p1, p2);
  }

  public String toString() {

    return "User{"
        + "email := "
        + Utils.toString(email)
        + ", password := "
        + Utils.toString(password)
        + ", papers := "
        + Utils.toString(papers)
        + "}";
  }
}
