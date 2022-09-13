package com.product.school.repositories;

import com.product.school.data.Sections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Sections, Long> {
}
