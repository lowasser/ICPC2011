import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class ProbITest extends TestCase {
  public void tester() {
    String testInput = "4\n-3 5\n3 4\n-6 -2\n1 -5\n1\n0 -1\n-1\n";
    String expectedOutput = "Case 1: 4\nCase 2: never\n";
    System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printstream = new PrintStream(out);
    System.setOut(printstream);
    ProbI.main(new String[0]);
    printstream.flush();
    String output = new String(out.toByteArray());
    assertEquals(expectedOutput, output);
  }
}
