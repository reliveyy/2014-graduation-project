package com.oplogo.kira.model;

import com.oplogo.kira.util.ClassUtil;
import com.oplogo.kira.util.FileUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

/**
 * Created by yy on 6/6/14.
 */
@Component
public class AutManager {

    private static final Logger logger = LoggerFactory.getLogger(AutManager.class);
    private Map<String, Class> map = new HashMap<>();
    private List<Aut> auts = new LinkedList<>();

    @Transactional
    private void updateClassFileModifyDate(HibernateTemplate ht, Aut aut, SessionFactory sessionFactory) {
        File classFile = FileUtil.getClassFile(aut);
        Date last = new Date(classFile.lastModified());
        aut.setClassFileModifyDate(last);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(aut);
        session.getTransaction().commit();
        //ht.save(aut);
    }

    @Autowired
    public AutManager(HibernateTemplate ht, SessionFactory sessionFactory) {
        instance = this;
        List list = ht.find("from Aut");
        if (list != null) {
            auts = (List<Aut>) list;
            for (Aut a : auts) {
                try {
                    Class c = ClassUtil.load(a);
                    updateClassFileModifyDate(ht, a, sessionFactory);
                    //System.out.println("AutManager load:" + c);
                    map.put(a.getUrlName(), c);
                } catch (ClassNotFoundException e) {
                    logger.info("Cannot load class file of Aut: {}", a);
                }
            }
        }
    }

    public boolean add(Aut aut) {
        try {
            Class c = ClassUtil.load(aut);
            auts.add(aut);
            map.put(aut.getUrlName(), c);
        } catch (ClassNotFoundException e) {
            logger.info("Cannot load class file of Aut: {}", aut);
            return false;
        }
        return true;
    }

    public Aut findById(long id) {
        for (Aut a : auts) if (a.getId() == id) return a;
        return null;
    }

    public void remove(long id) {
        Aut aut = findById(id);
        auts.remove(aut);
        map.remove(aut.getUrlName());
    }

    public Class getClass(String urlName) {
        return map.get(urlName);
    }

    public Collection<Class> getAllClasses() {
        return map.values();
    }

    public Aut findByUrlName(String urlName) {
        for (Aut a : auts) if (a.getUrlName().equals(urlName)) return a;
        return null;
    }

    public Aut findByName(String name) {
        for (Aut a : auts) if (a.getName().equals(name)) return a;
        return null;
    }

    private static AutManager instance = null;

    public static AutManager getInstance() {
        return instance;
    }
}
