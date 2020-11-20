
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CharactersInPlay {

    private ArrayList<String> charactersNames;
    private ArrayList<Integer> charactersFreqs;
    
    public CharactersInPlay() {
        charactersNames = new ArrayList<String>();
        charactersFreqs = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = charactersNames.indexOf(person);
        if (index == -1) {
            charactersNames.add(person);
            charactersFreqs.add(1);
        } else {
            int value = charactersFreqs.get(index);
            charactersFreqs.set(index, value+1);
        }
    }
    
    public void findAllCharacters() {
        charactersNames.clear();
        charactersFreqs.clear();
        
        FileResource resource = new FileResource();
        for (String s : resource.lines()) {
            int period_idx = s.indexOf('.');
            if (period_idx != -1) {
                update(s.substring(0, period_idx));
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Characters With Num Parts: " + num1 + " ~ " + num2);
        for (int i=0; i < charactersFreqs.size(); i++) {
            if (num1 <= charactersFreqs.get(i) && charactersFreqs.get(i) <= num2) {
                System.out.println(charactersNames.get(i) + "\t" + charactersFreqs.get(i));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        for (int i=0; i < charactersFreqs.size(); i++) {
            if (charactersFreqs.get(i) > 4) {
                System.out.println(charactersNames.get(i) + "\t" + charactersFreqs.get(i));
            }
        }
        int num1 = 10;
        int num2 = 15;
        charactersWithNumParts(num1, num2);
    }
}
