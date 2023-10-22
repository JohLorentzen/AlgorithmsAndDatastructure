package BFS;

import BFS.Edge;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Graph {

  int numberOfNodes;
  int numberOfEdges;

  Node[] nodes;

  /**
   * Initializes the previous node data for all nodes in the graph.
   *
   * @param startNode given start node
   */
  public void initPrevious(Node startNode) {
    for (int i = numberOfNodes-1; i >= 0; --i) {
      nodes[i].setNodeData(new PreviousNode());
    }
    ((PreviousNode)startNode.getNodeData()).setDistance(0); // sets distance to 0
  }

  /**
   * Breadth First Search
   *
   * @param startNode given start node
   */
  public void bfs(Node startNode){
    initPrevious(startNode);
    Queue queue = new Queue(numberOfNodes - 1);
    queue.addToQueue(startNode);
    while (!queue.isEmpty()){
      Node n = (Node) queue.nextInQueue();
      for (Edge e = n.getFirstEdge(); e != null; e = e.getNextEdge()) {
        PreviousNode preNode = (PreviousNode) e.getToNode().getNodeData();
        if (preNode.findDistance() == PreviousNode.infinity){
          preNode.setDistance(((PreviousNode) n.getNodeData()).findDistance() + 1);
          preNode.setPreNode(n);
          queue.addToQueue(e.getToNode());
        }
      }
    }
  }

  /**
   * Topological Sort lit class.
   */
  class Topo_lst {
    boolean found;
    Node next;
  }

  /**
   * Depth First Search for Topological Sort
   *
   * @param n node n
   * @param l node l
   * @return node n or node l
   */
  Node df_topo(Node n, Node l) {
    Topo_lst nd = (Topo_lst) n.getNodeData();
    if(nd.found) return l;
    nd.found = true;
    for(Edge e = n.getFirstEdge(); e != null; e = e.getNextEdge()) {
      l = df_topo(e.getToNode(), l);
    }
    nd.next = l;
    return n;
  }

  /**
   * Topological Sort
   *
   * @return node l
   */
  Node topologicalSort() {
    Node l = null;
    for(int i = numberOfNodes; i-->0;) {
      nodes[i].setNodeData(new Topo_lst());
    }
    for (int i = numberOfNodes; i-->0;) {
      l = df_topo(nodes[i], l);
    }
    return l;
  }

  /**
   * Reads the graph from the given BufferedReader.
   *
   * @param br bufferedReader
   * @throws IOException if the file is not found
   */
  public void getGraph(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    numberOfNodes = Integer.parseInt(st.nextToken());
    nodes = new Node[numberOfNodes];
    for (int i = 0; i < numberOfNodes; ++i) nodes[i] = new Node(i);
    numberOfEdges = Integer.parseInt(st.nextToken());
    for (int i = 0; i < numberOfEdges; ++i) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      Edge e = new Edge(nodes[to], nodes[from].getFirstEdge());
      nodes[from].setFirstEdge(e);
    }
  }

  /**
   * Displays the result of the BFS.
   */
  public void displayBFSResult() {
    System.out.println("Node | Previous | Distance to Start Node");
    for (int i = 0; i < numberOfNodes; i++) {
      Node node = nodes[i];
      PreviousNode prevNodeData = (PreviousNode) node.getNodeData();
      int distance = prevNodeData.findDistance();
      Node prevNode = prevNodeData.findPreNode();
      String prevNodeIndex = "None";
      if (prevNode != null) {
        for (int j = 0; j < numberOfNodes; j++) {
          if (nodes[j] == prevNode) {
            prevNodeIndex = Integer.toString(j);
            break;
          }
        }
      }
      System.out.println(i + " | " + prevNodeIndex + " | " + distance);
    }
  }

  /**
   * Displays the topological order of the graph.
   * @param startNode
   */
  public void displayTopologicalOrder(Node startNode) {
    System.out.println("Topological Order:");
    Node current = startNode;
    while (current != null) {
      int index = -1;
      for (int i = 0; i < numberOfNodes; i++) {
        if (nodes[i] == current) {
          index = i;
          break;
        }
      }
      System.out.print(index + " ");
      Topo_lst topoData = (Topo_lst) current.getNodeData();
      current = topoData.next;
    }
    System.out.println();
  }

}
