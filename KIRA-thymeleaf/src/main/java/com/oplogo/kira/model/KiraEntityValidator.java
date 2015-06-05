package com.oplogo.kira.model;

import com.oplogo.kira.util.CompileException;
import com.oplogo.kira.util.CompileUtil;
import com.oplogo.kira.util.FileCreationException;
import com.oplogo.kira.util.FileUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.IOException;

/**
 * Created by yy on 5/31/14.
 */
public class KiraEntityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return KiraEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        if(!errors.hasErrors()) {
            try {
                KiraEntity entity = (KiraEntity) target;
                File java = FileUtil.newJavaFile(entity.getPackageName(), entity.getName(), entity.getCode());
                DiagnosticCollector<JavaFileObject> dc = CompileUtil.compile(new File[]{java}, new File[]{});
                if (!dc.getDiagnostics().isEmpty()) {
                    String html = "";
                    for (Diagnostic diagnostic : dc.getDiagnostics()) {
                        html += "Line:" + diagnostic.getLineNumber() + " " + diagnostic.getMessage(null) + "<br/>";
                    }
                    errors.rejectValue("code", "compile.problem", new Object[]{html}, "");
                }
            } catch (FileCreationException | CompileException e) {
                errors.rejectValue("code", "compile.IOException", new Object[]{e}, "");
            }
        }
    }
}
