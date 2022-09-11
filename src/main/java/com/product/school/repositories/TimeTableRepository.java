package com.product.school.repositories;

import com.product.school.data.TimeTables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTables, Long> {
}
