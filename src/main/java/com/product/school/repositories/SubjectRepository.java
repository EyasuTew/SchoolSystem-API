package com.product.school.repositories;

import com.product.school.data.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subjects, Long> {
}
