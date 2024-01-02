
/**
 * 여기에 Part2 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class Part2 {

    public String findSimpleGene(String s, int startCodon, int stopCodon) {
        if (startCodon == -1) {
            return "";
        }
        if (stopCodon == -1) {
            return "";
        }
        String sub_s = s.substring(startCodon, stopCodon+3);
        if (sub_s.length() % 3 == 0) {
            return sub_s;
        }
        else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String dna = "ATGDOATAA";
        System.out.println(dna);
        System.out.println(findSimpleGene(dna, 0, 6));
    }
}
