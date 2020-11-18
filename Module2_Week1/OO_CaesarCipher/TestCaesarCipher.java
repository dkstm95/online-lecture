
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {

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
    
    private void breakCaesarCipher(String input) {
        int maxDex = maxIndex(countLetters(input));
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(26-dkey);
        System.out.println("BREAK_CAESAR_CIPHER: " + cc.encrypt(input));
    }
    
    
    public void simpleTests() {
        //FileResource fr = new FileResource();
        //String input_string = fr.asString();
        String input_string = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println("BEFORE: " + input_string);
        
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(input_string);
        System.out.println("ENCRYPTED: " + encrypted);
        System.out.println("DECRYPTED: " + cc.decrypt(encrypted));
        
        breakCaesarCipher(encrypted);
    }
}
