import java.util.LinkedList;
import java.util.Stack;

import javax.swing.tree.TreeNode;

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

  public void printShortestPaths(){
    System.out.println("The Shortest Path from each node to every other node (except for itself) follow:");
    //for each vertices
    for (int i = 0; i < vertices; i++){
      LinkedList<Edge> list = adjacencylist[i];
      //for each destination
      for (int j = 0; j < vertices; j++){
        if (i == j) continue;
        System.out.print("The shortest path from " + i + " to " + j + " is: ");
        if (DFS(i, 0, j, list) == true){
          System.out.println("The length");
        } else {
          System.out.println("No path");
        }
      }
    }
  }

  //check if there is a path to the target
  public boolean DFS(int source, int pathLength, int target, LinkedList<Edge> adj){
    if (adj.isEmpty()) return false;
    //add up node values, currSum carries over to the children calls
    if (adj.get)
    pathLength += 1;
    if (node.left == null && node.right == null){
        return (currSum == targetSum);
    }
    return (DFS(node.left, currSum, targetSum) || DFS(node.right, currSum, targetSum));
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
  //graph.printShortestPaths() // TODO add method to print shortest paths
  }
}