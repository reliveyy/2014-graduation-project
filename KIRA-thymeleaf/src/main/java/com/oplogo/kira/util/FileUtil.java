package com.oplogo.kira.util;

import com.oplogo.kira.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yy on 5/23/14.
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);


    public static File checkOrCreateDirectory(File file) throws FileCreationException {
        if (file.exists() && file.isDirectory()) return file;
        if (!file.mkdirs()) throw new FileCreationException(file.getAbsolutePath());
        return file;
    }

    public static File checkOrCreateFile(File file) throws IOException, FileCreationException {
        if (file.exists() && file.isFile()) return file;
        if (!file.createNewFile()) throw new FileCreationException(file.getAbsolutePath());
        return file;
    }

    public static File newJavaFile(String packageName, String filename, String content) throws FileCreationException {

        try {
            String packagePath = packageName.replace('.', '/');
            filename = filename + ".java";

            File java = checkOrCreateFile(new File(checkOrCreateDirectory(new File(Global.getJavaFolder(), packagePath)), filename));
            BufferedWriter writer = new BufferedWriter(new FileWriter(java));
            writer.write(content);
            writer.close();

            logger.info("Create a new java file {} with content:\n{}\n", java.getAbsolutePath(), content);

            return java;
        } catch (IOException e) {
            throw new FileCreationException(e);
        }
    }
}
