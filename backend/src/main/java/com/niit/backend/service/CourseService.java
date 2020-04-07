package com.niit.backend.service;

import com.niit.backend.dao.repository.CourseRepository;
import com.niit.backend.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Lấy ra tất cả course
     * @return List
     * */
    public List<Course> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

    /**
     * Lấy ra danh sách course có phân trang
     *
     * */
    public Page<Course> pageCourse(String page, String size) {
        Page<Course> coursePage = courseRepository.pageCourse(page, size);
        return coursePage;
    }

    /**
     * Lấy ra danh sách course có phân trang
     *
     * */
    public Page<Course> pageCourseByType(int type, String page, String size) {
        Page<Course> coursePage = courseRepository.findAllByType(type, page, size);
        return coursePage;
    }

    /**
     * Tìm kiếm và trả về danh sách course có phân trang
     *
     * */
    public Page<Course> searchCourse(String keyword,String page, String size) {
        Page<Course> coursePage = courseRepository.search(keyword, page, size);
        return coursePage;
    }

    /**
     * Cập nhật dữ liệu và lưu mới dữ liệu course
     * @param course
     * @return course or null
     * */
    public Course save(Course course) {
        Course savedCourse = courseRepository.save(course);
        if (savedCourse != null) {
            return savedCourse;
        }
        return null;
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
}
