import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * <pre>
 * 程序的中文名称。
 * </pre>
 *
 * @author likangming  likangming@kungeek.com
 * @version 1.00.00
 * @since 2018/11/12 10:25
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class MomoryLeak {
    public  static List<Double> list = new ArrayList<>();
     public void populateList() {
         for (int i = 0; i < 10000000; i++) {
             list.add(Math.random());
         }
         System.out.println("Debug Point 2");

     }

    public static void main(String[] args) {
        System.out.println("debug point 1");
        Properties properties = System.getProperties();
        Set set = properties.keySet();
        for (Object key : set) {
            System.out.println(key + " [" + properties.getProperty(key.toString()) + "]");
        }
        System.out.println("args = [" + System.getProperties() + "]");
//        new MomoryLeak().populateList();
        System.out.println("debug point 3");
    }

}
