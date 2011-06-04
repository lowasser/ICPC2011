import java.util.*;

/**
 * Problem J: "Pyramids." ICPC World Finals 2011.
 * 
 * This problem can be solved with dynamic programming. Note that there are at
 * most 198 possible pyramids which could ever be produced, and p[j] is the jth
 * smallest pyramid. Let sol[i][j] be the best solution (according to the
 * Pharaoh) that uses exactly i blocks, and restricts itself to the smallest (by
 * volume) j pyramids. This array needs dimensions 10^6 by 198, which is quite
 * manageable. Clearly, sol[i][j] = best(p[j] + sol[i-p[j].volume][j-1],
 * sol[i][j-1]), which can be computed in constant time. Therefore, if c blocks
 * are available, the answer is sol[c][198].
 * 
 * @author Louis Wasserman, Assistant Coach, UChicago "Works in Theory"
 */
public class ProbJ {
  static class Pyramid implements Comparable<Pyramid> {
    final int height;
    final int volume;
    final int base;
    final boolean high;

    Pyramid(int height, int volume, int base, boolean high) {
      this.height = height;
      this.volume = volume;
      this.high = high;
      this.base = base;
    }

    @Override public int compareTo(Pyramid o) {
      int cmp = o.volume - volume;
      return (cmp == 0) ? base - o.base : cmp;
    }

    public String toString() {
      return base + (high ? "H" : "L");
    }
  }

  static List<Pyramid> pyramids;
  static {
    pyramids = new ArrayList<Pyramid>(100);
    int blocks = 1;
    int lowblocks = 1;
    for (int h = 2; blocks < 100000; h++) {
      blocks += h * h;
      pyramids.add(new Pyramid(h, blocks, h, true));
      pyramids.add(new Pyramid(h, 4 * blocks, 2 * h, false));
      int lowWidth = 2 * h - 1;
      lowblocks += lowWidth * lowWidth;
      pyramids.add(new Pyramid(h, lowblocks, lowWidth, false));
    }
    Collections.sort(pyramids);
    Collections.reverse(pyramids);
  }

  static class Solution {
    Pyramid pyramid;
    Solution prev;

    Solution(Pyramid pyramid, Solution prev) {
      this.pyramid = pyramid;
      this.prev = prev;
    }

    private void append(StringBuilder builder) {
      builder.append(pyramid);
      if (prev != null) {
        builder.append(' ');
        prev.append(builder);
      }
    }

    public String toString() {
      StringBuilder builder = new StringBuilder();
      append(builder);
      return builder.toString();
    }
  }

  static Collection<Pyramid> pyramidsUpTo(int volume) {
    int index =
        Collections.binarySearch(pyramids,
            new Pyramid(1000000, volume, 0, true));
    return pyramids.subList((index >= 0) ? index : -1 - index, pyramids.size());
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Solution[][] solutions = new Solution[100001][];
    int lastDone = 0;
    for (int z = 1;; z++) {
      int c = scanner.nextInt();
      if (c == 0)
        break;
      for (int i = lastDone + 1; i <= c; i++) {
        solutions[i] = new Solution[pyramids.size()];
        Solution s = null;
        for (int j = 0; j < pyramids.size(); j++) {
          Pyramid p = pyramids.get(j);
          int remaining = i - p.volume;
          if (remaining > 0 && j > 0 && solutions[remaining][j - 1] != null)
            solutions[i][j] = s = new Solution(p, solutions[remaining][j - 1]);
          else if (remaining == 0)
            solutions[i][j] = s = new Solution(p, null);
          else if (remaining < 0)
            break;
          else
            solutions[i][j] = s;
        }
      }
      lastDone = Math.max(lastDone, c);
      Solution best = null;
      for (int j = 0; j < pyramids.size(); j++)
        if (solutions[c][j] != null)
          best = solutions[c][j];
      System.out.println("Case " + z + ": "
          + ((best == null) ? "impossible" : best.toString()));
    }
  }
}
