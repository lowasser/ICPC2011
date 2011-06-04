import junit.framework.TestCase;

public class ProbJTest extends TestCase {
  public void testOnProvidedData() {
    String testInput = "29\n28\n0\n";
    ICPCRunner.assertMatches(ProbJ.class, testInput, "3H 3L 2H", "impossible");
  }
}
