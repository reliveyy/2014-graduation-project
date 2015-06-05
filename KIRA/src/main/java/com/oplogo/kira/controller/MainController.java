package com.oplogo.kira.controller;

import org.springframework.stereotype.Controller;
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
}
