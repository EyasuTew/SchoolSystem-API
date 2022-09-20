package com.product.school.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.Instant;


import static javax.persistence.GenerationType.IDENTITY;
@Table(name = "parents")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parents {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
        private long id;


//    @Column(name = "fristName", unique = true, nullable = false)
//    private String fristName;
//
//    @Column(name = "lastName", unique = true, nullable = false)
//    private String lastName;
//
//
//    @Column(name = "midName", unique = true, nullable = false)
//    private String midName;
//
//
//
//    @Column(name = "phoneNumber", unique = true, nullable = false)
//    private int phoneNumber;

    @Column(name = "uploaded_on", nullable = false)
    private Instant updated_on;

    @Column(name = "created_on", nullable = false)
    private Instant created_on;


    @OneToMany(mappedBy = "student")
    @JoinColumn(name = "studentid", nullable = false)
    private long studentid;

    @OneToMany(mappedBy = "person")
    @JoinColumn(name = "personid", nullable = false)
    private long personid;

}
