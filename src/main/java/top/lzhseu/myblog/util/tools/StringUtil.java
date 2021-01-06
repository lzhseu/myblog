package top.lzhseu.myblog.util.tools;

/**
 * @author lzh
 * @date 2020/5/26 22:09
 */
public class StringUtil {


    /**
     * 检查字符串是否为 null，允许 "null" 字符串
     *
     * @param str 字符串
     * @return 是返回true
     */
    public static boolean isEmpty_(String str) {
        return str == null || "".equals(str);
    }


    /**
     * 将普通字符串转为 16 字符串
     *
     * @param str 普通字符串
     * @return 16 进制字符串
     */
    public static String stringToUnicode(String str) {

        if (isEmpty_(str)) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int ich = (int) ch;
            String hex = Integer.toHexString(ich);
            // 形如 \\u3e 或 \\ua 或 \\ua23 的浏览器 html 无法解析
            if (hex.length() == 2) {
                hex = "00" + hex;
            } else if (hex.length() == 1) {
                hex = "000" + hex;
            } else if (hex.length() == 3) {
                hex = "0" + hex;
            }

            builder.append("\\u").append(hex);
        }

        return builder.toString();
    }

    /**
     * 将 16 字符串转普通字符串
     *
     * @param unicode 16 进制字符串
     * @return 普通字符串
     */
    public static String unicodeToString(String unicode) {
        if (isEmpty_(unicode)) {
            return "";
        }

        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
}
