package com.product.school.repositories;

import com.product.school.data.AcademicYears;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicYearRepository extends JpaRepository<AcademicYears, Long> {
}
