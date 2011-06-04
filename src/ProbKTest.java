import java.util.regex.Pattern;

import junit.framework.TestCase;

public class ProbKTest extends TestCase {
  public void testOnProvidedData() {
    String testInput = "3\n0 0\n3 0\n0 4\n4\n0 10\n10 0\n20 10\n10 20\n0\n";
    ICPCRunner.assertMatches(ProbK.class, testInput, "2" + Pattern.quote(".")
        + "(?:39|4[01])", "14" + Pattern.quote(".") + "1[4-6]");
  }
}
