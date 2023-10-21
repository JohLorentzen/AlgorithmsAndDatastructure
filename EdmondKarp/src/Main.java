import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String[] flytgrafFiles = {"flytgraf1", "flytgraf2", "flytgraf3", "flytgraf4", "flytgraf5"};

        int[] endNodes = {7, 1, 1, 7, 7};
        int i = 0;
        //For each file in flytgrafFiles
        for (String file : flytgrafFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                // Create a new graph
                WeightedGraphTable graph = new WeightedGraphTable();
                graph.createWeightedGraphTable(br);
                // Find max flow
                graph.maxFlow(0, endNodes[i]);
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
