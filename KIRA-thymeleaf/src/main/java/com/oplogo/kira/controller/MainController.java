package com.oplogo.kira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yy on 6/4/14.
 */
@Controller
public class MainController {
    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/")
    public String welcome(Model model){
        model.addAttribute("message", "Hello, world!");
        return "welcome";
    }
}
