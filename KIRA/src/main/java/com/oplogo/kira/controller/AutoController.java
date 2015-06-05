package com.oplogo.kira.controller;

import com.oplogo.kira.AutHibernateManager;
import com.oplogo.kira.annotation.AutAddField;
import com.oplogo.kira.annotation.AutDetailField;
import com.oplogo.kira.annotation.AutEditField;
import com.oplogo.kira.annotation.AutListField;
import com.oplogo.kira.model.Aut;
import com.oplogo.kira.model.AutManager;
import com.oplogo.kira.util.StringUtil;
import org.apache.commons.lang3.text.WordUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by yy on 6/7/14.
 */
@Controller
@RequestMapping("/auto")
public class AutoController {

    @Autowired
    AutManager autManager;

    @Autowired
    AutHibernateManager autHibernateManager;

    //////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                          //
    //                                                                                          //
    //                                          LIST                                            //
    //                                                                                          //
    //                                                                                          //
    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/{autUrlName}")
    public String list(@PathVariable String autUrlName, Model model) {
        Class c = autManager.getClass(autUrlName);
        Aut aut = autManager.findByUrlName(autUrlName);
        List<AutListField> fields = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            AutListField af = AutListField.create(field);
            if (af != null) fields.add(af);
        }

        Session session = autHibernateManager.getSession();
        session.beginTransaction();
        List list = session.createQuery("from " + aut.getName()).list();
        session.getTransaction().commit();

        model.addAttribute("aut", aut);
        model.addAttribute("list", list);
        model.addAttribute("fields", fields);

        return "/auto/list";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                          //
    //                                                                                          //
    //                                          DETAIL                                          //
    //                                                                                          //
    //                                                                                          //
    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/detail/{autUrlName}/{id}")
    public String detail(@PathVariable String autUrlName, @PathVariable long id, Model model) {
        Class c = autManager.getClass(autUrlName);
        Aut aut = autManager.findByUrlName(autUrlName);
        List<AutDetailField> fields = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            AutDetailField af = AutDetailField.create(field);
            if (af != null) fields.add(af);
        }

        Session session = autHibernateManager.getSession();
        session.beginTransaction();
        Object x = session.createQuery("from " + aut.getName() + " where id=" + id).list().get(0);
        session.getTransaction().commit();

        model.addAttribute("aut", aut);
        model.addAttribute("command", x);
        model.addAttribute("fields", fields);
        model.addAttribute("id",id);
        return "/auto/detail";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                          //
    //                                                                                          //
    //                                          ADD                                             //
    //                                                                                          //
    //                                                                                          //
    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/add/{autUrlName}")
    public String add(@PathVariable String autUrlName, Model model) {
        Class c = autManager.getClass(autUrlName);
        Aut aut = autManager.findByUrlName(autUrlName);
        List<AutAddField> fields = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            AutAddField af = AutAddField.create(field);
            if (af != null) fields.add(af);
        }
        try {
            model.addAttribute("command", c.newInstance());
            model.addAttribute("fields", fields);
            model.addAttribute("aut", aut);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "/auto/add";
    }

