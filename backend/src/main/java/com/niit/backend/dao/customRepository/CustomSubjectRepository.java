package com.niit.backend.dao.customRepository;

import com.niit.backend.entities.Subject;
import org.springframework.data.domain.Page;

public interface CustomSubjectRepository extends CustomRepository<Subject> {
    /**
     * Danh sách có phân trang của môn học dựa theo khóa học
     * @param id khóa học id
     * @param page
     * @param size
     * @return
     * */
    Page<Subject> pageSubject(Long id,String page, String size);
}
