
/**
 * 여기에 Part3 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class Part3 {
    
    public String lastPart(String stringa, String stringb) {
        int startIdx = stringb.indexOf(stringa);
        if (startIdx == -1) {
            return stringb;
        }
        return stringb.substring(startIdx, startIdx + stringa.length() + 1);
    }
    
    public boolean twoOccurrences(String stringa, String stringb) {
        int cnt = 0;
        int idx = -1;
        int new_idx = 0;
        
        while (idx < stringb.length()) {
            new_idx = stringb.indexOf(stringa, idx + 1);
            if (new_idx != -1) {
                cnt++;
                idx = new_idx;
            }
            else {
                idx++;
            }
        }
        if (cnt >= 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public void testing() {
        boolean result = true;
        String stringa = "by";
        String stringb = "A story by Abby Long";
        System.out.println(stringa + " " + stringb + " " + twoOccurrences(stringa, stringb));
        stringa = "a";
        stringb = "banana";
        System.out.println(stringa + " " + stringb + " " + twoOccurrences(stringa, stringb));
        stringa = "atg";
        stringb = "ctgtatgta";
        System.out.println(stringa + " " + stringb + " " + twoOccurrences(stringa, stringb));
        
        stringa = "an";
        stringb = "banana";
        System.out.println(stringa + " " + stringb + " " + lastPart(stringa, stringb));
        stringa = "zoo";
        stringb = "forest";
        System.out.println(stringa + " " + stringb + " " + lastPart(stringa, stringb));
    }

}
