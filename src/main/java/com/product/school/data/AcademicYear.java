package com.product.school.data;

import javax.persistence.*;

/**
 * @author Eyasu Tewodros
 *
 */
@Entity
@Table(name = "academicyear")
public class AcademicYear {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private Boolean isCurrent;

    @Column(nullable = false)
    private Boolean isActive;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", isCurrent=" + isCurrent +
                ", isActive=" + isActive +
                '}';
    }
}