    @RequestMapping(value = "add/{autUrlName}", method = RequestMethod.POST)
    public String add(@PathVariable String autUrlName, HttpServletRequest request, Model model) {
        Class c = autManager.getClass(autUrlName);
        Aut aut = autManager.findByUrlName(autUrlName);
        List<AutAddField> fields = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            AutAddField af = AutAddField.create(field);
            if (af != null) fields.add(af);
        }
        try {
            Object x = c.newInstance();
            for (AutAddField af : fields) {
                String s = request.getParameter(af.getName());
                if (s == null) continue;
                Field field = c.getDeclaredField(af.getName());//use getField() can only get public field
                Method method = c.getMethod("set" + WordUtils.capitalize(af.getName()), field.getType());
                switch (field.getType().getSimpleName()) {
                    case "long":
                        method.invoke(x, Long.parseLong(s));
                        break;
                    case "String":
                        method.invoke(x, s);
                        break;
                    case "Date":
                        SimpleDateFormat formatter = new SimpleDateFormat(af.getDateFormat());
                        try {
                            Date date = formatter.parse(s);
                            method.invoke(x, date);
                        } catch (ParseException e) {

                        }
                        break;
                    case "Set":
                        String[] values = s.split(",");
                        String entityName = StringUtil.grep(".(\\w+)>", field.getGenericType().toString());
                        Method getSet = c.getMethod("get" + WordUtils.capitalize(af.getName()));
                        Set set = (Set) getSet.invoke(x);
                        for (int i = 0; i < values.length; i++) {
                            long id = Long.parseLong(values[i]);
                            Session session = autHibernateManager.getSession();
                            session.beginTransaction();
                            Object entity = session.createQuery("from " + entityName + " where id = " + id).list().get(0);
                            session.getTransaction().commit();
                            set.add(entity);
                        }
                        break;
                    default:
                        long id = Long.parseLong(s);
                        entityName = StringUtil.grep(".(\\w+)$", field.getGenericType().toString());
                        //entityName = field.getType().getSimpleName();
                        Session session = autHibernateManager.getSession();
                        session.beginTransaction();
                        Object entity = session.createQuery("from " + entityName + " where id = " + id).list().get(0);
                        session.getTransaction().commit();
                        method.invoke(x, entity);
                        break;
                }

            }
            Session session = autHibernateManager.getSession();
            session.beginTransaction();
            session.save(x);
            session.getTransaction().commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "redirect:/auto/" + aut.getUrlName();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                          //
    //                                                                                          //
    //                                          EDIT                                            //
    //                                                                                          //
    //                                                                                          //
    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/edit/{autUrlName}/{id}")
    public String edit(@PathVariable String autUrlName, @PathVariable long id, Model model) {
        Class c = autManager.getClass(autUrlName);
        Aut aut = autManager.findByUrlName(autUrlName);
        List<AutEditField> fields = new ArrayList<>();
        for (Field field : c.getDeclaredFields()) {
            AutEditField af = AutEditField.create(field);
            if (af != null) fields.add(af);
        }
        Session session = autHibernateManager.getSession();
        session.beginTransaction();
        Object x = session.createQuery("from " + aut.getName() + " where id=" + id).list().get(0);
        session.getTransaction().commit();

        model.addAttribute("command", x);
        model.addAttribute("fields", fields);
        model.addAttribute("aut", aut);

        return "/auto/edit";
    }

    @RequestMapping(value = "/edit/{autUrlName}/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable String autUrlName, @PathVariable long id, HttpServletRequest request) {
        Class c = autManager.getClass(autUrlName);
        Aut aut = autManager.findByUrlName(autUrlName);
        List<AutAddField> fields = new ArrayList<AutAddField>();
        for (Field field : c.getDeclaredFields()) {
            AutAddField af = AutAddField.create(field);
            if (af != null) fields.add(af);
        }
        try {
            Session session = autHibernateManager.getSession();
            session.beginTransaction();
            Object x = session.createQuery("from " + aut.getName() + " where id=" + id).list().get(0);
            session.getTransaction().commit();

            for (AutAddField af : fields) {
                String s = request.getParameter(af.getName());
                if (s == null) continue;
                Field field = c.getDeclaredField(af.getName());//use getField() can only get public field
                Method method = c.getMethod("set" + WordUtils.capitalize(af.getName()), field.getType());
                switch (field.getType().getSimpleName()) {
                    case "long":
                        method.invoke(x, Long.parseLong(s));
                        break;
                    case "String":
                        method.invoke(x, s);
                        break;
                    case "Date":
                        SimpleDateFormat formatter = new SimpleDateFormat(af.getDateFormat());
                        try {
                            Date date = formatter.parse(s);
                            method.invoke(x, date);
                        } catch (ParseException e) {

                        }
                        break;
                    case "Set":
                        String[] values = s.split(",");
                        String entityName = StringUtil.grep(".(\\w+)>", field.getGenericType().toString());
                        Method getSet = c.getMethod("get" + WordUtils.capitalize(af.getName()));
                        Set set = (Set) getSet.invoke(x);
                        set.clear();

                        for (int i = 0; i < values.length; i++) {
                            long _id = Long.parseLong(values[i]);
                            Session session2 = autHibernateManager.getSession();
                            session2.beginTransaction();
                            Object entity = session2.createQuery("from " + entityName + " where id = " + _id).list().get(0);
                            session2.getTransaction().commit();
                            set.add(entity);
                        }
                        break;
                    default:
                        long _id = Long.parseLong(s);
                        entityName = StringUtil.grep(".(\\w+)$", field.getGenericType().toString());
                        //entityName = field.getType().getSimpleName();
                        Session session2 = autHibernateManager.getSession();
                        session2.beginTransaction();
                        Object entity = session2.createQuery("from " + entityName + " where id = " + _id).list().get(0);
                        session2.getTransaction().commit();
                        method.invoke(x, entity);
                        break;
                }
            }
//            Method method = c.getMethod("setId", long.class);
//            method.invoke(x, id);
            session = autHibernateManager.getSession();
            session.beginTransaction();
            session.update(x);
            session.getTransaction().commit();
        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "redirect:/auto/" + aut.getUrlName();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                          //
    //                                                                                          //
    //                                          DELETE                                          //
    //                                                                                          //
    //                                                                                          //
    //////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/delete/{autUrlName}/{id}")
    public String delete(@PathVariable String autUrlName, @PathVariable long id) {
        Class c = autManager.getClass(autUrlName);
        Session session = autHibernateManager.getSession();
        session.beginTransaction();
        Object x = session.load(c, id);
        session.delete(x);
        session.getTransaction().commit();
        return "redirect:/auto/" + autUrlName;
    }
}
