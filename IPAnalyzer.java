package com.example.loganalyzer;

import java.util.*;

public class IPAnalyzer {
    private Map<String, Integer> ipCount = new HashMap<>();

    public void analyze(List<LogEntry> entries) {
        for (LogEntry e : entries) {
            if (e.getIP() != null) {
                ipCount.put(e.getIP(), ipCount.getOrDefault(e.getIP(), 0) + 1);
            }
        }
    }

    public Map<String, Integer> getIpCount() {
        return ipCount;
    }

    public List<Map.Entry<String, Integer>> topIPs(int n) {
        return ipCount.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(n)
                .toList();
    }
}
