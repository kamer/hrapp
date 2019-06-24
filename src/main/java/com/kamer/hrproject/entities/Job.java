package com.kamer.hrproject.entities;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "applications", callSuper = true)
@NoArgsConstructor
public class Job extends BaseEntity {
    private String jobTitle;
    private String jobDescription;
    private Integer quota;
    private Date lastApplicationDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private Set<Application> applications;

}
