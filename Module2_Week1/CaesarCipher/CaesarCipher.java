
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {

    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet_upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_lower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet_upper = alphabet_upper.substring(key) + alphabet_upper.substring(0, key);
        String shiftedAlphabet_lower = alphabet_lower.substring(key) + alphabet_lower.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char newChar;
            int idx = 0;
            if (Character.isUpperCase(currChar)) {
                idx = alphabet_upper.indexOf(currChar);
                if (idx != -1) {
                    newChar = shiftedAlphabet_upper.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            else {
                idx = alphabet_lower.indexOf(currChar);
                if (idx != -1) {
                    newChar = shiftedAlphabet_lower.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet_upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_lower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet_upper_key1 = alphabet_upper.substring(key1) + alphabet_upper.substring(0, key1);
        String shiftedAlphabet_lower_key1 = alphabet_lower.substring(key1) + alphabet_lower.substring(0, key1);
        String shiftedAlphabet_upper_key2 = alphabet_upper.substring(key2) + alphabet_upper.substring(0, key2);
        String shiftedAlphabet_lower_key2 = alphabet_lower.substring(key2) + alphabet_lower.substring(0, key2);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            char newChar;
            int idx = 0;
            if (i%2 == 0) {
                if (Character.isUpperCase(currChar)) {
                    idx = alphabet_upper.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet_upper_key1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else {
                    idx = alphabet_lower.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet_lower_key1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
            else {
                if (Character.isUpperCase(currChar)) {
                    idx = alphabet_upper.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet_upper_key2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else {
                    idx = alphabet_lower.indexOf(currChar);
                    if (idx != -1) {
                        newChar = shiftedAlphabet_lower_key2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys() {
        int key1 = 8;
        int key2 = 21;
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("key is " + key1 + " " + key2 + "\n" + encrypted);
    }
}
