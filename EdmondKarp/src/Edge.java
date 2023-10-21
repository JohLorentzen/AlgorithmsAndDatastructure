public class Edge {
    private Node toNode;
    private Edge nextEdge;

    /**
     * Constructor.
     *
     * @param node the node the edge's end point connects with.
     * @param next is null at initialization.
     */
    public Edge(Node node, Edge next) {
        this.toNode = node;
        this.nextEdge = next;
    }

    /**
     * Returns the node that this edge's end point connects with.
     *
     * @return the node that this edge's end point connect with.
     */
    public Node getToNode() {
        return this.toNode;
    }

    /**
     * Returns the next edge that the original node connects with.
     * Returns null if there are no more edges in the current node.
     *
     * @return the next edge or null.
     */
    public Edge getNextEdge() {
        return this.nextEdge;
    }


    /**
     * Recursive toString method.
     * Base case that checks if the nextEdge is null. If it is it will return the current toNode as string.
     * Else it will return the current toNode as string concatenated with the nextEdge's toString.
     *
     * @return all connections of the original node.
     */

    /*
    @Override
    public String toString() {
        if (nextEdge == null) return toNode.getNodeDataAsString();
        return toNode.getNodeDataAsString() + " " + nextEdge.toString();
    }
    */
}
