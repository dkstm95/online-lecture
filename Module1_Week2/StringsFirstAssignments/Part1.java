
/**
 * 여기에 Part1 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class Part1 {
    
    public String findSimpleGene(String s) {
        int startIdx = s.indexOf("ATG");
        if (startIdx == -1) {
            return "";
        }
        int stopIdx = s.indexOf("TAA", startIdx+3);
        if (stopIdx == -1) {
            return "";
        }
        String sub_s = s.substring(startIdx, stopIdx+3);
        if (sub_s.length() % 3 == 0) {
            return sub_s;
        }
        else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String dna = "ATADOTAA";
        System.out.println(dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "ATGWERFD";
        System.out.println(dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "ASDFWER";
        System.out.println(dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "ATGAVDTAA";
        System.out.println(dna);
        System.out.println(findSimpleGene(dna));
        
        dna = "ATGASDFTAA";
        System.out.println(dna);
        System.out.println(findSimpleGene(dna));
    }

}
