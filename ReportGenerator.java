package loganalyzer;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ReportGenerator {

    public static void generateReport(String outputPath,
                                      List<LogEntry> entries,
                                      IPAnalyzer ipAnalyzer) throws IOException {

        int total = entries.size();
        long errorCount = entries.stream()
                .filter(e -> e.getLevel().equalsIgnoreCase("ERROR"))
                .count();

        long warningCount = entries.stream()
                .filter(e -> e.getLevel().equalsIgnoreCase("WARNING"))
                .count();

        long infoCount = entries.stream()
                .filter(e -> e.getLevel().equalsIgnoreCase("INFO"))
                .count();

        long failedLogins = entries.stream()
                .filter(LogEntry::isFailedLogin)
                .count();

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(outputPath))) {

            writer.write("===== Log Analysis Report =====\n");
            writer.write("Total entries: " + total + "\n");
            writer.write("INFO: " + infoCount + "\n");
            writer.write("WARNING: " + warningCount + "\n");
            writer.write("ERROR: " + errorCount + "\n");
            writer.write("Failed login attempts: " + failedLogins + "\n\n");

            writer.write("Top IP addresses:\n");
            for (Map.Entry<String, Integer> entry :
                    ipAnalyzer.topIPs(5)) {
                writer.write(entry.getKey() + " -> "
                        + entry.getValue() + " times\n");
            }
        }
    }
}

