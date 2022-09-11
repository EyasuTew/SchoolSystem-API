package com.product.school.repositories;

import com.product.school.data.TimeTableSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableScheduleRepository extends JpaRepository<TimeTableSchedule, Long> {
}
