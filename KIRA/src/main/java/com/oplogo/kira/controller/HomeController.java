package com.oplogo.kira.controller;

import com.oplogo.kira.AutHibernateManager;
import com.oplogo.kira.model.Aut;
import com.oplogo.kira.model.AutManager;
import com.oplogo.kira.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by yy on 5/25/14.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    AutManager autManager;

    @Autowired
    AutHibernateManager autHibernateManager;

    @RequestMapping("")
    public String index() {
        return "home/index";
    }

    @RequestMapping(value = "/init/auto-controller")
    @ResponseBody
    public Collection<Class> initAutoController() {
        autManager.getAllClasses().forEach(autHibernateManager::add);
        autHibernateManager.refresh();
        return autManager.getAllClasses();
    }
}
