
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordPlay {
    
    public boolean isVowel(char ch) {
        String vowel = "aeiouAEIOU";
        if (vowel.indexOf(ch) != -1) {
            return true;
        } else {
            return false;
        }
    }
    
    public void testisVowel() {
        System.out.println(isVowel('F'));
        System.out.println(isVowel('A'));
    }
    
    
    public String replaceVowels(String phrase, char ch) {
        String vowel = "aeiouAEIOU";
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                result.setCharAt(i, ch);
            }
        }
        return result.toString();
    }
    
    public void testreplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    
    public String emphasize(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = Character.toLowerCase(phrase.charAt(i));
            ch = Character.toLowerCase(ch);
            if (currChar == ch) {
                if ((i+1) % 2 == 1) {
                    result.setCharAt(i, '*');
                } else {
                    result.setCharAt(i, '+');
                }
            }
        }
        return result.toString();
    }
    
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
