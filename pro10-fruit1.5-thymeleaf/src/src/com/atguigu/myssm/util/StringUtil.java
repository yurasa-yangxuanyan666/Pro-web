package src.com.atguigu.myssm.util;

public class StringUtil {
    //判断字符串是否weinull或者“”
    public static boolean isEmpty(String str){
        return str==null ||"".equals(str);
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
