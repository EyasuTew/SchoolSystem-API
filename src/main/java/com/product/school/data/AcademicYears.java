package com.product.school.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Eyasu Tewodros
 *
 */
@Entity
@Table(name = "academic_year")
@Data
public class AcademicYears {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name="year",nullable = false)
    private String year;

    @Column(name = "is_current",nullable = false)
    private Boolean isCurrent;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy="academicYear")
    private Set<TimeTables> timeTable;
}