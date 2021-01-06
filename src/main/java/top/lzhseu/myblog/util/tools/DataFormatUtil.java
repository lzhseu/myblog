package top.lzhseu.myblog.util.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzh
 * @date 2020/5/26 22:09
 */
public class DataFormatUtil {

    public static String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }

    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf =new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
