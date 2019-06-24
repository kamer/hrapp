package com.kamer.hrproject.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Application extends BaseEntity {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String thoughts;
    private byte[] resume;
    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private Job job;

}
