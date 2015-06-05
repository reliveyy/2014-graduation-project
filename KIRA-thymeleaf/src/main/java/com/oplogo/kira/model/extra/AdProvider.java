package com.oplogo.kira.model.extra;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by yy on 5/20/14.
 */
@Entity
public class AdProvider {

    @Id
    private String name;

    private String contactName;

    private String contactPhone;

    @OneToMany(mappedBy = "adProvider")
    private Set<Advertisement> advertisements;
}
