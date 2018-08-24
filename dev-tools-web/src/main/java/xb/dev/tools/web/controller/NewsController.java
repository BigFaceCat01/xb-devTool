package xb.dev.tools.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: Created by huangxb on 2018-08-16 15:46:01
 * @Description:
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @GetMapping("")
    public ModelAndView index(){
        return new ModelAndView("layui");
    }
}
