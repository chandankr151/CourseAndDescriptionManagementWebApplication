package com.springbootCrud.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootCrud.Dao.CourseHistoryRepository;
import com.springbootCrud.Dao.CourseRepository;
import com.springbootCrud.entities.Course;
import com.springbootCrud.entities.CourseHistory;

@Service
public class CourseServiceImpl implements CourseService {

	private static Logger LOG = LogManager.getLogger(CourseServiceImpl.class);

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private CourseHistoryRepository courseHistoryRepo;

	@Override
	public Course addCourse(Course course) {
		
		if (course.getTitle().isEmpty() || course.getTitle().isBlank()) {
			LOG.error("Title can't be Empty/Blank.");
		} else if (course.getDescription().isEmpty() || course.getDescription().isBlank()) {
			LOG.error("Description can't be Empty/Blank.");
		} else {
			CourseHistory CourseHis = new CourseHistory();
			CourseHis.setParentId(course.getId());
			CourseHis.setTitle(course.getTitle());
			CourseHis.setDescription(course.getDescription());

			courseHistoryRepo.save(CourseHis);
			courseRepository.save(course);
			
			LOG.info("Course added/updated Successfully.");
		}
		return course;
	}

	@Override
	public Course getCourse(long courseId) {
		Course course1 = null;
		Optional<Course> course = courseRepository.findById(courseId);
		if (course.isEmpty()) {
			LOG.error("Course with given id is not available");
		} else {
			course1 = course.get();
		}
		return course1;
	}

	@Override
	public List<Course> getAllCourse() {
		LOG.trace("Extracting record from database");
		return courseRepository.findAll();
	}

	@Override
	public void deleteCourse(long courseId) {
		if (courseRepository.existsById(courseId)) {
			Course course = new Course();
			course = courseRepository.findById(courseId).get();

			CourseHistory CourseHis = new CourseHistory();
			CourseHis.setParentId(course.getId());
			CourseHis.setTitle(course.getTitle());
			CourseHis.setDescription(course.getDescription());

			courseHistoryRepo.save(CourseHis);

			LOG.warn("You are about to delete a record.");
			courseRepository.deleteById(courseId);
			LOG.info("Given course deleted successfully.");

		} else {
			LOG.error("Can't delete, Course with given id is not available.");
		}
	}
}