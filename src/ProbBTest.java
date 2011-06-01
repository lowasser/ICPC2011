import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class ProbBTest extends TestCase {
  public void tester() {
    String testInput =
        "3 0 4 0 1 4\n-2 -4 -1 3 3 -4\n0 1 1 1 2 1\n1 2 2 2 3 2\n"
            + "1 0 2 0 3 0\n3 3 1 1 2 2\n1 0 2 0 3 0\n3 2 1 1 2 2\n"
            + "2 3 0 6 1 2\n2 3 0 6 1 2\n0 0 0 0 0 0\n";
    String expectedOutput =
        "Case 1: equivalent solutions\nCase 2: inconsistent solutions\n"
            + "Case 3: no solution\nCase 4: inconsistent solutions\n"
            + "Case 5: equivalent solutions\n";
    System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printstream = new PrintStream(out);
    System.setOut(printstream);
    ProbB.main(new String[0]);
    printstream.flush();
    String output = new String(out.toByteArray());
    assertEquals(expectedOutput, output);
  }
}
