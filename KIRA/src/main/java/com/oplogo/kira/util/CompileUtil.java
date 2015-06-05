package com.oplogo.kira.util;

import com.oplogo.kira.Global;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yy on 6/1/14.
 */
@Component
public class CompileUtil implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ServletContext servletContext = (ServletContext) applicationContext.getBean("servletContext");
        WEB_INF_CLASSES = servletContext.getRealPath("/WEB-INF/classes");
        WEB_INF_LIB = servletContext.getRealPath("/WEB-INF/lib");
    }

    private static String WEB_INF_CLASSES;
    private static String WEB_INF_LIB;
    private static final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    public static DiagnosticCollector<JavaFileObject> compile(File[] src, File[] lib) throws CompileException {
        DiagnosticCollector<JavaFileObject> dc = new DiagnosticCollector<>();
        try {
            StandardJavaFileManager fileManager =
                    compiler.getStandardFileManager(dc, null, null);//local,charset
            Iterable<? extends JavaFileObject> compilationUnits =
                    fileManager.getJavaFileObjectsFromFiles(Arrays.asList(src));
            List<String> options = new ArrayList<>();
            options.add("-classpath");
            String cp = "." + ":"
                    //+ "/Users/yy/hibernate-annotations-3.5.6-Final.jar" + ":"
                    //+ "/Users/yy/persistence-api-1.0.2.jar" + ":"
                    + Global.getJavaFolder().getAbsolutePath() + ":"
                    + WEB_INF_CLASSES + ":"
                    + WEB_INF_LIB + "/hibernate-jpa-2.1-api-1.0.0.Final.jar" + ":"
                    + WEB_INF_LIB + "/hibernate-core-4.3.5.Final.jar";
            System.out.println(cp);
            options.add(cp);
            boolean ok = compiler.getTask(null, fileManager, dc, options, null, compilationUnits).call();
            fileManager.close();
        } catch (IOException e) {
            throw new CompileException(e);
        }
        return dc;
    }
}
