package CircularList;

/**
 * Represents an element in a linked list.
 * @author johanneslorentzen
 */
public class Node {

  double element;
  Node next;

  public Node( double e, Node n) {
    element = e;
    next = n;
  }
}
