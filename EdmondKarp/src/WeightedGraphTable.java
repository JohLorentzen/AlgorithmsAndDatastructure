import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class WeightedGraphTable {
    private int numberOfNodes;
    private int numberOfEdges;
    private WeightedEdgeTable edges[][]; // endre navn på klassen????

    /**
     * Creates a weighted graph table from a file.
     *
     * @param br the buffered reader to read from.
     * @throws IOException if file is not found
     */
    public void createWeightedGraphTable(BufferedReader br) throws IOException {
        // Standard
        StringTokenizer st = new StringTokenizer(br.readLine());

        numberOfNodes = Integer.parseInt(st.nextToken());

        edges = new WeightedEdgeTable[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; ++i) {
            for (int j = 0; j < numberOfNodes; ++j) {
                edges[i][j] = new WeightedEdgeTable();
            }
        }

        numberOfEdges = Integer.parseInt(st.nextToken());
        for (int i = 0; i < numberOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from][to].registerAsExisting();
            edges[from][to].addWeight(weight);

            if (!edges[to][from].doesExist()) {
                // Create a reverse edge with weight 0 if it doesn't exist
                edges[to][from].registerAsExisting();
                edges[to][from].addWeight(0);
            }

            // Set reverse edges for both (from, to) and (to, from)
            edges[from][to].setReverseEdge(edges[to][from]);
            edges[to][from].setReverseEdge(edges[from][to]);
        }
    }

    /**
     * Finds the max flow in a graph.
     *
     * @param startNode source
     * @param endNode sink
     */
    public void maxFlow(int startNode, int endNode) {
        int maxFlow = 0;
        int[] visited = new int[numberOfNodes];

        System.out.println("Økning : Flytøkende vei");

        // BFS loop
        while (true) {
            Arrays.fill(visited, -1);
            Queue queue = new Queue(numberOfNodes);
            queue.addToQueue(startNode);
            visited[startNode] = startNode;

            while (!queue.isEmpty()) {
                int fromNode = (int) queue.nextInQueue();
                for (int toNode = 0; toNode < numberOfNodes; ++toNode) {
                    if (visited[toNode] == -1 && edges[fromNode][toNode].getRestCapacity() > 0) {
                        visited[toNode] = fromNode;
                        queue.addToQueue(toNode);
                    }
                }
            }
            // If sink is not found break;
            if (visited[endNode] == -1) {
                break; // Didn't find endNode
            }

            int pathFlow = Integer.MAX_VALUE;
            int sink = endNode;
            // Find the path flow
            LinkedList<Integer> path = new LinkedList<>();
            // Update path and find flow
            while (sink != startNode) {
                int currentNode = visited[sink];
                path.addFirst(sink);
                pathFlow = Math.min(pathFlow, edges[currentNode][sink].getRestCapacity());
                sink = currentNode;
            }
            path.addFirst(startNode);

            System.out.println(pathFlow + " " + path.toString().replaceAll("[\\[\\],]", ""));
            // Update flow of edges
            sink = endNode;
            while (sink != startNode) {
                int currentNode = visited[sink];
                edges[currentNode][sink].addFlow(pathFlow);
                edges[sink][currentNode].addFlow(-pathFlow);
                sink = currentNode;
            }


            maxFlow += pathFlow;
        }

        System.out.println("Maksimal flyt ble " + maxFlow);

    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfNodes; ++i) {
            for (int j = 0; j < numberOfNodes; ++j) {
                sb.append(edges[i][j].getRestCapacity()).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 0; i < numberOfNodes; i++) {
        }

        return sb.toString();
    }
}
