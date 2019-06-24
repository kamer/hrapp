package com.kamer.hrproject.entities;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Job extends BaseEntity {
    private String jobTitle;
    private String jobDescription;
    private Integer quota;
    private Date lastApplicationDate;

}
