package com.product.school.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(	name = "grades")
@Data
@NoArgsConstructor
public class Grades {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active;

    @JsonIgnore
    @OneToMany(mappedBy="grade")
    private Set<Sections> sections;

    @JsonIgnore
    @OneToMany(mappedBy="grade")
    private Set<Subjects> subjects;

//    @JsonIgnore
//    @OneToMany(mappedBy="grade")
//    private Set<TimeTables> timeTables;
}
