
/**
 * 여기에 Part4 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
import edu.duke.*;

public class Part4 {
    
    public void testing() {
        URLResource file = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        
        System.out.println("ASDFASDF");
        for (String item : file.lines()) {
           String itemLower = item.toLowerCase();
           int pos = itemLower.indexOf("youtube.com");
           if (pos != -1) {
               int beg = item.lastIndexOf("\"", pos);
               int end = item.indexOf("\"", pos+1);
               System.out.println(item.substring(beg+1, end));
           }
        }
    }
}
