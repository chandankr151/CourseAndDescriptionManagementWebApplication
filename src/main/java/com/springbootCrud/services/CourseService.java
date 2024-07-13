package com.springbootCrud.services;

import java.util.List;

import com.springbootCrud.entities.Course;

public interface CourseService {

	public List<Course> getAllCourse();

	public Course getCourse(long courseId);

	public Course addCourse(Course course);

	public void deleteCourse(long courseId);
}
