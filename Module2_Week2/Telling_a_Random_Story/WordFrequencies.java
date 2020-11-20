
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource resource = new FileResource();
        
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int maxIdx = 0;
        for (int i=0; i < myFreqs.size(); i++) {
            if (myFreqs.get(maxIdx) < myFreqs.get(i)) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void tester() {
        findUnique();
        for (int k=0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        }
        System.out.println("Number of unique words: " + myWords.size());
        int maxIdx = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " +
                            myWords.get(maxIdx) + "\t" + myFreqs.get(maxIdx));
    }
}
