package EdmondKarp;

public class WeightedEdgeTable {
    private int exists;
    private int weight;
    private int capacity;
    private int flow;
    private WeightedEdgeTable reverseEdge;

    public WeightedEdgeTable() {
        this.exists = 0;
        this.flow = 0;
    }

    public void addFlow(int flow) {
        this.flow += flow;
    }

    public void registerAsExisting() {
        this.exists = 1;
    }

    public boolean doesExist() {
        return exists == 1;
    }

    public void addWeight(int weight) {
        this.weight = weight;
        this.capacity = weight; // Initialize capacity based on weight
    }

    public void setReverseEdge(WeightedEdgeTable reverseEdge) {
        this.reverseEdge = reverseEdge;
    }

    public boolean hasReverseEdge() {
        return reverseEdge != null;
    }

    public int getRestCapacity() {
        return capacity - flow;
    }

    public void updateRestCapacity() {
        capacity = weight - flow;
    }

    public int getFlow() {
        return this.flow;
    }
}