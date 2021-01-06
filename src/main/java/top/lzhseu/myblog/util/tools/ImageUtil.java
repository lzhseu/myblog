package top.lzhseu.myblog.util.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author lzh
 * @date 2020/6/7 13:02
 */
public class ImageUtil {

    private static final String[] IMAGE_TYPE = {"png", "tif", "jpg", "jpeg", "bmp", "gif"};


    /**
     * 获取文件的图片类型
     *
     * @param name 文件名
     * @return 类型
     */
    public static String getImageMimeType(String name) {

        if (StringUtil.isEmpty_(name)) {
            return null;
        }

        int typeIndex = name.lastIndexOf(".");
        String type = name.substring(typeIndex + 1);

        for (String s : IMAGE_TYPE) {
            if (type.equalsIgnoreCase(s)) {
                return s;
            }
        }

        return null;
    }

    /**
     * 判断上传文件是否为图片类型
     *
     * @param file 上传文件
     * @return 是为true
     */
    public static boolean isImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        String mimetype = file.getContentType();
        String type = mimetype.split("/")[0];

        // 要么是图片类型，要么是二进制数据但文件名必须标明为图片类型
        return "image".equals(type);
    }

    /**
     * 获得图片文件类型
     *
     * @param file 上传文件
     * @return 类型
     */
    public static String getImageType(MultipartFile file) {
        if (isImageFile(file)) {
            String ct = file.getContentType();
            return ct.split("/")[1];
        } else {
            return null;
        }
    }

    /**
     * 从图片路径中获取文件名
     *
     * @param path 路径
     * @return 文件名
     */
    public static String getImageName(String path) {
        if (StringUtil.isEmpty_(path)) {
            return "";
        }

        int li = path.lastIndexOf(File.separator);
        int li1 = path.lastIndexOf(".");
        return path.substring(li + 1, li1);
    }
}
