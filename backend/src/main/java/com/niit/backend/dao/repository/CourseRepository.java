package com.niit.backend.dao.repository;

import com.niit.backend.dao.customRepository.CustomCourseRepository;
import com.niit.backend.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CustomCourseRepository {
}
