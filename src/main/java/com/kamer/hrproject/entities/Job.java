package com.kamer.hrproject.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "applications", callSuper = true)
@NoArgsConstructor
public class Job extends BaseEntity {
    private String jobTitle;
    @Lob
    private String jobDescription;
    private Integer quota;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastApplicationDate;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private Set<Application> applications;

    public Job(String jobTitle, String jobDescription, Integer quota, Date lastApplicationDate) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.quota = quota;
        this.lastApplicationDate = lastApplicationDate;
    }
}
