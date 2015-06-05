package com.oplogo.kira.model.extra;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by yy on 5/1/14.
 */
@Entity
public class VideoAd extends Advertisement {
    public enum Type{
        MP4,
        AVI,
        RMVB
    }
    @Enumerated(EnumType.STRING)
    public Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
