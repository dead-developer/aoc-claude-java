import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1Part1 {
    public static void solve() {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        // Read the input file
        readInputFile( leftList, rightList);


        // Sort both lists
        Collections.sort(leftList);
        Collections.sort(rightList);

        // Calculate total distance
        long totalDistance = 0;
        for (int i = 0; i < leftList.size(); i++) {
            int distance = Math.abs(leftList.get(i) - rightList.get(i));
            totalDistance += distance;
        }

        System.out.println("Total distance: " + totalDistance);
    }

    private static  void readInputFile( List<Integer> leftList, List<Integer> rightList) {
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
            System.err.println("Error reading file");
            System.exit(1);
        }
    }


}