import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2Part1 {
    public static void solve() {
        int safeReports = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("puzzles/day2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                List<Integer> levels = parseLevels(line);
                if (isReportSafe(levels)) {
                    safeReports++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Safe reports: " + safeReports);
    }

    private static List<Integer> parseLevels(String line) {
        String[] parts = line.trim().split("\\s+");
        List<Integer> levels = new ArrayList<>();
        for (String part : parts) {
            levels.add(Integer.parseInt(part));
        }
        return levels;
    }

    private static boolean isReportSafe(List<Integer> levels) {
        if (levels.size() < 2) return true;

        boolean isIncreasing = false;
        int firstDiff = levels.get(1) - levels.get(0);

        // Determine if sequence should be increasing or decreasing
        if (firstDiff > 0) {
            isIncreasing = true;
        } else if (firstDiff < 0) {
            isIncreasing = false;
        } else {
            return false; // Equal numbers are not safe
        }

        // Check each adjacent pair
        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i) - levels.get(i - 1);

            // Check if difference is within bounds (1-3)
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }

            // Check if direction matches the sequence
            if ((isIncreasing && diff <= 0) || (!isIncreasing && diff >= 0)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        solve();
    }
}