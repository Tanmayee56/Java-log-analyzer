package loganalyzer;

import java.io.*;
import java.util.*;

public class LogParser {

    private List<LogEntry> entries = new ArrayList<>();

    public LogParser(String filePath) throws IOException {
        parse(filePath);
    }

    private void parse(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                entries.add(new LogEntry(line));
            }
        }
    }

    public List<LogEntry> getEntries() {
        return entries;
    }
}

