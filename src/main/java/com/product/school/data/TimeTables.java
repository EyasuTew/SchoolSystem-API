package com.product.school.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(	name = "time_tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTables {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "starFrom", nullable = false)
    private Date startFrom;

    @Column(name = "endTo", nullable = false)
    private Date endTo;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name="section_id", nullable=false)
    private Sections section;

    @ManyToOne
    @JoinColumn(name="subject_id", nullable=false)
    private Subjects subject;

    @JsonIgnore
    @OneToMany(mappedBy="timeTable")
    private Set<TimeTableSchedule> timeTableSchedules;

}
