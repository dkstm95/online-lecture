import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String result = "";
        for (int i = whichSlice; i < message.length(); i+=totalSlices) {
            result += message.charAt(i);
        }
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cCracker = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slicedString = sliceString(encrypted, i, klength);
            int dkey = cCracker.getKey(slicedString);
            key[i] = dkey;
            //System.out.println("KEY : " + dkey);
        }
        return key;
    }
    
    public void breakVigenere() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        int klength = 4;
        char mostCommon = 'e';
        int[] keys = tryKeyLength(input, klength, mostCommon);
        VigenereCipher vCipher = new VigenereCipher(keys);
        String decrypt = vCipher.decrypt(input);
        System.out.println(decrypt);
    }
    
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> map = new HashSet<>();
        for (String line : fr.lines()) {
            map.add(line.toLowerCase());
        }
        return map;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String[] split_msg = message.split("\\W+");
        for (String word : split_msg) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String bestString = "";
        int maxCount = 0;
        int maxKlength = 0;
        //char mostCommon = 'e';
        char mostCommon = mostCommonCharIn(dictionary);  
        for (int klength = 1; klength < 101; klength++) {
            int[] keys = tryKeyLength(encrypted, klength, mostCommon);
            VigenereCipher vCipher = new VigenereCipher(keys);
            String decrypt = vCipher.decrypt(encrypted);
            int currCount = countWords(decrypt, dictionary);
            if (maxCount < currCount) {
                maxCount = currCount;
                bestString = decrypt;
                maxKlength = klength;
            }
        }
        System.out.println("KEY LENGTH : " + maxKlength);
        System.out.println("WORD COUNT : " + maxCount);
        return bestString;
    }
    
    public void secondBreakVigenere() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        FileResource fr2 = new FileResource();
        HashSet<String> dict = readDictionary(fr2);
        String decrypt = breakForLanguage(input, dict);
        System.out.println(decrypt.substring(0,100));
    }
    
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> map = new HashMap<>();
        char mostCommonChar = ' ';
        int mostCommonCharNum = 0;
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }
        }
        for (Character c : map.keySet()) {
            if (mostCommonCharNum < map.get(c)) {
                mostCommonCharNum = map.get(c);
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        for (String language : languages.keySet()) {
            System.out.println("PROCESSING : " + language);
            String decrypt = breakForLanguage(encrypted, languages.get(language));
            System.out.println(decrypt.substring(0, 100));
            System.out.println();
        }
    }
    
    public void thirdBreakVigenere() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr2 = new FileResource(f);
            HashSet<String> dict = readDictionary(fr2);
            languages.put(f.getName(), dict);
        }
        breakForAllLangs(input, languages);
    }
}
