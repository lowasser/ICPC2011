import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class ProbHTest extends TestCase {
  public void tester() {
    String testInput =
        "9\n1 3\n4 1\n3 5\n1 2\n2 6\n1 5\n6 3\n1 6\n3 2\n6\n"
            + "1 2\n1 3\n2 4\n2 5\n3 6\n3 7\n0\n";
    String expectedOutput = "Case 1: 2 4\nCase 2: 4 1\n";
    System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printstream = new PrintStream(out);
    System.setOut(printstream);
    ProbH.main(new String[0]);
    printstream.flush();
    String output = new String(out.toByteArray());
    assertEquals(expectedOutput, output);
  }
}
