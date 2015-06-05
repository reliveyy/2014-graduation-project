package com.oplogo.kira.model.extra;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by yy on 5/1/14.
 */
@Entity
public class ImageAd extends Advertisement {
    public enum Type{
        JPG,
        PNG,
        BMP,
        GIF
    }
    @Enumerated(EnumType.STRING)
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
