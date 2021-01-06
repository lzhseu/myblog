package top.lzhseu.myblog.controller.api.blogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lzhseu.myblog.controller.BaseCheckController;
import top.lzhseu.myblog.entity.blogger.BloggerProfile;
import top.lzhseu.myblog.exception.api.common.EmptyResultException;
import top.lzhseu.myblog.exception.api.parameter.ParameterIllegalException;
import top.lzhseu.myblog.service.blogger.BloggerProfileService;
import top.lzhseu.myblog.util.constant.exception.ExceptionMessage;
import top.lzhseu.myblog.util.restful.ResultBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzh
 * @date 2020/6/1 10:34
 */
@Controller
@RequestMapping("manage/blogger/profile")
public class BloggerProfileController extends BaseCheckController {

    @Autowired
    private BloggerProfileService profileService;


    @PostMapping("/update")
    @ResponseBody
    public Object updateProfile(BloggerProfile newProfile,
                                HttpServletRequest request) {

        if (newProfile == null) {
            throw new ParameterIllegalException(ExceptionMessage.PARAMETER_ILLEGAL_EXCEPTION_MSG);
        }

        Integer profileId = newProfile.getId();
        if (profileId == null) {
            handleParameterCheck(newProfile.getNickname(), newProfile.getLabel(), newProfile.getIntro(), newProfile.getEmail());
            Integer bloggerId = handleBloggerLogin(request);
            newProfile.setBloggerId(bloggerId);
            profileService.insertProfile(newProfile);
            return new ResultBean("insert ok");
        }

        BloggerProfile profile = profileService.getBloggerProfileById(newProfile.getId());

        if (profile == null) {
            throw new EmptyResultException(ExceptionMessage.EMPTY_RESULT_EXCEPTION_MSG);
        }

        profileService.updateProfile(newProfile);
        return new ResultBean("update ok");
    }
}
