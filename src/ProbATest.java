import junit.framework.TestCase;

public class ProbATest extends TestCase {
  public void testOnProvidedData() {
    String testInput =
        "1 2 2 3 10 20\n1 3 2 3 22 33\n3 2 2 3 4 5\n5 3 2 3 2 3\n0 0 0 0 0 0";
    ICPCRunner.assertMatches(ProbA.class, testInput, "1A 2M",
        "1M 2A 1M", "impossible", "empty");
  }
}
