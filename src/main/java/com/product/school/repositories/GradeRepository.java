package com.product.school.repositories;

import com.product.school.data.Grades;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grades, Long> {
}
