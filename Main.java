package loganalyzer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java -jar LogAnalyzer.jar <logfile>");
            return;
        }

        String logFile = args[0];
        String reportFile = "reports/report.txt";

        try {
            LogParser parser = new LogParser(logFile);

            IPAnalyzer ipAnalyzer = new IPAnalyzer();
            ipAnalyzer.analyze(parser.getEntries());

            ReportGenerator.generateReport(
                    reportFile,
                    parser.getEntries(),
                    ipAnalyzer
            );

            System.out.println("Log analysis complete.");
            System.out.println("Report saved to " + reportFile);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

