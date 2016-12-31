package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Paper {
  public String abstract_ = SeqUtil.toStr(SeqUtil.seq());
  public Number publicationDate;
  public String DOI = SeqUtil.toStr(SeqUtil.seq());
  public String title = SeqUtil.toStr(SeqUtil.seq());
  private VDMSet authors = SetUtil.set();
  private VDMSet cites = SetUtil.set();
  private VDMSet relatedTo = SetUtil.set();

  public void cg_init_Paper_2(
      final String ab,
      final Number pubDate,
      final String t,
      final String doi,
      final VDMSet authrs) {

    abstract_ = ab;
    publicationDate = pubDate;
    title = t;
    DOI = doi;
    authors = Utils.copy(authrs);
    return;
  }

  public void cg_init_Paper_1(
      final String ab,
      final Number pubDate,
      final String t,
      final String doi,
      final String author) {

    abstract_ = ab;
    publicationDate = pubDate;
    title = t;
    DOI = doi;
    authors = SetUtil.set(author);
    return;
  }

  public Paper(
      final String ab,
      final Number pubDate,
      final String t,
      final String doi,
      final String author) {

    cg_init_Paper_1(ab, pubDate, t, doi, author);
  }

  public void changeAbstract(final String newAbstract) {

    abstract_ = newAbstract;
  }

  public void changePublicationDate(final Number newPublicationDate) {

    publicationDate = newPublicationDate;
  }

  public void changeTitle(final String newTitle) {

    title = newTitle;
  }

  public void changeDOI(final String newDOI) {

    DOI = newDOI;
  }

  public void changeAuthors(final VDMSet newAuthors) {

    authors = Utils.copy(newAuthors);
  }

  public Paper(
      final String ab,
      final Number pubDate,
      final String t,
      final String doi,
      final VDMSet authrs) {

    cg_init_Paper_2(ab, pubDate, t, doi, Utils.copy(authrs));
  }

  public VDMSet getAuthors() {

    return Utils.copy(authors);
  }

  public String getTitle() {

    return title;
  }

  public VDMSet getCitations() {

    return Utils.copy(cites);
  }

  public VDMSet getRelatedPapers() {

    return Utils.copy(relatedTo);
  }

  public void addCitation(final Paper p) {

    throw new UnsupportedOperationException();
  }

  public void addRelatedPaper(final Paper p) {

    throw new UnsupportedOperationException();
  }

  public Number getNumCitedBy(final VDMSet papers) {

    Number res = 0L;
    for (Iterator iterator_2 = papers.iterator(); iterator_2.hasNext(); ) {
      Paper p = (Paper) iterator_2.next();
      if (SetUtil.inSet(this, p.getCitations())) {
        res = res.longValue() + 1L;
      }
    }
    return res;
  }

  public static VDMSet papersFromAuthor(final VDMSet papers, final String author) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_3 = papers.iterator(); iterator_3.hasNext(); ) {
      Paper p = (Paper) iterator_3.next();
      if (SetUtil.inSet(author, p.getAuthors())) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(p));
      }
    }
    return Utils.copy(res);
  }

  public Paper() {}

  public String toString() {

    return "Paper{"
        + "abstract_ := "
        + Utils.toString(abstract_)
        + ", publicationDate := "
        + Utils.toString(publicationDate)
        + ", DOI := "
        + Utils.toString(DOI)
        + ", title := "
        + Utils.toString(title)
        + ", authors := "
        + Utils.toString(authors)
        + ", cites := "
        + Utils.toString(cites)
        + ", relatedTo := "
        + Utils.toString(relatedTo)
        + "}";
  }
}
