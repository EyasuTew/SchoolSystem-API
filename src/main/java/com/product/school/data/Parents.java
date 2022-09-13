package com.product.school.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.time.Instant;


import static javax.persistence.GenerationType.IDENTITY;
@Table(name = "parents")
@Entity
public class Parents {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
        private long id;
    public long getId() {
        return id; }
    public void setId(long id) {
        this.id = id; }
    @Column(name = "midName", unique = true, nullable = false)
    private String fristName;

    @Column(name = "lastName", unique = true, nullable = false)
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "midName", unique = true, nullable = false)
    private String midName;


    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }
    @Column(name = "phoneNumber", unique = true, nullable = false)
    private int phoneNumber;
    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Column(name = "uploaded_on", nullable = false)
    private Instant updated_on;

    @Column(name = "created_on", nullable = false)
    private Instant created_on;

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "studentid", nullable = false)
    private long studentid;

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public Instant getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Instant updated_on) {
        this.updated_on = updated_on;
    }

    public Instant getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Instant created_on) {
        this.created_on = created_on;
    }

    public long getStudentid() {
        return studentid;
    }

    public void setStudentid(long studentid) {
        this.studentid = studentid;
    }
}
