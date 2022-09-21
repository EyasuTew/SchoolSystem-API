package com.product.school.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(	name = "time_table_schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTableSchedule {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "start_from", nullable = false)
    private Date startFrom;

    @Column(name = "end_to", nullable = false)
    private Date endTo;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name="time_table_id", nullable=false)
    private TimeTables timeTable;

}
