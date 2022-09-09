package com.product.school.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
