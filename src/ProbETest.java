import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class ProbETest extends TestCase {
  public void tester() {
    String testInput = "4 4 5 3\n1 1\n1 2\n3 3\n4 4\n2 4\n1\n2\n4\n0 0 0 0\n";
    String expectedOutput = "Case 1:\n3 (3,4)\n4 (2,2)\n5 (3,1)\n";
    System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printstream = new PrintStream(out);
    System.setOut(printstream);
    ProbE.main(new String[0]);
    printstream.flush();
    String output = new String(out.toByteArray());
    assertEquals(expectedOutput, output);
  }
}
