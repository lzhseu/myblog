package top.lzhseu.myblog.controller.api.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.lzhseu.myblog.controller.BaseCheckController;
import top.lzhseu.myblog.entity.blog.BlogImage;
import top.lzhseu.myblog.manager.properties.BlogProperties;
import top.lzhseu.myblog.service.blog.BlogImageService;
import top.lzhseu.myblog.util.tools.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author lzh
 * @date 2020/6/7 13:22
 */
@RestController
public class ImageController extends BaseCheckController {

    @Autowired
    private BlogImageService imageService;

    @Autowired
    private BlogProperties blogProperties;

    /**
     * editorMd 上传图片
     * @param attach 图片文件
     * @param desc 图片描述。editormd 没有上传图片描述，要真正上传需要改 editormd 的源码，暂不修改。
     * @return editormd 规范的 json 数据
     */
    @ResponseBody
    @RequestMapping(value = "/manage/image/upload/editormd")
    public JSONObject uploadBlogImage(@RequestParam(value = "editormd-image-file", required = false) MultipartFile attach,
                                      @RequestParam(value = "desc", required = false) String desc) {

        JSONObject jsonObject = new JSONObject();


        if (!ImageUtil.isImageFile(attach)) {
            jsonObject.put("success", 0);
            jsonObject.put("message", "图片为空");
            return jsonObject;
        }

        String filename = attach.getOriginalFilename();

        // 判断图片是否已上传
        BlogImage image = imageService.getImageByTitle(filename);
        if (image != null) {
            jsonObject.put("success", 1);
            jsonObject.put("message", "文件已存在；文件URL为：http:/" + image.getUrl());
            jsonObject.put("url", image.getUrl());
            return jsonObject;
        }

        try {
            // String rootPath = request.getServletContext().getRealPath(blogProperties.getBlogImageLocation());
            String rootPath = blogProperties.getBlogImageLocation();

            // 文件路径不存在则需要创建文件路径
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            // 最终文件名
            File realFile = new File(rootPath + File.separator + filename);
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);
            //attach.transferTo(realFile);

            // 插入数据库
            image = new BlogImage();
            image.setTitle(filename);
            image.setDesc(desc);
            imageService.insertImage(image);

        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("success", 0);
            jsonObject.put("message", e);
            return jsonObject;
        }

        // 下面response返回的json格式是editor.md所限制的，规范输出就OK
        jsonObject.put("success", 1);
        jsonObject.put("message", "上传成功");
        jsonObject.put("url", blogProperties.getBlogImageRootUrl() + "/" + image.getId());

        return jsonObject;
    }


    /**
     * 获取博文中的图片
     * @param imageId 图片 id
     * @param response 响应
     */
    @RequestMapping("/upload/editormd/image/{imageId}")
    public void getBlogImage(@PathVariable Integer imageId,
                             HttpServletResponse response) {

        BlogImage image = imageService.getImageById(imageId);

        if (image != null) {
            String rootPath = blogProperties.getBlogImageLocation();
            outPutPicture(image, rootPath, response);
        }
    }


    /**
     * 输出图片
     * @param image 图片实例
     * @param rootPath 图片位于本机的根路径
     * @param response 响应
     */
    private void outPutPicture(BlogImage image,
                               String rootPath,
                               HttpServletResponse response) {

        try (ServletOutputStream os = response.getOutputStream()) {

            String path = rootPath + File.separator + image.getTitle();

            File imageFile = new File(path);
            if (!imageFile.exists()) {
                handlerOperateFail();
            }

            String type = ImageUtil.getImageMimeType(imageFile.getName());

            if (type == null) {
                handlerOperateFail();
            }

            response.setContentType("image/" + type);

            BufferedImage read = ImageIO.read(imageFile);
            ImageIO.write(read, type, os);
        } catch (IOException e) {
            e.printStackTrace();
            handlerOperateFail(e);
        }
    }

}
