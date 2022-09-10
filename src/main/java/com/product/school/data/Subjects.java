package com.product.school.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(	name = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subjects {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name="grade_id", nullable=false)
    private Grades grade;

    @ManyToOne
    @JoinColumn(name="general_subject_id", nullable=false)
    private GeneralSubjects generalSubject;

}
