
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts) {
        for(String word : resource.words()){
            int word_len = word.length();
            if (!Character.isLetter(word.charAt(0))) {
                word_len -= 1;
            }
            if (!Character.isLetter(word.charAt(word.length()-1))) {
                word_len -= 1;
            }
            if (0 < word_len && word_len < counts.length) {
                counts[word_len] += 1;
            }
        }
    }
    
    public int indexOfMax(int[] values) {
        int maxIndex = 0;
        for (int i=0; i<values.length; i++) {
            if (values[maxIndex] < values[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for (int i=0; i<counts.length; i++) {
            System.out.println(counts[i]);
        }
        System.out.println("MAX INDEX : " + indexOfMax(counts));
    }
}
