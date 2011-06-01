import java.util.*;

/**
 * Problem H: "Mining Your Own Business." ICPC World Finals 2011.
 * 
 * An articulation vertex of a connected graph is a vertex whose deletion
 * disconnects the graph. Clearly, if any (non-backtracking) path between
 * vertices u and v goes through an articulation vertex, all paths between u and
 * v go through that vertex.
 * 
 * Therefore, if we delete all articulation vertices from a connected graph G,
 * and u and v are now in different connected components, then there is a single
 * articulation vertex whose removal from G disconnects u from v; and if u and v
 * are still in the same connected component, there is no single vertex in G
 * whose removal disconnects u from v.
 * 
 * Therefore, the problem reduces to identifying the articulation vertices,
 * removing them, and identifying the connected components of the resulting
 * graph. Each component needs one and only one escape shaft, which can be put
 * on any of its vertices. Therefore, the number of escape shafts needed is the
 * number of components, and the number of possible ways to assign those escape
 * shafts is the product of the sizes of the components.
 * 
 * <a href="http://en.wikipedia.org/wiki/Articulation_vertex">Wikipedia</a>
 * provides a linear-time algorithm to identify articulation vertices, which
 * requires a single depth-first search.
 * 
 * @author Louis Wasserman, Assistant Coach, UChicago "Works in Theory"
 */
public class ProbH {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    for (int z = 1;; z++) {
      int n = input.nextInt();
      if (n == 0)
        break;
      Map<Integer, Set<Integer>> g = new HashMap<Integer, Set<Integer>>(n);
      for (int i = 0; i < n; i++) {
        int s = input.nextInt(), t = input.nextInt();
        adj(g, s).add(t);
        adj(g, t).add(s);
      }
      for (Integer art : articulationPoints(g))
        remove(g, art);
      Collection<Set<Integer>> components = components(g);
      long poss = 1;
      for (Set<Integer> c : components)
        poss *= c.size();
      System.out.println("Case " + z + ": " + components.size() + " " + poss);
    }
  }

  static <V> void remove(Map<V, Set<V>> g, V v) {
    Set<V> adj = g.remove(v);
    if (adj != null)
      for (V w : adj)
        g.get(w).remove(v);
  }

  private static <V> Collection<Set<V>> components(Map<V, Set<V>> g) {
    Map<V, Set<V>> copy = new LinkedHashMap<V, Set<V>>(g);
    List<Set<V>> comps = new ArrayList<Set<V>>();
    while (!copy.isEmpty())
      comps.add(dfsComponents(copy, copy.keySet().iterator().next(),
          new HashSet<V>()));
    return comps;
  }

  private static <V> Set<V> dfsComponents(Map<V, Set<V>> g, V v, Set<V> comp) {
    comp.add(v);
    Set<V> adj = g.remove(v);
    if (adj != null)
      for (V w : adj)
        dfsComponents(g, w, comp);
    return comp;
  }

  private static <V> Set<V> adj(Map<V, Set<V>> g, V s) {
    Set<V> sAdj = g.get(s);
    if (sAdj == null)
      g.put(s, sAdj = new HashSet<V>());
    return sAdj;
  }

  public static <V> Set<V> articulationPoints(Map<V, Set<V>> g) {
    V v = g.keySet().iterator().next();
    Map<V, Integer> depth = new HashMap<V, Integer>(g.size());
    Map<V, Integer> low = new HashMap<V, Integer>(g.size());
    Set<V> arts = new HashSet<V>();
    int rootChildren = 0;
    depth.put(v, 0);
    for (V w : g.get(v))
      if (dfs(g, w, depth, low, 1, arts))
        rootChildren++;
    if (rootChildren >= 2)
      arts.add(v);
    return arts;
  }

  public static <V> boolean dfs(Map<V, Set<V>> g, V v, Map<V, Integer> depth,
      Map<V, Integer> low, int d, Set<V> arts) {
    if (!depth.containsKey(v)) {
      depth.put(v, d);
      int lowpoint = d;
      int maxLow = 0;
      for (V w : g.get(v)) {
        if (dfs(g, w, depth, low, d + 1, arts)) {
          Integer wLow = low.get(w);
          lowpoint = Math.min(lowpoint, wLow);
          maxLow = Math.max(maxLow, wLow);
        } else
          lowpoint = Math.min(lowpoint, depth.get(w));
      }
      low.put(v, lowpoint);
      if (maxLow >= d)
        arts.add(v);
      return true;
    }
    return false;
  }
}
