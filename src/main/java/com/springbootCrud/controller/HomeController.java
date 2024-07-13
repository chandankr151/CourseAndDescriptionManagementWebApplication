package com.springbootCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootCrud.entities.Course;
import com.springbootCrud.services.CourseService;

@RestController
public class HomeController {

	@Autowired
	private CourseService courseService;

	// Add course or update Course.
	@PostMapping({ "/addCourse", "updateCourse" })
	public Course addCourse(@RequestBody Course course) {
		return this.courseService.addCourse(course);
	}

	// display course by id.
	@GetMapping("/courses/{courseId}")
	public Course getOneCourse(@PathVariable String courseId) {
		return this.courseService.getCourse(Long.parseLong(courseId));
	}

	// display all course...
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return this.courseService.getAllCourse();
	}

	// course delete...
	@DeleteMapping("/courses/{courseId}")
	public void deleteCourse(@PathVariable String courseId) {
		this.courseService.deleteCourse(Long.parseLong(courseId));
	}
}