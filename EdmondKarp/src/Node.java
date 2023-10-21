import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Node {
    private List<Node> nodeList;
    private List<WeightedEdgeTable> edgesFromNode;

    int nodeIdentifier;

    Object nodeData;

    public Node (int nodeIdentifier) {
        this.nodeIdentifier = nodeIdentifier;
        this.nodeList = new ArrayList<>();
        this.edgesFromNode = new ArrayList<>();
    }

    public Object getNodeData() {
        return this.nodeData;
    }

    public void setNodeData(Object data) {
        this.nodeData = data;
    }

    /**
     * Returns the node data as a string.
     * For use in toString methods.
     *
     * @return the node object data as a string.
     */
    public String getNodeDataAsString() {
        return nodeIdentifier + "";
    }

    public int getNodeIdentifier() {
        return (int) this.nodeIdentifier;
    }

    public ArrayList<Node> getConnectedToNodes() {
        return (ArrayList<Node>) this.nodeList;
    }

    public Node getConnectedToNode(int index) {
        if (nodeList.isEmpty()) return null;
        return nodeList.get(index);
    }

    public ArrayList<WeightedEdgeTable> getEdgesFromNode() {
        if (edgesFromNode.isEmpty()) return null;
        return (ArrayList<WeightedEdgeTable>) this.edgesFromNode;
    }

    public WeightedEdgeTable getEdgeFromNode(int index) {
        return this.edgesFromNode.get(index);
    }

    public void setConnectedToNodes(WeightedEdgeTable[] edgesToNodes, Node[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            if (edgesToNodes[i].doesExist()) {
                nodeList.add(nodes[i]);
                edgesFromNode.add(edgesToNodes[i]);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodeList.size(); i++) {
            sb.append(nodeList.get(i).nodeIdentifier).append(" ");
        }
        return sb.toString();
    }
}
