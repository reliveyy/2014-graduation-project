package com.oplogo.kira.model;

import com.oplogo.kira.annotaion.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yy on 5/22/14.
 */
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"packageName", "name"})
)
public class KiraEntity implements Serializable {
    @Id
    @GeneratedValue
    @Display(enabled = false)
    private long id;

    @Edit(css = "font-family: Monaco;")
    private String packageName;

    @Display(name = "Entity Name")
    @Edit(css = "font-family: Monaco;", hint = "Hello")
    private String name;

    @Display(name = "Java Source Code")
    @Edit(type = Edit.Type.TEXT_AREA, rows = 16, css = "font-family: Monaco;", hint = "Input code here")
    @Column(columnDefinition="TEXT")
    private String code;

    private String urlName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}
