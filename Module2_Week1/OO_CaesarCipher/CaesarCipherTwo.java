
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipherTwo {

    private int mainKey1;
    private int mainKey2;
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    
    public CaesarCipherTwo(int key1, int key2) {
        mainKey1 = key1;
        mainKey2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char newChar;
            int idx = 0;
            if (i%2 == 0) {
                if (Character.isUpperCase(currChar)) {
                    idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else {
                    idx = alphabet.indexOf(Character.toUpperCase(currChar));
                    if (idx != -1) {
                        newChar = Character.toLowerCase(shiftedAlphabet1.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
            else {
                if (Character.isUpperCase(currChar)) {
                    idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else {
                    idx = alphabet.indexOf(Character.toUpperCase(currChar));
                    if (idx != -1) {
                        newChar = Character.toLowerCase(shiftedAlphabet2.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return ccTwo.encrypt(input);
    }
}
