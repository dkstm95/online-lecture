
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        la.printAll();
    }
    
    public void testCountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        System.out.println("# OF UNIQUE IPs : " + la.countUniqueIPs());
    }
    
    public void testprintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        String date = "Sep 27";
        System.out.println("UNIQUE IP VISITS ON DAY : " + la.uniqueIPVisitsOnDay(date).size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        int low = 200;
        int high = 299;
        System.out.println("COUNT UNIQUE IPS IN RANGE : " + la.countUniqueIPsInRange(low, high));
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("MOST NUMBER VISITS BY IP : " + la.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("IP, MOST NUMBER VISITS: " + la.iPsMostVisits(counts));
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        System.out.println("IPS FOR DAYS: " + counts);
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        System.out.println("DAY WITH MOST IP VISITS: " + la.dayWithMostIPVisits(counts));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile();
        HashMap<String, ArrayList<String>> counts = la.iPsForDays();
        String date = "Sep 30";
        System.out.println("IP WITH MOST VISITS ON DAY: " + la.iPsWithMostVisitsOnDay(counts, date));
    }
}
