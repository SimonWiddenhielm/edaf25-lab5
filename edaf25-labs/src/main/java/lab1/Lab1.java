package lab1;

import graph.Graph;
import graph.SimpleGraph;

public class Lab1 {
  /**
   * Returns the number of vertices in the graph g.
   */
  public static int vertexCount(Graph<Integer> g) {
    // TODO(D1): implement this!
    return 0;
  }

  /**
   * Returns the number of edges in the graph g.
   */
  public static int edgeCount(Graph<Integer> g) {
    // TODO(D2): implement this!
    return 0;
  }

  /**
   * Returns true if there is an edge from vertex u to vertex v.
   * Returns false if u and v are not connected or if there is only an edge from v to u.
   *
   * @param g a graph containing u and v
   * @param u index of the first vertex in g
   * @param v index of the second vertex in g
   */
  public static boolean edgeBetween(Graph<Integer> g, int u, int v) {
    // TODO(D3): implement this!
    return false;
  }

  /**
   * Returns a simple graph with at least 6 vertices and at least 10 edges.
   */
  public static Graph<Integer> buildGraph() {
    // TODO(D5): implement this!
    return new SimpleGraph(1);
  }
}
