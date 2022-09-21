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

    @Column(name = "uploaded_on", nullable = false)
    private Instant updatedOn;

    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

//    @OneToMany(mappedBy = "student")
//    @JoinColumn(name = "studentid", nullable = false)
//    private long studentid;
//
//    @OneToMany(mappedBy = "person")
//    @JoinColumn(name = "personid", nullable = false)
//    private long personid;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "person", referencedColumnName = "id")
//    private Persons person;

//    @OneToOne(mappedBy = "parent", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Persons person;


    @OneToOne(mappedBy = "parent")
    private Persons person;

}
