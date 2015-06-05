package com.oplogo.kira.controller;

import com.oplogo.kira.annotation.AutAddField;
import com.oplogo.kira.annotation.AutDetailField;
import com.oplogo.kira.annotation.AutEditField;
import com.oplogo.kira.annotation.AutListField;
import com.oplogo.kira.exception.AddEntityException;
import com.oplogo.kira.model.Aut;
import com.oplogo.kira.model.AutValidator;
import com.oplogo.kira.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yy on 5/19/14.
 */
@Controller
@RequestMapping("/entity")
public class EntityController {

    @Autowired
    EntityService entityService;

    @RequestMapping(value = {"/list*", ""})
    public String list(Model model) {
        List list = entityService.findAll();
        List<AutListField> fields = new ArrayList<>();
        for (Field field : Aut.class.getDeclaredFields()) {
            AutListField af = AutListField.create(field);
            if (af != null) fields.add(af);
        }
        model.addAttribute("list", list);
        model.addAttribute("fields", fields);
        return "/entity/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        List<AutDetailField> fields = new ArrayList<>();
        for (Field field : Aut.class.getDeclaredFields()) {
            AutDetailField df = AutDetailField.create(field);
            if (df != null) fields.add(df);
        }
        model.addAttribute("fields", fields);
        model.addAttribute("command", entityService.findById(id));
        return "/entity/detail";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        List<AutEditField> fields = new ArrayList<>();
        for (Field field : Aut.class.getDeclaredFields()) {
            AutEditField af = AutEditField.create(field);
            if (af != null) fields.add(af);
        }
        model.addAttribute("fields", fields);
        model.addAttribute("aut", entityService.findById(id));
        return "/entity/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@Valid Aut aut, BindingResult result, @PathVariable long id, Model model) {
        //new AutValidator().validate(aut, result);
        if (result.hasErrors()) {
            List<AutEditField> fields = new ArrayList<>();
            for (Field field : Aut.class.getDeclaredFields()) {
                AutEditField af = AutEditField.create(field);
                if (af != null) fields.add(af);
            }
            model.addAttribute("fields", fields);
            return "/entity/edit";
        }
        aut.setId(id);
        entityService.update(aut);
        return "redirect:/entity/list";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        List<AutAddField> fields = new ArrayList<>();
        for (Field field : Aut.class.getDeclaredFields()) {
            AutAddField af = AutAddField.create(field);
            if (af != null) fields.add(af);
        }
        model.addAttribute("fields", fields);
        model.addAttribute("aut", new Aut());
        return "/entity/add";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new AutValidator());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid Aut entity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<AutAddField> fields = new ArrayList<>();
            for (Field field : Aut.class.getDeclaredFields()) {
                AutAddField af = AutAddField.create(field);
                if (af != null) fields.add(af);
            }
            model.addAttribute("fields", fields);
            return "/entity/add";
        }
        try {
            entityService.save(entity);
        } catch (AddEntityException e) {
            e.printStackTrace();
        }
        return "redirect:/entity/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        entityService.delete(id);
        return "redirect:/entity/list";
    }

}
