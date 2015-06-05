package com.oplogo.kira;

import com.oplogo.kira.model.Aut;
import com.oplogo.kira.util.ClassUtil;
import com.oplogo.kira.util.FileCreationException;
import com.oplogo.kira.util.FileUtil;

import javax.servlet.ServletContext;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by yy on 6/1/14.
 */
public class Global {

    private static File workspace;
    private static File dataFolder;
    private static File javaFolder;

    public static void setWorkspace(File workspace) {

        Global.workspace = workspace;
        try {
            javaFolder = FileUtil.checkOrCreateDirectory(new File(workspace, "java"));
//            try {
//                ClassUtil.classLoader = URLClassLoader.newInstance(new URL[]{
//                        javaFolder.toURI().toURL()
//                },ClassLoader.getSystemClassLoader());
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
            //Thread.currentThread().setContextClassLoader( ClassUtil.classLoader);
            ClassUtil.classLoader.addRepository(javaFolder.toURI().toURL().toString());
            dataFolder = FileUtil.checkOrCreateDirectory(new File(workspace, "data"));
        } catch (FileCreationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static File getDataFolder() {
        return dataFolder;
    }

    public static File getJavaFolder() {
        return javaFolder;
    }
}
