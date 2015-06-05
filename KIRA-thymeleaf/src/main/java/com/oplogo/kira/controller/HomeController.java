package com.oplogo.kira.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yy on 5/25/14.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("")
    public String index(){
        return "home/index";
    }

}
