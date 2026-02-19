package loganalyzer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {

    private LocalDateTime timestamp;
    private String level;
    private String message;
    private String ip;

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogEntry(String line) {
        // Example:
        // 2026-02-11 10:32:45 ERROR Failed login from 192.168.1.10

        String[] parts = line.split(" ", 4);

        if (parts.length < 4) {
            this.timestamp = null;
            this.level = "UNKNOWN";
            this.message = line;
            this.ip = null;
        } else {
            this.timestamp = LocalDateTime.parse(parts[0] + " " + parts[1], formatter);
            this.level = parts[2];
            this.message = parts[3];
            this.ip = extractIP(this.message);
        }
    }

    private String extractIP(String msg) {
        Pattern pattern = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
        Matcher matcher = pattern.matcher(msg);
        return matcher.find() ? matcher.group() : null;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getLevel() { return level; }
    public String getMessage() { return message; }
    public String getIP() { return ip; }

    public boolean isFailedLogin() {
        return message.toLowerCase().contains("failed login");
    }
}

