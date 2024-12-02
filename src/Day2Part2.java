import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2Part2 {
    public static void solve() {
        int safeReports = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("puzzles/day2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                List<Integer> levels = parseLevels(line);
                if (isReportSafeWithDampener(levels)) {
                    safeReports++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Safe reports with dampener: " + safeReports);
    }

    private static List<Integer> parseLevels(String line) {
        String[] parts = line.trim().split("\\s+");
        List<Integer> levels = new ArrayList<>();
        for (String part : parts) {
            levels.add(Integer.parseInt(part));
        }
        return levels;
    }

    private static boolean isReportSafeWithDampener(List<Integer> levels) {
        if (levels.size() < 2) return true;

        // Check if report is safe without dampener
        if (isReportSafe(levels)) return true;

        // Try removing each level one at a time
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> modifiedLevels = new ArrayList<>(levels);
            modifiedLevels.remove(i);

            if (isReportSafe(modifiedLevels)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isReportSafe(List<Integer> levels) {
        if (levels.size() < 2) return true;

        boolean isIncreasing = false;
        int firstDiff = levels.get(1) - levels.get(0);

        if (firstDiff > 0) {
            isIncreasing = true;
        } else if (firstDiff < 0) {
            isIncreasing = false;
        } else {
            return false; // Equal numbers are not safe
        }

        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i) - levels.get(i - 1);

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }

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