package com.product.school.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "frist_name", nullable = false)
    private String fristName;
//    @Column(name = "midname", nullable = false)
//    private String midName;
//    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "phone", nullable = false, unique = true, length = 45)
    private String phone;
    @Column(name = "subcity", length = 45)
    private String subcity;
    @Column(name = "kebele", length = 45)
    private String kebele;
    @Column(name = "gender", length = 45)
    private String gender;
    @Column(name = "created_on", nullable = false)
    private Instant createdOn;
    @Column(name = "email", nullable = true, unique = true, length = 45)
    private String email;
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updatedOn", nullable = false)
//    private Instant updatedOn;

//    @OneToOne(mappedBy = "person")
//    private Parents parent;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "parent_id", nullable = true)
//    @JsonIgnore
//    private Parents parent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private Parents parent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private Students student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = true)
    @JsonIgnore
    private Teachers teacher;

}
