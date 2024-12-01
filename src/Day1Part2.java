import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1Part2 {
    public static void solve() {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        // Read the input file
        readInputFile(leftList, rightList);

        // Create a frequency map for the right list
        Map<Integer, Integer> rightListFrequency = new HashMap<>();
        for (int num : rightList) {
            rightListFrequency.put(num, rightListFrequency.getOrDefault(num, 0) + 1);
        }

        // Calculate similarity score
        long similarityScore = 0;
        for (int leftNum : leftList) {
            // Get frequency of the left number in right list (0 if not present)
            int frequency = rightListFrequency.getOrDefault(leftNum, 0);
            // Multiply number by its frequency and add to total
            similarityScore += (long) leftNum * frequency;
        }

        System.out.println("Similarity score: " + similarityScore);
    }

    private static void readInputFile(List<Integer> leftList, List<Integer> rightList) {
        try (BufferedReader reader = new BufferedReader(new FileReader("puzzles/day1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    leftList.add(Integer.parseInt(parts[0]));
                    rightList.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading puzzle file: " + e.getMessage());
            System.exit(1);
        }
    }
}