
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarBreaker {
    
    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int [26];
        for (int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values) {
        int maxIndex = 0;
        for (int i=0; i<values.length; i++) {
            if (values[maxIndex] < values[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }
    
    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        int key = 14;
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        String encrypted = cc.encrypt(message, key);
        System.out.println("ENCRYPT MESSAGE: " + encrypted);
        System.out.println("DECRYPT MESSAGE: " + decrypt(encrypted));
    }
    
    
    public String halfOfString(String message, int start) {
        String halfMsg = "";
        for (int i=start; i < message.length(); i+=2) {
            halfMsg += message.charAt(i);
        }
        return halfMsg;
    }
    
    public int getKey(String s) {
        return maxIndex(countLetters(s));
    }
    
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int maxDex1 = getKey(halfOfString(encrypted, 0));
        int dkey1 = maxDex1 - 4;
        if (maxDex1 < 4) {
            dkey1 = 26 - (4 - maxDex1);
        }
        int maxDex2 = getKey(halfOfString(encrypted, 1));
        int dkey2 = maxDex2 - 4;
        if (maxDex2 < 4) {
            dkey2 = 26 - (4 - maxDex2);
        }
        System.out.println("TWO KEYS: " + dkey1 + " " + dkey2);
        return cc.encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
        //return cc.encryptTwoKeys(encrypted, 26-2, 26-20);
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println(decryptTwoKeys(encrypted));
    }
}
