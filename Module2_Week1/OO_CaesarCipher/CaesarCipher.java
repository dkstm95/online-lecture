
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    
    private int mainKey;
    private String alphabet;
    private String shiftedAlphabet;
    
    public CaesarCipher(int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char newChar;
            int idx = 0;
            if (Character.isUpperCase(currChar)) {
                idx = alphabet.indexOf(currChar);
                if (idx != -1) {
                    newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else {
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
                if (idx != -1) {
                    newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet_key1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet_key2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char newChar;
            int idx = 0;
            if (i%2 == 0) {
                if (Character.isUpperCase(currChar)) {
                    idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet_key1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else {
                    idx = alphabet.indexOf(Character.toUpperCase(currChar));
                    if (idx != -1) {
                        newChar = Character.toLowerCase(shiftedAlphabet_key1.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
            else {
                if (Character.isUpperCase(currChar)) {
                    idx = alphabet.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet_key2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else {
                    idx = alphabet.indexOf(Character.toUpperCase(currChar));
                    if (idx != -1) {
                        newChar = Character.toLowerCase(shiftedAlphabet_key2.charAt(idx));
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }
}
