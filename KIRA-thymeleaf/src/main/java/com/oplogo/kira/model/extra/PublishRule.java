package com.oplogo.kira.model.extra;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by yy on 5/20/14.
 */
@Entity
public class PublishRule {
    @Id
    private int id;

    private String name;

    private String type;

    private String description;
}
