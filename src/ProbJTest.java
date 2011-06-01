import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class ProbJTest extends TestCase {
  public void tester() {
    String testInput = "29\n28\n0\n";
    String expectedOutput = "Case 1: 3H 3L 2H\nCase 2: impossible\n";
    System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printstream = new PrintStream(out);
    System.setOut(printstream);
    ProbJ.main(new String[0]);
    printstream.flush();
    String output = new String(out.toByteArray());
    assertEquals(expectedOutput, output);
  }
}
