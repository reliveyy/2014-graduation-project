package com.oplogo.kira.util;

import com.oplogo.kira.Global;
import com.oplogo.kira.model.Aut;
import org.apache.catalina.loader.WebappClassLoader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yy on 5/24/14.
 */
public class ClassUtil {

    public static WebappClassLoader classLoader = (WebappClassLoader)Aut.class.getClassLoader();
    //public static URLClassLoader classLoader;

    //public static Map<Class, URLClassLoader> map = new HashMap<>();

    public static Class load(String name) throws ClassNotFoundException {
        return classLoader.loadClass(name);
//        try {
//            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{
//                    Global.getJavaFolder().toURI().toURL()
//            }, ClassLoader.getSystemClassLoader());
//            Class c = classLoader.loadClass(name);
//            if (c != null) map.put(c, classLoader);
//            return c;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

//    public static void unload(Class c) {
//        URLClassLoader classLoader = map.get(c);
//        try {
//            classLoader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static Class load(Aut entity) throws ClassNotFoundException {
        if (entity.getPackageName().isEmpty()) {
            return load(entity.getName());
        }
        return load(entity.getPackageName() + "." + entity.getName());
    }

//    public static void unload(Aut entity){
//        try {
//            unload(load(entity));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
