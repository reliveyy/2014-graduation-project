package com.oplogo.kira;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by yy on 6/6/14.
 */
@Component
public class AutHibernateManager {


    private Configuration cfg;
    private SessionFactory sf;

    public AutHibernateManager() {
        instance = this;
        init();
    }

    public void init() {
        cfg = new Configuration().configure();
    }

    private void buildSessionFactory() {
        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder()
                        .applySettings(cfg.getProperties());
        sf = cfg.buildSessionFactory(builder.build());
    }

    public void add(Class c) {
        cfg.addAnnotatedClass(c);
    }

    public void refresh() {
        buildSessionFactory();
    }

    public Session getSession() {
        return sf.getCurrentSession();
    }

    private static AutHibernateManager instance = null;

    public static AutHibernateManager getInstance() {
        return instance;
    }

}
