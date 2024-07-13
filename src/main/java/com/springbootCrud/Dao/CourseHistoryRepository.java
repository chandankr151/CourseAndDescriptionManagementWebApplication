package com.springbootCrud.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springbootCrud.entities.CourseHistory;

public interface CourseHistoryRepository extends JpaRepository<CourseHistory, Long> {

}
