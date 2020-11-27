
/**
 * 여기에 Part1 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countries = record.get("Country");
            
            if (countries.contains(country)) {
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        boolean flag = false;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
                flag = true;
            }
        }
        if (flag == false) {
            System.out.println("NO COUNTRY");
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String values = record.get("Value (dollars)");
            
            if (amount.length() < values.length()) {
                System.out.println(record.get("Country") + " " + values);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));

        //listExportersTwoProducts(parser, "cotton", "flowers");

        //System.out.println(numberOfExporters(parser, "cocoa"));

        bigExporters(parser, "$999,999,999,999");
    }

}
