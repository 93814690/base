package top.liyf.base.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liyf.base.annotation.AnonymousInterface;
import top.liyf.base.annotation.NeedPermission;
import top.liyf.base.bo.UserBO;
import top.liyf.base.result.ResultBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liyf
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @AnonymousInterface
    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/needLogin")
    public ResultBean<String> needLogin() {
        return new ResultBean<>("success");
    }

    @NeedPermission("aaa")
    @GetMapping("/needPermission")
    public String needPermission() {
        return "you have permission";
    }

    @AnonymousInterface
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        UserBO userBo = new UserBO();
        request.getSession().setAttribute("user", userBo);
        return "login";
    }

}
