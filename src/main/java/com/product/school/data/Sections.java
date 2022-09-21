package com.product.school.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(	name = "sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sections {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "active",nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name="grade_id", nullable=false)
    private Grades grade;

    @JsonIgnore
    @OneToMany(mappedBy="section")
    private Set<TimeTables> timeTables;

}
