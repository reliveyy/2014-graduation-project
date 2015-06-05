package com.oplogo.kira.util;

import com.oplogo.kira.model.KiraEntity;

import java.net.URLClassLoader;

/**
 * Created by yy on 5/24/14.
 */
public class ClassUtil {

    public static URLClassLoader classLoader;

    public static Class load(String name) throws ClassNotFoundException {
        return classLoader.loadClass(name);
    }
    public static Class load(KiraEntity entity) throws ClassNotFoundException {
        return classLoader.loadClass(entity.getPackageName() + "." + entity.getName());
    }
}
