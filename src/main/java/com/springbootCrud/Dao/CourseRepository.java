package com.springbootCrud.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootCrud.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
}
