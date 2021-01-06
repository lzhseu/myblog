package top.lzhseu.myblog.controller.blogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.lzhseu.myblog.manager.SideBarDataManager;
import java.util.Map;

/**
 * 博主控制台页面
 * @author lzh
 * @date 2020/6/1 8:51
 */
@Controller
@RequestMapping("manage/blogger")
public class BloggerMainPageController {

    @Autowired
    private SideBarDataManager sideBarDataManager;

    @RequestMapping("/main")
    public ModelAndView mainPage() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("blogger/blogger_main");

        // 获取左边栏数据
        long start = System.currentTimeMillis();
        Map<String, Object> sideBarData = sideBarDataManager.getSideBarData();
//        long end = System.currentTimeMillis();
//        System.out.println("========================================");
//        System.out.println((end - start) / 1000);

        mv.addObject("bloggerProfile", sideBarData.get("bloggerProfile"));
        mv.addObject("blogCategory", sideBarData.get("blogCategory"));

        return mv;
    }
}
