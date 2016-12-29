package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TestPaper extends MyTestCase {
  public Scholar generalScn() {

    Scholar scholar = new Scholar();
    return scholar;
  }

  public void testAll() {

    IO.println("Starting 'Paper' tests.");
    IO.println("\ttestGetNumCitedBy() starting.");
    testGetNumCitedBy();
    IO.println("\ttestGetNumCitedBy() ended.");
    IO.println("\ttestPapersFromAuthor() starting.");
    testPapersFromAuthor();
    IO.println("\ttestPapersFromAuthor() ended.");
    IO.println("'Paper' tests done.");
  }

  public void testGetNumCitedBy() {

    Paper paper = new Paper("", 0L, "", "");
    Paper p1 = new Paper("", 0L, "", "");
    Paper p2 = new Paper("", 0L, "", "");
    Paper p3 = new Paper("", 0L, "", "");
    Paper p4 = new Paper("", 0L, "", "");
    Paper p5 = new Paper("", 0L, "", "");
    Paper p6 = new Paper("", 0L, "", "");
    Paper p7 = new Paper("", 0L, "", "");
    Paper p8 = new Paper("", 0L, "", "");
    Paper p9 = new Paper("", 0L, "", "");
    Paper p10 = new Paper("", 0L, "", "");
    Paper p11 = new Paper("", 0L, "", "");
    Paper p12 = new Paper("", 0L, "", "");
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
    Paper p1 = new Paper("", 0L, "", author);
    Paper p2 = new Paper("", 0L, "", "");
    Paper p3 = new Paper("", 0L, "", "");
    Paper p4 = new Paper("", 0L, "", author);
    Paper p5 = new Paper("", 0L, "", "");
    Paper p6 = new Paper("", 0L, "", "");
    Paper p7 = new Paper("", 0L, "", "");
    Paper p8 = new Paper("", 0L, "", author);
    Paper p9 = new Paper("", 0L, "", "");
    Paper p10 = new Paper("", 0L, "", "");
    Paper p11 = new Paper("", 0L, "", author);
    Paper p12 = new Paper("", 0L, "", "");
    VDMSet papers = SetUtil.set(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);
    assertEqual(SetUtil.set(p1, p4, p8, p11), Paper.papersFromAuthor(Utils.copy(papers), author));
  }

  public TestPaper() {}

  public String toString() {

    return "TestPaper{}";
  }
}
