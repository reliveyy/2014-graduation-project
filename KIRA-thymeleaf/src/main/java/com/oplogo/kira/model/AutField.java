package com.oplogo.kira.model;

import com.oplogo.kira.annotaion.Display;
import com.oplogo.kira.annotaion.Edit;

/**
 * Created by yy on 6/5/14.
 */
public class AutField {
    private String name;
    private Display display;
    private Edit edit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Edit getEdit() {
        return edit;
    }

    public void setEdit(Edit edit) {
        this.edit = edit;
    }
}
