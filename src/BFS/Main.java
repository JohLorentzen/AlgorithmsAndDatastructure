package BFS;

import BFS.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {

  public static void main(String[] args) {
    // Files for BFS
    String[] bfsFiles = {"ø6g1", "ø6g2", "ø6g3"};
    // Files for Topological Sort
    String[] topoFiles = {"ø6g5", "ø6g7"};

    // Test BFS
    for (String file : bfsFiles) {
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        Random rand = new Random();

        Graph graph = new Graph();
        graph.getGraph(br);
        int startNode = rand.nextInt(graph.numberOfNodes);
        graph.bfs(graph.nodes[startNode]);  // Assuming the start node is the first node
        graph.displayBFSResult();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // Test Topological Sort
    for (String file : topoFiles) {
      try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        Graph graph = new Graph();
        graph.getGraph(br);
        Node sortedList = graph.topologicalSort();
        graph.displayTopologicalOrder(sortedList);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
