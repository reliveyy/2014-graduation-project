package com.oplogo.kira.util;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yy on 6/1/14.
 */
public class CompileUtil {
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
            options.add(".:/Users/yy/hibernate-annotations-3.5.6-Final.jar:/Users/yy/persistence-api-1.0.2.jar");
            boolean ok = compiler.getTask(null, fileManager, dc, options, null, compilationUnits).call();
            fileManager.close();
        } catch (IOException e) {
            throw new CompileException(e);
        }
        return dc;
    }
}
