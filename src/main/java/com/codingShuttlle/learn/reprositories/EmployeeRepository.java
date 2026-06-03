package com.codingShuttlle.learn.reprositories;

import com.codingShuttlle.learn.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity ,Long> {
}
