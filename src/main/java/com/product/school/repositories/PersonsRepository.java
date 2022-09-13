package com.product.school.repositories;

import com.product.school.data.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonsRepository extends JpaRepository<Persons ,Long> {
}
