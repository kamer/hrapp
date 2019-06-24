package com.kamer.hrproject.entities;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Application extends BaseEntity {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String thoughts;
    private byte[] resume;

}
