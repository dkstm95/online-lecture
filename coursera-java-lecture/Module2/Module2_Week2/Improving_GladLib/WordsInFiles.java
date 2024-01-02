
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (!map.containsKey(word)) {
                ArrayList<String> fname = new ArrayList<String>();
                fname.add(f.getName());
                map.put(word, fname);
            } else {
                ArrayList<String> fname = map.get(word);
                if (!fname.contains(f.getName())){
                    fname.add(f.getName());
                    map.put(word, fname);
                }
            }
        }
    }
    
    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        int maxNum = 0;
        for (String word : map.keySet()) {
            int currNum = map.get(word).size();
            if (maxNum < currNum) {
                maxNum = currNum;
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word : map.keySet()) {
            if (map.get(word).size() == number) {
                words.add(word);
            }
        }
        return words;
    }
    
    public void printFilesIn(String word) {
        for (String key : map.keySet()) {
            if (key.equals(word)) {
                for (String fname : map.get(key)) {
                    System.out.println(fname);
                }
            }
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int maxnum = maxNumber();
        ArrayList<String> words = wordsInNumFiles(4);
        System.out.println("The greatest number of files a word appears in is " + maxnum +
                          " and there are " + words.size() + " such words: ");
        for (String word : words) {
            System.out.print(word + "\t");
        }
        System.out.println("");
        for (String word : words) {
            System.out.println("\"" + word + "\"" + " appears in the files: ");
            printFilesIn(word);
        }
    }
}
