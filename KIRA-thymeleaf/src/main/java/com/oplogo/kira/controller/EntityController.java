package com.oplogo.kira.controller;

import com.oplogo.kira.App;
import com.oplogo.kira.annotaion.AddField;
import com.oplogo.kira.exception.AddEntityException;
import com.oplogo.kira.model.KiraEntity;
import com.oplogo.kira.model.KiraEntityValidator;
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
    App app;

    @Autowired
    EntityService entityService;

    @RequestMapping(value = {"/list*", ""})
    public String list(Model model) {
        List list = entityService.findAll();
        List<String> fields = new ArrayList<>();
        for (Field field : KiraEntity.class.getDeclaredFields()) {
            fields.add(field.getName());
        }
        model.addAttribute("list", list);
        model.addAttribute("fields", fields);
        return "/entity/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        model.addAttribute("command", entityService.findById(id));
        model.addAttribute("id", id);
        return "/entity/detail";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("kiraEntity", entityService.findById(id));
        return "/entity/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute KiraEntity kiraEntity, BindingResult result, @PathVariable long id, Model model) {
        new KiraEntityValidator().validate(kiraEntity, result);
        if (result.hasErrors()) {
            return "/entity/edit";
        }
        try {
            entityService.update(kiraEntity);
        } catch (AddEntityException e) {
            e.printStackTrace();
        }
        return "redirect:/entity/list";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        List<AddField> fields = new ArrayList<>();
        for (Field field : KiraEntity.class.getDeclaredFields()) {
            AddField af = AddField.create(field);
            if (af != null) fields.add(af);
        }
        model.addAttribute("fields", fields);
        model.addAttribute("command", new KiraEntity());
        return "/entity/add";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new KiraEntityValidator());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid KiraEntity entity, BindingResult result, Model model) {
        if (result.hasErrors()) {
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
