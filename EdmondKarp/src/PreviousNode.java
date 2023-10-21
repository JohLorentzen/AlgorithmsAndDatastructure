public class PreviousNode {
    private int distance;
    private Node preNode;
    static int infinity = 1000000000; // 1 billion

    public PreviousNode() {
        this.distance = infinity;
    }

    public Node getPreNode() {
        return this.preNode;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPreNode(Node node) {
        this.preNode = node;
    }
}
