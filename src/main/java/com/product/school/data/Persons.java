package com.product.school.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "persons")
@Entity
public class Persons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "fristName", nullable = false)
    private String fristName;
    @Column(name = "midname",nullable = false)
    private String midName;
    @Column(name ="lastName" ,nullable = false)
    private String lastName;
    @Column(name = "phoneNumber",nullable = false, unique = true, length=45)
    private String phoneNumber;
    @Column(name="subcity", length=45)
    private String subcity;
    @Column(name="kebele", length=45)
    private String kebele;
    @Column(name= "gender", length=45)
    private String gender;
    @Column(name = "created_on", nullable = false)
    private Date createdOn;
    @Column(name="emailAddress", nullable = true, unique = true, length=45)
    private String emailAddress;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updatedOn", nullable=false)
    private Date updatedOn;



    @OneToMany(fetch=FetchType.LAZY, mappedBy="persons")
    private Set<Parents> parents= new HashSet<Parents>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSubcity() {
        return subcity;
    }

    public void setSubcity(String subcity) {
        this.subcity = subcity;
    }

    public String getKebele() {
        return kebele;
    }

    public void setKebele(String kebele) {
        this.kebele = kebele;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<Parents> getParents() {
        return parents;
    }

    public void setParents(Set<Parents> parents) {
        this.parents = parents;
    }
}
