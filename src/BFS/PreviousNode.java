package BFS;

import BFS.Node;

/**
 * This class is used to store the previous node and the distance from the source node to the previous node.
 * @author johanneslorentzen
 */
public class PreviousNode {
  int distance;
  Node preNode;
  static int infinity = 1000000000; // 1 billion

  /**
   * Returns the distance from the source node to the previous node.
   *
   * @return the distance
   */
  public int findDistance() {
    return distance;
  }

  /**
   * Sets the previous node.
   *
   * @param node the previous node
   */
  public void setPreNode(Node node) {
    this.preNode = node;
  }

  /**
   * Sets the distance from the source node to the previous node.
   *
   * @param distance the distance
   */
  public void setDistance(int distance) {
    this.distance = distance;
  }

  /**
   * Returns the previous node.
   * @return previous node
   */
  public Node findPreNode() {
    return preNode;
  }

  /**
   * Constructor, initializes the distance to infinity.
   */
  public PreviousNode() {
    this.distance = infinity;
  }
}
