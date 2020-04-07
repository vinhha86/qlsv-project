package com.niit.backend.dao.customRepository;

import com.niit.backend.entities.Course;
import org.springframework.data.domain.Page;

public interface CustomCourseRepository extends CustomRepository<Course> {
    /**
     * Danh sách có phân trang
     * @param page
     * @param size
     * @return
     * */
    Page<Course> pageCourse(String page, String size);
    /**
     * Danh sách tìm kiếm với từ khóa có phân trang
     * @param keyword từ khóa tìm kiếm
     * @param page trang
     * @param size kích thước trang
     * @return
     * */
    Page<Course> search(String keyword,String page,String size);

    /**
     * Danh sách khóa học theo kiểu khóa học có phân trang
     * @param type kiểu tìm kiếm
     * @param page trang
     * @param size kích thước trang
     * @return
     * */
    Page<Course> findAllByType(int type, String page, String size);
}
