
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {

    private int[] countLetters(String message) {
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
    
    private int maxIndex(int[] values) {
        int maxIndex = 0;
        for (int i=0; i<values.length; i++) {
            if (values[maxIndex] < values[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private String halfOfString(String message, int start) {
        String halfMsg = "";
        for (int i=start; i < message.length(); i+=2) {
            halfMsg += message.charAt(i);
        }
        return halfMsg;
    }
    
    private int getKey(String s) {
        return maxIndex(countLetters(s));
    }
    
    private void breakCaesarCipher(String input) {
        int maxDex1 = getKey(halfOfString(input, 0));
        int dkey1 = maxDex1 - 4;
        if (maxDex1 < 4) {
            dkey1 = 26 - (4 - maxDex1);
        }
        int maxDex2 = getKey(halfOfString(input, 1));
        int dkey2 = maxDex2 - 4;
        if (maxDex2 < 4) {
            dkey2 = 26 - (4 - maxDex2);
        }
        System.out.println("TWO KEYS: " + dkey1 + " " + dkey2);
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(26-dkey1, 26-dkey2);
        System.out.println("BREAK_CAESAR_CIPHER: " + ccTwo.encrypt(input));
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String input_string = fr.asString();
        //String input_string = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        //System.out.println("BEFORE: " + input_string);
        
        //CaesarCipherTwo ccTwo = new CaesarCipherTwo(14, 24);
        //String encrypted = ccTwo.encrypt(input_string);
        //System.out.println("ENCRYPTED: " + encrypted);
        //System.out.println("DECRYPTED: " + ccTwo.decrypt(encrypted));
        
        breakCaesarCipher(input_string);
    }
}
