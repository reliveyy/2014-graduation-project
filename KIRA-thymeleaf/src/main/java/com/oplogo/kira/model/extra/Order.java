package com.oplogo.kira.model.extra;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yy on 5/1/14.
 */
@Entity
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid")
    private String id;

    @ManyToOne
    //@JoinColumn(name = "ad_id")
    private Advertisement advertisement;

    @ManyToOne
    private Hotel hotel;

    @OneToMany
    private Set<PublishRule> rules;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
