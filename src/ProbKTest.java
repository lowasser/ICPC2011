import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

public class ProbKTest extends TestCase {
  public void tester() {
    String testInput = "3\n0 0\n3 0\n0 4\n4\n0 10\n10 0\n20 10\n10 20\n0\n";
    System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printstream = new PrintStream(out);
    System.setOut(printstream);
    ProbK.main(new String[0]);
    printstream.flush();
    String output = new String(out.toByteArray());
    String[] split = output.split("\\s");
    assertEquals(split[0], "Case");
    assertEquals(split[1], "1:");
    assertTrue(Math.abs(Double.parseDouble(split[2]) - 2.40) <= 0.01);
    assertEquals(split[3], "Case");
    assertEquals(split[4], "2:");
    assertTrue(Math.abs(Double.parseDouble(split[5]) - 14.15) <= 0.01);
  }
}
