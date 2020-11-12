
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    
    public void printNames() {
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100){
                System.out.println("Name " + rec.get(0) +
                                   " Gender " + rec.get(1) +
                                   " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += 1;
            if (rec.get(1).equals("M")) {
                totalBoys += 1;
            } else {
                totalGirls += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }
    
    
    public int getRank(int year, String name, String gender) {
        String filepath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filepath);
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank++;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    
    public String getName(int year, int rank, String gender) {
        String filepath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filepath);
        int curr_rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                curr_rank++;
                if (curr_rank == rank) {
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int old_rank = getRank(year, name, gender);
        String new_name = getName(newYear, old_rank, gender);
        String gender_expr = "";
        if (gender.equals("M")){
            gender_expr = "he";
        } else {
            gender_expr = "she";
        }
        System.out.println(name + " born in " + year + " would be " + new_name + " if " +
                           gender_expr + " was born in " + newYear);
    }
    
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = Integer.MAX_VALUE;
        int highestYear = -1;
        for (File f : dr.selectedFiles()) {
            int currYear = Integer.parseInt(f.getName().replaceAll("\\D+",""));
            int currRank = getRank(currYear, name, gender);
            if (currRank != -1 && highestRank > currRank) {
                highestRank = currRank;
                highestYear = currYear;
            }
        }
        return highestYear;
    }
    
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        double sum = 0.0;
        for (File f : dr.selectedFiles()) {
            int currYear = Integer.parseInt(f.getName().replaceAll("\\D+",""));
            int currRank = getRank(currYear, name, gender);
            if (currRank == -1) {
                return -1.0;
            }
            count++;
            sum += currRank;
        }
        return sum / count;
    }
    
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int targetRank = getRank(year, name, gender);
        int currRank = 1;
        int totalNum = 0;
        String filepath = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filepath);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (targetRank <= currRank) {
                break;
            }
            if (rec.get(1).equals(gender)) {
                totalNum += Integer.parseInt(rec.get(2));
                currRank++;
            }
        }
        return totalNum;
    }
}