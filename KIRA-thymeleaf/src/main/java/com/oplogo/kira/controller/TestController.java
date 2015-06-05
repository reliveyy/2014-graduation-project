package com.oplogo.kira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;

/**
 * Created by yy on 5/25/14.
 */
@Controller
public class TestController {

    @Autowired
    ServletContext application;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return application.getAttribute("hello").toString();
    }
}
