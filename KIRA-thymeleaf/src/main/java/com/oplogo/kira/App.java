package com.oplogo.kira;

import com.oplogo.kira.exception.AddEntityException;
import com.oplogo.kira.exception.ApplicationConfigurationException;
import com.oplogo.kira.model.KiraEntity;
import com.oplogo.kira.util.ClassUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yy on 5/22/14.
 */
@Component
public class App {

    private Configuration cfg = new Configuration().configure();
    private SessionFactory sf;
    private Map<String, Class> entityClassUrlMapping;
    private List<KiraEntity> entities;

    public void configHibernate() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());
        sf = cfg.buildSessionFactory(builder.build());
    }

    public Class getEntityClass(String urlName) {
        return entityClassUrlMapping.get(urlName);
    }

    public void addEntity(KiraEntity entity) throws AddEntityException {
        try {
            Class c = ClassUtil.load(entity);
            entityClassUrlMapping.put(entity.getUrlName(), c);
            cfg.addAnnotatedClass(c);
            configHibernate();
        } catch (ClassNotFoundException e) {
            throw new AddEntityException(e);
        }
    }

    public void addEntities(List<KiraEntity> entities) throws AddEntityException {
        try {
            for (KiraEntity entity : entities) {
                Class c = ClassUtil.load(entity);
                entityClassUrlMapping.put(entity.getUrlName(), c);
                cfg.addAnnotatedClass(c);
            }
            configHibernate();
        } catch (ClassNotFoundException e) {
            throw new AddEntityException(e);
        }
    }

    public void deleteEntity(long id) {
        for (KiraEntity entity : entities)
            if (entity.getId() == id) {
                entities.remove(entity);
                break;
            }
        entityClassUrlMapping.clear();
        cfg = new Configuration().configure();
        try {
            addEntities(entities);
        } catch (AddEntityException e) {
            e.printStackTrace();
        }
        configHibernate();
    }

    @Transactional
    private void configEntities(HibernateTemplate ht) throws ApplicationConfigurationException {
        entities = new ArrayList<>();
        entityClassUrlMapping = new HashMap<>();
        try {
            List list = ht.find("from KiraEntity");
            if (list == null) return;
            for (Object obj : list) {
                KiraEntity entity = (KiraEntity) obj;
                entities.add(entity);
                Class c = ClassUtil.load(entity);
                cfg.addAnnotatedClass(c);
            }
            configHibernate();
        } catch (ClassNotFoundException e) {
            throw new ApplicationConfigurationException(e);
        }
    }

    @Autowired
    public App(HibernateTemplate ht) throws ApplicationConfigurationException {
        try {
            configEntities(ht);
        } catch (Exception e) {
            throw new ApplicationConfigurationException(e);
        }
    }

    public Session getHibernateSession() {
        return sf.getCurrentSession();
    }

}
