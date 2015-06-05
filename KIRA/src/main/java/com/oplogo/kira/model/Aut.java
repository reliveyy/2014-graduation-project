package com.oplogo.kira.model;

import com.oplogo.kira.annotation.Display;
import com.oplogo.kira.annotation.Edit;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yy on 5/22/14.
 */
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"packageName", "name"})
)
public class Aut implements Serializable {
    @Id
    @GeneratedValue
    @Display(enabled = false)
    private long id;

    @Edit(cssStyle = "font-family: Monaco;")
    private String packageName;

    @Display(
            name = "Entity Name"
    )
    @Edit(
            cssStyle = "font-family: Monaco;",
            hint = "Hello"
    )
    private String name;

    @Display(
            name = "Java Source Code",
            scope = Display.Scope.DETAIL,
            type = Display.Type.CODE
    )
    @Edit(
            type = Edit.Type.TEXT_AREA,
            rows = 16, cssStyle = "font-family: Monaco;",
            hint = "Input code here"
    )
    @Column(columnDefinition = "TEXT")
    private String code;

    @Display(
            type = Display.Type.LINK,
            href = "/auto/{value}"
    )
    private String urlName;

    @Edit(enabled = false)
    private Date lastModifyDate;

    //@Transient
    @Edit(enabled = false)
    private Date classFileModifyDate;

    @Edit(enabled = false)
    @Display(scope = Display.Scope.DETAIL)
    private String javaFilePath;

    @Edit(enabled = false)
    @Display(scope = Display.Scope.DETAIL)
    private String javaClassFilePath;

//////////////////////////////////////////////////////////////////////
//                        Getters & Setters
//////////////////////////////////////////////////////////////////////

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public Date getClassFileModifyDate() {
        return classFileModifyDate;
    }

    public void setClassFileModifyDate(Date classFileModifyDate) {
        this.classFileModifyDate = classFileModifyDate;
    }

    public String getJavaFilePath() {
        return javaFilePath;
    }

    public void setJavaFilePath(String javaFilePath) {
        this.javaFilePath = javaFilePath;
    }

    public String getJavaClassFilePath() {
        return javaClassFilePath;
    }

    public void setJavaClassFilePath(String javaClassFilePath) {
        this.javaClassFilePath = javaClassFilePath;
    }
}
