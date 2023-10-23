package CircularList;


/**
   * Represents a circular linked list.
   * @author johanneslorentzen
   */
  public class CircularList {

    private Node head = null;
    private Node tail = null;
    private int nrElements = 0;

    public int findNr() {return nrElements;}

    public Node findHead() {return head;}

    /**
     * Builds the circular linked list.
     *
     * @param value the value of the element contained in the new node.
     */
    public void addLast(double value) {
      Node newNode = new Node(value, null);

      if (head == null) {
        head = newNode;
      } else {
        tail.next = newNode;
      }

      tail = newNode;
      tail.next = head;
      nrElements ++;
    }

    /**
     * Deletes the node n
     *
     * @param n a specified node.
     * @return the specified node thisN as null with no links.
     */
    public Node deleteNode(Node n) {
      if (nrElements == 0) {
        return null;
      }

      Node last = null;
      Node thisN = head;
      while (thisN != null && thisN != n) {
        last = thisN;
        thisN = thisN.next;
      }
      if (last != head) last.next = thisN.next;
      else head = thisN.next;
      thisN = null;
      nrElements--;
      return thisN;
    }

  }
