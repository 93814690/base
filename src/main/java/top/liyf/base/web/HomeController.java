package top.liyf.base.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyf
 * Created in 2020-05-02
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String hello() {
        return "index.html";
    }
}
