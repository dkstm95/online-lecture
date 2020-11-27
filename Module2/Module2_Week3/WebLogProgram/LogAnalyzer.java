
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
        records = new ArrayList<>();
    }
    
    public void readFile() {
        WebLogParser parser = new WebLogParser();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            records.add(parser.parseEntry(line));
        }
    }
    
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }
    
    public int countUniqueIPs() {
        /*
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)) {
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
        */

        HashMap<String, Integer> counts = countVisitsPerIP();
        return counts.size();
    }
    
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int currCode = le.getStatusCode();
            if (num < currCode) {
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> result = new ArrayList<>();
        for (LogEntry le : records) {
            String str = le.getAccessTime().toString();
            if (str.indexOf(someday, 4) != -1) {
                String ipAddr = le.getIpAddress();
                if (!result.contains(ipAddr)){
                    result.add(ipAddr);
                }
            }
        }
        return result;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (low <= statusCode && statusCode <= high) {
                String ipAddr = le.getIpAddress();
                if (!uniqueIPs.contains(ipAddr)) {
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }
    
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxNum = 0;
        for (Integer num: counts.values()) {
            if (maxNum < num) {
                maxNum = num;
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        int maxNum = mostNumberVisitsByIP(counts);
        ArrayList<String> result = new ArrayList<>();
        for (String ip: counts.keySet()) {
            if (counts.get(ip) == maxNum) {
                result.add(ip);
            }
        }
        return result;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        for (LogEntry le: records) {
            String ip = le.getIpAddress();
            String date = le.getAccessTime().toString();
            date = date.substring(4, 10);
            if (!result.containsKey(date)) {
                ArrayList<String> ipaddr = new ArrayList<>();
                ipaddr.add(ip);
                result.put(date, ipaddr);
            } else {
                ArrayList<String> ipaddr = result.get(date);
                ipaddr.add(ip);
                result.put(date, ipaddr);
            }
        }
        return result;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        int maxNum = 0;
        String maxDate = "";
        for (String date : map.keySet()) {
            if (maxNum < map.get(date).size()) {
                maxNum = map.get(date).size();
                maxDate = date;
            }
        }
        return maxDate;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String date) {
        HashMap<String, Integer> new_map = new HashMap<>();
        for (String ip: map.get(date)) {
            if(!new_map.containsKey(ip)) {
                new_map.put(ip, 1);
            } else {
                new_map.put(ip, new_map.get(ip) + 1);
            }
        }
        return iPsMostVisits(new_map);
    }
     
}
