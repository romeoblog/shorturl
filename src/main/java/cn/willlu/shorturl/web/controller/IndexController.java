package cn.willlu.shorturl.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author willlu.zheng
 * @date 2019/11/24
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "index", "index.htm", "index.html", "home"})
    public String index() {
        return "/index";
    }

}
