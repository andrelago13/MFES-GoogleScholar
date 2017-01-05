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
    Paper pc = p.cg_clone(false);
    assertEqual(p.getAbstract(), pc.getAbstract());
    assertEqual(p.getPublicationDate(), pc.getPublicationDate());
    assertEqual(p.getTitle(), pc.getTitle());
    assertEqual(p.getDOI(), pc.getDOI());
    assertEqual(p.getAuthors(), pc.getAuthors());
  }

  public TestPaper() {}

  public String toString() {

    return "TestPaper{}";
  }
}
