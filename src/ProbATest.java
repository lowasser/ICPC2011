import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class ProbATest extends TestCase {
  public void tester() {
    String testInput =
        "1 2 2 3 10 20\n1 3 2 3 22 33\n3 2 2 3 4 5\n5 3 2 3 2 3\n0 0 0 0 0 0";
    String expectedOutput =
        "Case 1: 1A 2M\nCase 2: 1M 2A 1M\nCase 3: impossible\nCase 4: empty\n";
    System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printstream = new PrintStream(out);
    System.setOut(printstream);
    ProbA.main(new String[0]);
    printstream.flush();
    String output = new String(out.toByteArray());
    assertEquals(expectedOutput, output);
  }
}
