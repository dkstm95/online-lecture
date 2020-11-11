
/**
 * 여기에 Part1 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        //startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = (currIndex - startIndex) % 3;
            if (diff == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex +1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex= findStopCodon(dna, startIndex, "TAG");
        int tgaIndex= findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
                minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)) {
                minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    
    public void printAllGenes(String dna){
       int startIndex = 0;
       while (true) {
           String currentGene = findGene(dna, startIndex);
           if(currentGene.isEmpty()) {
               break;
           }
           System.out.println(currentGene);
           startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
       }
        
    }
}
