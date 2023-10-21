    /**
     * Class represents a weighted edge in a graph and extends the class edge.
 * @author johanneslorentzen
 */
public class WeightedEdge extends Edge{

    private int weight;
    private int flow; // The flow of the edge.
    private int restCapacity; // The Capacity - the flow.
    private WeightedEdge reverseEdge; // The reverse edge of this edge.


    /**
     * Constructor.
     *
     * @param node the node the edge's end point connects with.
     * @param next is null at initialization.
     */
    public WeightedEdge(Node node, Edge next, int weight) {
        super(node, next);
        this.weight = weight;
        this.flow = 0;
    }

    public void setReverseEdge(WeightedEdge reverseEdge) {
        this.reverseEdge = reverseEdge;
    }

    public WeightedEdge getReverseEdge() {
        return this.reverseEdge;
    }

    public int getRestCapacity() {
        restCapacity = weight - flow;
        return restCapacity;
    }
    public void setFlow(int flow) {
        //TODO update flow to += argument?
        this.flow = flow;
    }

    public int getFlow() {
        return flow;
    }

    public int getWeight() {
        return weight;
    }
}