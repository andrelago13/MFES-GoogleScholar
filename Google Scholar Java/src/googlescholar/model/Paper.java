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
  private Paper parent = null;

  public void cg_init_Paper_2(
      final String ab,
      final Number pubDate,
      final String t,
      final String doi,
      final VDMSet authrs,
      final Paper p) {

    abstract_ = ab;
    publicationDate = pubDate;
    title = t;
    DOI = doi;
    authors = Utils.copy(authrs);
    parent = p;
    if (!(Utils.equals(p, null))) {
      cites = p.cites;
      relatedTo = p.relatedTo;
    }

    return;
  }

  public void cg_init_Paper_1(
      final String ab,
      final Number pubDate,
      final String t,
      final String doi,
      final String author,
      final Paper p) {

    abstract_ = ab;
    publicationDate = pubDate;
    title = t;
    DOI = doi;
    authors = SetUtil.set(author);
    parent = p;
    if (!(Utils.equals(p, null))) {
      cites = p.cites;
      relatedTo = p.relatedTo;
    }

    return;
  }

  public Paper(
      final String ab,
      final Number pubDate,
      final String t,
      final String doi,
      final String author,
      final Paper p) {

    cg_init_Paper_1(ab, pubDate, t, doi, author, p);
  }

  public Paper cg_clone(final Boolean makeChild) {

    Object ternaryIfExp_2 = null;

    if (makeChild) {
      ternaryIfExp_2 = this;
    } else {
      ternaryIfExp_2 = null;
    }

    return new Paper(
        abstract_, publicationDate, title, DOI, Utils.copy(authors), ((Paper) ternaryIfExp_2));
  }

  private Boolean inCitations() {

    return SetUtil.inSet(this, cites);
  }

  private Boolean inRelated() {

    return SetUtil.inSet(this, relatedTo);
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

  public void addAuthor(final String author) {

    authors = SetUtil.union(Utils.copy(authors), SetUtil.set(author));
  }

  public void removeAuthor(final String author) {

    authors = SetUtil.diff(Utils.copy(authors), SetUtil.set(author));
  }

  public Paper(
      final String ab,
      final Number pubDate,
      final String t,
      final String doi,
      final VDMSet authrs,
      final Paper p) {

    cg_init_Paper_2(ab, pubDate, t, doi, Utils.copy(authrs), p);
  }

  public String getAbstract() {

    return abstract_;
  }

  public VDMSet getAuthors() {

    return Utils.copy(authors);
  }

  public String getTitle() {

    return title;
  }

  public Number getPublicationDate() {

    return publicationDate;
  }

  public String getDOI() {

    return DOI;
  }

  public VDMSet getCitations() {

    return Utils.copy(cites);
  }

  public VDMSet getRelatedPapers() {

    return Utils.copy(relatedTo);
  }

  public void addCitation(final Paper p) {

    cites = SetUtil.union(Utils.copy(cites), SetUtil.set(p));
  }

  public void removeCitation(final Paper p) {

    cites = SetUtil.diff(Utils.copy(cites), SetUtil.set(p));
  }

  public void addRelatedPaper(final Paper p) {

    relatedTo = SetUtil.union(Utils.copy(relatedTo), SetUtil.set(p));
  }

  public void removeRelatedPaper(final Paper p) {

    relatedTo = SetUtil.diff(Utils.copy(relatedTo), SetUtil.set(p));
  }

  public Number getNumCitedBy(final VDMSet papers) {

    Number res = 0L;
    for (Iterator iterator_3 = papers.iterator(); iterator_3.hasNext(); ) {
      Paper p = (Paper) iterator_3.next();
      if (SetUtil.inSet(this, p.getCitations())) {
        res = res.longValue() + 1L;
      }
    }
    return res;
  }

  public static VDMSet papersFromAuthor(final VDMSet papers, final String author) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_4 = papers.iterator(); iterator_4.hasNext(); ) {
      Paper p = (Paper) iterator_4.next();
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
        + ", parent := "
        + Utils.toString(parent)
        + "}";
  }
}
