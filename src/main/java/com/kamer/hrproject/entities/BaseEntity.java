package com.kamer.hrproject.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;
    private Boolean isDeleted;
    private Timestamp createDate;

}
