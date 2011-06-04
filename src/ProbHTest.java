import junit.framework.TestCase;

public class ProbHTest extends TestCase {
  public void testOnProvidedData() {
    String testInput =
        "9\n1 3\n4 1\n3 5\n1 2\n2 6\n1 5\n6 3\n1 6\n3 2\n6\n"
            + "1 2\n1 3\n2 4\n2 5\n3 6\n3 7\n0\n";
    ICPCRunner.assertMatches(ProbH.class, testInput, "2 4", "4 1");
  }
}
