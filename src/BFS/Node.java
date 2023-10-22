package BFS;

import BFS.Edge;

public class Node {
  private Edge firstEdge;
  private Object nodeData;

  /**
   * Constructor.
   *
   * @param nodeData the data that gets stored in the node object.
   */
  public Node(int nodeData) {
    this.nodeData = nodeData;
  }

  public Object getNodeData() {
    return nodeData;
  }

  public void setNodeData(Object nodeData) {
    this.nodeData = nodeData;
  }

  /**
   * Returns the first edge stored in the node.
   *
   * @return the first edge.
   */
  public Edge getFirstEdge() {
    return this.firstEdge;
  }

  /**
   * Takes an edge object as argument and sets the node objects' firstEdge to this edge object.
   *
   * @param firstEdge the edge object that gets stored in firstEdge field.
   */
  public void setFirstEdge(Edge firstEdge) {
    this.firstEdge = firstEdge;
  }

  /**
   * Returns the node data as a string.
   * For use in toString methods.
   *
   * @return the node object data as a string.
   */
  public String getNodeDataAsString() {
    return nodeData + "";
  }


  /**
   * Returns an empty string if firstEdge is null.
   * If not it returns the firstEdge's to string.
   *
   * @return string representation of firstEdge.
   */
  @Override
  public String toString() {
    if (firstEdge == null) return "";
    return firstEdge.toString();
  }
}
