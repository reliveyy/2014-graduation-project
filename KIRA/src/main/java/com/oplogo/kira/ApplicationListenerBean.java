package com.oplogo.kira;

import com.oplogo.kira.model.AutManager;
import com.oplogo.kira.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by yy on 6/8/14.
 */
@Component
public class ApplicationListenerBean implements ApplicationListener {
    private static int count = 0;

    @Autowired
    ServletContext servletContext;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("===========================================" + count++);
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            AutManager autManager = (AutManager)applicationContext.getBean("autManager");
            AutHibernateManager autHibernateManager = (AutHibernateManager)applicationContext.getBean("autHibernateManager");
            System.out.println(autManager.getAllClasses());
            autManager.getAllClasses().forEach(autHibernateManager::add);
            autHibernateManager.refresh();

//            try {
//                //File file = applicationContext.getResource("classpath*:com/oplogo/kira/annotation/Display").getFile();
//                //File file = applicationContext.getResource("classpath:WEB-INF/classes").getFile();
//
//                //System.out.print(file.getAbsolutePath());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            System.out.println(servletContext.getRealPath("/WEB-INF/classes"));
            System.out.println("===========================================");
        }
    }
}
