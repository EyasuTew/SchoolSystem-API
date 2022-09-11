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
    private Long id;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private Boolean isCurrent;

    @Column(nullable = false)
    private Boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy="academicYear")
    private Set<TimeTables> timeTable;
}