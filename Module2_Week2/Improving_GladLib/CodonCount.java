
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {

    private HashMap<String, Integer> codonMap;
    
    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        for (int i=start; i+3 <= dna.length(); i+=3) {
            String codon = dna.substring(i, i+3);
            if (!codonMap.containsKey(codon)) {
                codonMap.put(codon, 1);
            } else {
                codonMap.put(codon, codonMap.get(codon) + 1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        int maxCount = 0;
        String mostCommonCodon = "";
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if (maxCount < count) {
                maxCount = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }
    
    public void printCodonCounts(int start, int end) {
        for (String codon : codonMap.keySet()) {
            int count = codonMap.get(codon);
            if (start <= count && count <= end) {
                System.out.println(codon + "\t" + count);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna_strand = fr.asString().trim().toUpperCase();
        for (int i=0; i < 3; i++) {
            System.out.println("\n");
            buildCodonMap(i, dna_strand);
            System.out.println("Reading frame starting with " + i + " results in " +
                                codonMap.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println(" and most common codon is " + mostCommonCodon +
                                " with count " + codonMap.get(mostCommonCodon));
            int num1 = 1;
            int num2 = 7;
            System.out.println("Counts of codons between " + num1 + " and " + num2 +
                                " inclusive are: ");
            printCodonCounts(num1, num2);
            System.out.println("\n");
        }
        
    }
}
