import edu.duke.*;
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
        if (minIndex == -1 || minIndex >= dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
    
    public void testOn() {
        String dnaA = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        StorageResource genes = getAllGenes(dnaA);
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    
    public double cgRatio(String dna) {
        int cg_num = 0;
        int total_num = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                cg_num++;
            }
            total_num++;
        }
        return ((double)cg_num)/total_num;
    }
    
    public int countCTG (String dna) {
        int startIdx = dna.indexOf("CTG");
        int cnt = 0;
        while (startIdx != -1 && startIdx+3 < dna.length()) {
            cnt += 1;
            int nextIdx = dna.indexOf("CTG", startIdx+3);
            if (nextIdx == -1){
                break;
            }
            startIdx = nextIdx;
        }
        return cnt;
    }
    
    public void processGenes (StorageResource sr) {
        System.out.println("Size of Genes : " + sr.size());
        int longer_than_9_num = 0;
        int longer_than_60_num = 0;
        int higher_than_35_num = 0;
        int longest = 0;
        for (String s: sr.data()) {
            if (s.length() > 9) {
                //System.out.println("Longer than 9 : " + s);
                longer_than_9_num++;
            }
            if (s.length() > 60) {
                //System.out.println("Longer than 60 : " + s);
                longer_than_60_num++;
            }
            if (cgRatio(s) > 0.35) {
                //System.out.println("Higher than 0.35 : " + s);
                higher_than_35_num++;
            }
            if (s.length() > longest) {
                longest = s.length();
            }
        }
        System.out.println("Longer than 60 : " + longer_than_60_num);
        System.out.println("Higher than 0.35 : " + higher_than_35_num);
        System.out.println("LONGEST : " + longest);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        //dna = "CTGACTGASDCGCTG";
        System.out.println("CTG NUM : " + countCTG(dna));
        processGenes(getAllGenes(dna));
    }
}
