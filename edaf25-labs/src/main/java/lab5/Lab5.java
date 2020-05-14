package lab5;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab5 {
  private static Scanner sc;

/**
   * Computes the maximum flow for a flow network.
   * @param g a graph with edge capacities and a source and sink
   * @return shortest distance between start and end
   */
 public static int maxFlow(FlowGraph g, int source, int sink) {
	 int[][] residual = new int[g.vertexCount()][g.vertexCount()];
	 int u, v;
	 for(u = 0; u < residual.length;u++) {
		 for (v = 0; v < residual.length; v++) {
			 residual[u][v] = g.getCapacity(u,v);
		 }
	 }
	 int max_flow = 0;
	 int pred[] = new int[g.vertexCount()];
	 
	 while(bfs(g.vertexCount(),source,sink,residual,pred))   {
          // Find minimum residual capacity of the edhes
          // along the path filled by BFS. Or we can say
          // find the maximum flow through the path found.
          int path_flow = Integer.MAX_VALUE;
          for (v=sink; v!=source; v=pred[v]) {
              u = pred[v];
              path_flow = Math.min(path_flow, residual[u][v]);
          }

          // update residual capacities of the edges and
          // reverse edges along the path
          for (v=sink; v != source; v=pred[v]) {
              u = pred[v];
              residual[u][v] -= path_flow;
              residual[v][u] += path_flow;
          }

          // Add path flow to overall flow
          max_flow += path_flow;
      }

      // Return the overall flow
      return max_flow;
  } 
 
 private static  boolean bfs(int numVertex, int start, int end, int[][] residual, int[] pred) {
	 

	    boolean[]visited = new boolean[numVertex]; 
	    
	    for( int i = 0; i < numVertex;i++) 
	    	visited[i] = false;
	    
	
        // source vertex as visited 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        queue.add(start); 
        visited[start] = true; 
        pred[start]=-1; 
  
        
        
        // Standard BFS Loop 
        while (queue.size()!=0) { 
            int u = queue.poll(); 
  
            for (int v=0; v<numVertex; v++) 
            { 
                if (visited[v]==false && residual[u][v] > 0) 
                { 
                    queue.add(v); 
                    pred[v] = u; 
                    visited[v] = true; 
                } 
            } 
        } 
  
        // If we reached sink in BFS starting from source, then 
        // return true, else false 
        return (visited[end] == true); 
	    	

 }

  /**
   * Read a flowgraph from a file.
   */
  public static FlowGraph loadFlowGraph(Path path) throws IOException {
	  
	  
	  sc = new Scanner(path);
	  
	  int vertexCount = sc.nextInt(); //	  • På första raden i filen finns ett heltal som anger antalet noder i grafen (n).
	  
	  int edges = sc.nextInt(); //	  • Därefter följer en rad med ett heltal som anger antalet bågar i grafen (m)
	  
	  FlowEdge[] flowEdges = new FlowEdge[edges];
	  int i = 0;
	  
      while (sc.hasNext()) {
    	  
    	 int u,v,c;
    	 u = sc.nextInt();
    	 v = sc.nextInt();
    	 c = sc.nextInt();
    	 
    	 
    	 
    	 if(c == -1) {
    		 
    		 c = Integer.MAX_VALUE;
    		 
    	 }
    	
    	FlowEdge fl = new FlowEdge(u,v,c);
    	flowEdges[i] = fl;
    	i++;
    	
      }
      
      FlowGraph flow = new FlowGraph(vertexCount,flowEdges);
      
      sc.close();

    return flow;
  }
}
