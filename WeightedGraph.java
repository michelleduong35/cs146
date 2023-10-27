//NAME: MICHELLE DUONG
//CLASS: CS146
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import java.util.Stack; (never used)

//import javax.swing.tree.TreeNode; (never used)

// NOTE: Courtesy of: https://tutorialhorizon.com/algorithms/weighted-graph-implementation-java/
// The accompanying documentation and diagram and video on that site WILL be helpful
// so please study them carefully
public class WeightedGraph {
  static class Edge {
    int source;
    int destination;
    int weight;
      public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
      }
  }

static class Graph {
  int vertices;
  int distance;
  LinkedList<Edge> [] adjacencylist;
  Graph(int vertices) {
    this.vertices = vertices;
    adjacencylist = new LinkedList[vertices];
    //initialize adjacency lists for all the vertices
    for (int i = 0; i <vertices ; i++) {
      adjacencylist[i] = new LinkedList<>();
    }
  }
  public void addEdge(int source, int destination, int weight) {
    Edge edge = new Edge(source, destination, weight);
    adjacencylist[source].addFirst(edge); //for directed graph
  }
  public void printGraph(){
    for (int i = 0; i <vertices ; i++) {
      LinkedList<Edge> list = adjacencylist[i];
      for (int j = 0; j <list.size() ; j++) {
        System.out.println("vertex-" + i + " is connected to " +
        list.get(j).destination + " with weight " +
        list.get(j).weight);
      }
    }
  }

  //create adjacency matrix
  public void printAdjacencyMatrix(){
    System.out.println("The Adjacency Matrix for the weighted graph is:");
    //create the matrix
    int[][] matrix = new int[vertices][vertices];
    //iterate through vertices and their adj list
    for (int i = 0; i < vertices; i++){
      LinkedList<Edge> list = adjacencylist[i];
      //iterate through destinations in vertex list
      for (int j = 0; j < list.size(); j++) {
        //if linked list for vertex(i) has source(j) add weight to the matrix
        matrix[i][list.get(j).destination] = list.get(j).weight;
      }
    }
    //print the matrix
    System.out.println("  0 1 2 3 4 5");
    for (int i = 0; i < vertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < vertices; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public void printAdjacencyList(){
    System.out.println("The (Outcoming) Adjacency List for the weighted graph is:");
    for (int i = 0; i < vertices; i++){
      LinkedList<Edge> list = adjacencylist[i];
      System.out.print(i + " -> ");
      for (int j = 0; j < list.size(); j++) {
        System.out.print(list.get(j).destination + " ");
      }
      System.out.println();
    }
  }

  //NOTE: Referenced from WilliamFiset on Youtube for shortestPaths
  public void printShortestPaths(){
    System.out.println("The Shortest Path from each node to every other node (except for itself) follow:");
    //THIS PORTION FOR SORTING AND CREATING DISTANCE ARRAY
    Map<Integer, List<Edge>> graph = new HashMap<>();
    for (int i = 0; i < vertices; i++){
      graph.put(i, adjacencylist[i]);
    }

    for (int i = 0; i < vertices; i++){
      Integer[] dist = helper(graph, i, 6);
      //for each destination
      for (int j = 0; j < vertices; j++){
        if (i == j) continue; // if it is comparing itself, move on
        System.out.print("The shortest path from " + i + " to " + j + " is: ");
        if (dist[j] == null){
          System.out.println("No path");
        }else{
          System.out.println(dist[j]);
        }
      }
    }
  }
  //this helper function creates the distance arr for shortest path
  //from starting node, get shortest distance to every node. null if not reachable
  public static Integer[] helper(Map<Integer, List<Edge>> graph, int start, int vertices) {

    int[] topsort = topologicalSort(graph, vertices);
    Integer[] dist = new Integer[vertices];
    dist[start] = 0;

    for (int i = 0; i < vertices; i++) {

      int nodeIndex = topsort[i];
      if (dist[nodeIndex] != null) {
        List<Edge> adjacentEdges = graph.get(nodeIndex);
        if (adjacentEdges != null) {
          for (Edge edge : adjacentEdges) {

            int newDist = dist[nodeIndex] + edge.weight;
            if (dist[edge.destination] == null) dist[edge.destination] = newDist;
            else dist[edge.destination] = Math.min(dist[edge.destination], newDist);
          }
        }
      }
    }

    return dist;
  }

  //helper dfs for topologicalSort
  private static int dfs(
      int i, int at, boolean[] visited, int[] ordering, Map<Integer, List<Edge>> graph) {

    visited[at] = true;

    List<Edge> edges = graph.get(at);

    if (edges != null)
      for (Edge edge : edges) if (!visited[edge.destination]) i = dfs(i, edge.destination, visited, ordering, graph);

    ordering[i] = at;
    return i - 1;
  }

  //sort the nodes!
  public static int[] topologicalSort(Map<Integer, List<Edge>> graph, int numNodes){
    int[] ordering = new int[numNodes];
    boolean[] visited = new boolean[numNodes];

    int i = numNodes - 1;
    for (int at = 0; at < numNodes; at++)
      if (!visited[at]) i = dfs(i, at, visited, ordering, graph);

    return ordering;
  }
}

  public static void main(String[] args) {
  int vertices = 6;
  Graph graph = new Graph(vertices);
  graph.addEdge(0, 1, 4);
  graph.addEdge(0, 2, 3);
  graph.addEdge(1, 3, 2);
  graph.addEdge(1, 2, 5);
  graph.addEdge(2, 3, 7);
  graph.addEdge(3, 4, 2);
  graph.addEdge(4, 0, 4);
  graph.addEdge(4, 1, 4);
  graph.addEdge(4, 5, 6);
  //graph.printGraph(); 
  graph.printAdjacencyMatrix(); 
  graph.printAdjacencyList();
  graph.printShortestPaths();
  }
}