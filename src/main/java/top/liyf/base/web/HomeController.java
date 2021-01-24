package top.liyf.base.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liyf
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String hello() {
        return "index.html";
    }


}
