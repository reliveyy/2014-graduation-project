package com.oplogo.kira.controller;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by yy on 5/22/14.
 */
@Entity
public class Message {
    @Id
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
