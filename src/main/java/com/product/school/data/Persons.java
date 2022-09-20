package com.product.school.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "persons")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "fristName", nullable = false)
    private String fristName;
    @Column(name = "midname", nullable = false)
    private String midName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "phoneNumber", nullable = false, unique = true, length = 45)
    private String phoneNumber;
    @Column(name = "subcity", length = 45)
    private String subcity;
    @Column(name = "kebele", length = 45)
    private String kebele;
    @Column(name = "gender", length = 45)
    private String gender;
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;
    @Column(name = "emailAddress", nullable = true, unique = true, length = 45)
    private String emailAddress;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedOn", nullable = false)
    private Instant updatedOn;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persons")
    private Set<Parents> parents = new HashSet<Parents>(0);


}
