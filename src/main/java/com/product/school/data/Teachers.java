package com.product.school.data;

import com.product.school.data.Persons;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "teachers")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teachers {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "uploaded_on", nullable = false)
    private Instant updatedOn;

    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @OneToOne(mappedBy = "teacher")
    private Persons person;

}
