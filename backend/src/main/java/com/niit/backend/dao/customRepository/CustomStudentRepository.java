package com.niit.backend.dao.customRepository;

import com.niit.backend.entities.Student;
import org.springframework.data.domain.Page;

public interface CustomStudentRepository extends CustomRepository<Student> {
    /**
     * Danh sách có phân trang
     * @param page
     * @param size
     * @return
     * */
    Page<Student> pageStudent(String page, String size);

    /**
     * Danh sách tìm kiếm với từ khóa có phân trang
     * @param keyword từ khóa tìm kiếm
     * @param page trang
     * @param size kích thước trang
     * @return
     * */
    Page<Student> search(String keyword,String page,String size);
}
