package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestPaper extends MyTestCase {
  public void testAll() {

    IO.println("Starting 'Paper' tests.");
    IO.println("\ttestChanges() starting.");
    testChanges();
    IO.println("\ttestChanges() ended.");
    IO.println("\ttestGetNumCitedBy() starting.");
    testGetNumCitedBy();
    IO.println("\ttestGetNumCitedBy() ended.");
    IO.println("\ttestPapersFromAuthor() starting.");
    testPapersFromAuthor();
    IO.println("\ttestPapersFromAuthor() ended.");
    IO.println("\ttestClone() starting.");
    testClone();
    IO.println("\ttestClone() ended.");
    IO.println("\ttestRemovals() starting.");
    testRemovals();
    IO.println("\ttestRemovals() ended.");
    IO.println("\ttestParent() starting.");
    testParent();
    IO.println("\ttestParent() ended.");
    IO.println("'Paper' tests done.");
  }

  public void testChanges() {

    Paper paper = new Paper("abstract", 1995L, "title", "doi", "author", null);
    Paper relatedPaper = new Paper("ab", 2013L, "t", "doi", "a", null);
    paper.changeAbstract("new abstract");
    paper.changePublicationDate(2017L);
    paper.changeTitle("new title");
    paper.changeDOI("new DOI");
    paper.changeAuthors(SetUtil.set("new author 1", "new author 2"));
    assertEqual("new abstract", paper.getAbstract());
    assertEqual(2017L, paper.getPublicationDate());
    assertEqual("new title", paper.getTitle());
    assertEqual("new DOI", paper.getDOI());
    assertTrue(SetUtil.inSet("new author 1", paper.getAuthors()));
    assertTrue(SetUtil.inSet("new author 2", paper.getAuthors()));
    assertEqual(2L, paper.getAuthors().size());
    assertEqual(SetUtil.set(), paper.getRelatedPapers());
    paper.addRelatedPaper(relatedPaper);
    assertEqual(SetUtil.set(relatedPaper), paper.getRelatedPapers());
  }

  public void testGetNumCitedBy() {

    Paper paper = new Paper("", 0L, "", "", "", null);
    Paper p1 = new Paper("", 0L, "", "", "", null);
    Paper p2 = new Paper("", 0L, "", "", "", null);
    Paper p3 = new Paper("", 0L, "", "", "", null);
    Paper p4 = new Paper("", 0L, "", "", "", null);
    Paper p5 = new Paper("", 0L, "", "", "", null);
    Paper p6 = new Paper("", 0L, "", "", "", null);
    Paper p7 = new Paper("", 0L, "", "", "", null);
    Paper p8 = new Paper("", 0L, "", "", "", null);
    Paper p9 = new Paper("", 0L, "", "", "", null);
    Paper p10 = new Paper("", 0L, "", "", "", null);
    Paper p11 = new Paper("", 0L, "", "", "", null);
    Paper p12 = new Paper("", 0L, "", "", "", null);
    VDMSet papers = SetUtil.set(paper, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);
    p2.addCitation(paper);
    p5.addCitation(paper);
    p8.addCitation(paper);
    p10.addCitation(paper);
    p11.addCitation(paper);
    assertEqual(5L, paper.getNumCitedBy(Utils.copy(papers)));
  }

  public void testPapersFromAuthor() {

    String author = "PASCOAL";
    Paper p1 = new Paper("", 0L, "", "", author, null);
    Paper p2 = new Paper("", 0L, "", "", "", null);
    Paper p3 = new Paper("", 0L, "", "", "", null);
    Paper p4 = new Paper("", 0L, "", "", author, null);
    Paper p5 = new Paper("", 0L, "", "", "", null);
    Paper p6 = new Paper("", 0L, "", "", "", null);
    Paper p7 = new Paper("", 0L, "", "", "", null);
    Paper p8 = new Paper("", 0L, "", "", author, null);
    Paper p9 = new Paper("", 0L, "", "", "", null);
    Paper p10 = new Paper("", 0L, "", "", "", null);
    Paper p11 = new Paper("", 0L, "", "", author, null);
    Paper p12 = new Paper("", 0L, "", "", "", null);
    VDMSet papers = SetUtil.set(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);
    assertEqual(SetUtil.set(p1, p4, p8, p11), Paper.papersFromAuthor(Utils.copy(papers), author));
  }

  public void testClone() {

    Paper p = new Paper("abstract", 1995L, "title", "doi", "author", null);
    Paper pc1 = p.cg_clone(false);
    Paper pc2 = p.cg_clone(true);
    assertEqual(p.getAbstract(), pc1.getAbstract());
    assertEqual(p.getPublicationDate(), pc1.getPublicationDate());
    assertEqual(p.getTitle(), pc1.getTitle());
    assertEqual(p.getDOI(), pc1.getDOI());
    assertEqual(p.getAuthors(), pc1.getAuthors());
    assertEqual(null, pc1.getParent());
    assertEqual(p.getAbstract(), pc2.getAbstract());
    assertEqual(p.getPublicationDate(), pc2.getPublicationDate());
    assertEqual(p.getTitle(), pc2.getTitle());
    assertEqual(p.getDOI(), pc2.getDOI());
    assertEqual(p.getAuthors(), pc2.getAuthors());
    assertEqual(p, pc2.getParent());
  }

  public void testRemovals() {

    Paper p = new Paper("abstract", 1995L, "title", "doi", "author", null);
    Paper p1 = new Paper("", 1L, "", "", "", null);
    Paper p2 = new Paper("", 1L, "", "", "", null);
    Paper p3 = new Paper("", 1L, "", "", "", null);
    Paper p4 = new Paper("", 1L, "", "", "", null);
    Paper p5 = new Paper("", 1L, "", "", "", null);
    Paper p6 = new Paper("", 1L, "", "", "", null);
    p.addRelatedPaper(p1);
    p.addRelatedPaper(p2);
    p.addRelatedPaper(p3);
    p.addRelatedPaper(p4);
    p.addRelatedPaper(p5);
    p.addCitation(p2);
    p.addCitation(p3);
    p.addCitation(p4);
    p.addCitation(p5);
    p.addCitation(p6);
    p.addAuthor("author1");
    assertEqual(SetUtil.set("author", "author1"), p.getAuthors());
    assertEqual(SetUtil.set(p1, p2, p3, p4, p5), p.getRelatedPapers());
    assertEqual(SetUtil.set(p2, p3, p4, p5, p6), p.getCitations());
    p.removeRelatedPaper(p4);
    p.removeCitation(p6);
    p.removeAuthor("author");
    assertEqual(SetUtil.set(p1, p2, p3, p5), p.getRelatedPapers());
    assertEqual(SetUtil.set(p2, p3, p4, p5), p.getCitations());
    assertEqual(SetUtil.set("author1"), p.getAuthors());
  }

  public void testParent() {

    Paper p = new Paper("abstract", 1995L, "title", "doi", "author", null);
    Paper p1 = new Paper("abstract", 1995L, "title", "doi", "author", p);
    Paper p2 = new Paper("abstract", 1995L, "title", "doi", SetUtil.set("author", "author2"), p);
    Paper pCitation = new Paper("ab", 1994L, "t", "d", "a", null);
    assertEqual(p1.getParent(), p);
    assertEqual(p2.getParent(), p);
    p.addCitation(pCitation);
    assertEqual(SetUtil.set(pCitation), p1.getCitations());
    assertEqual(SetUtil.set(pCitation), p2.getCitations());
    p.addRelatedPaper(pCitation);
    assertEqual(SetUtil.set(pCitation), p1.getRelatedPapers());
  }

  public void testUserPaperWithParentUserPaper() {

    Paper p = new Paper("abstract", 1995L, "title", "doi", "author", null);
    Paper p1 = new Paper("abstract", 1995L, "title", "doi", "author", p);
    Paper p2 = new Paper("abstract", 1995L, "title", "doi", SetUtil.set("author", "author2"), p1);
    assertEqual(p2.getParent(), p1);
  }

  public void testSelfCitingPaper() {

    Paper p = new Paper("abstract", 1995L, "title", "doi", "author", null);
    p.addCitation(p);
  }

  public TestPaper() {}

  public String toString() {

    return "TestPaper{}";
  }
}
