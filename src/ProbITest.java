import junit.framework.TestCase;

public class ProbITest extends TestCase {
  public void testOnProvidedData() {
    String testInput = "4\n-3 5\n3 4\n-6 -2\n1 -5\n1\n0 -1\n-1\n";
    ICPCRunner.assertMatches(ProbI.class, testInput, 4, "never");
  }
}
