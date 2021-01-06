package top.lzhseu.myblog.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author lzh
 * @date 2020/5/30 11:39
 */
@Controller
public class TestController {

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {

        if (!file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/static/upload/editormd/image");
            String filename = file.getOriginalFilename();
            File filePath = new File(path, filename);
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path + File.separator + filename));
            return "success";
        } else {
            return "error";
        }

    }
}
