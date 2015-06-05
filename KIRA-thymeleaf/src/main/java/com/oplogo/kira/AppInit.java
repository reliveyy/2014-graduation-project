package com.oplogo.kira;

import com.oplogo.kira.config.AppConfig;
import com.oplogo.kira.config.DispatcherConfig;
import com.oplogo.kira.util.FileCreationException;
import com.oplogo.kira.util.FileUtil;
import org.springframework.core.io.Resource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 整个程序的入口点
 * Created by yy on 5/19/14.
 */
public class AppInit implements WebApplicationInitializer {

    private Map<String, String> env = System.getenv();

    public void ckeckWorkSpace(Properties props, ServletContext application) throws ServletException {
        String workspace = props.getProperty("app.workspace.path");
        Pattern r = Pattern.compile("(\\$\\w*)");
        Matcher m = r.matcher(workspace);
        while (m.find()) {
            String global = m.group(1);
            global = global.substring(1, global.length());
            try {
                workspace = m.replaceAll(env.get(global));
            } catch (NullPointerException e) {
                throw new ServletException(
                        String.format("Cannot get environment variable: '%s'.", global)
                );
            }
        }
        try {
            File file = FileUtil.checkOrCreateDirectory(new File(workspace));
            Global.setWorkspace(file);
        } catch (FileCreationException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        //application.setAttribute("workspace",file);
    }

    private void check(WebApplicationContext ctx, ServletContext application) throws ServletException {
        Resource hello = ctx.getResource("classpath:application.properties");
        try {
            InputStream is = hello.getInputStream();
            Properties props = new Properties();
            props.load(is);
            ckeckWorkSpace(props, application);
            application.setAttribute("logo", props.getProperty("app.logo"));
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void onStartup(ServletContext container) throws ServletException {

        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DispatcherConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        // Check the system
        check(rootContext, container);
    }
}
