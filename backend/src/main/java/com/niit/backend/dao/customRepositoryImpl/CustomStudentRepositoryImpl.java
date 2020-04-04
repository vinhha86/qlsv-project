package com.niit.backend.dao.customRepositoryImpl;

import com.niit.backend.dao.customRepository.CustomStudentRepository;
import com.niit.backend.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public class CustomStudentRepositoryImpl extends CustomRepositoryImpl<Student> implements CustomStudentRepository {

    public CustomStudentRepositoryImpl() {
        super(Student.class);
    }

    @Override
    public Page<Student> pageStudent(String page, String size) {
        int pageNum, sizeNum;
        // Set default or set with param
        if (page==null) { pageNum = 0; } else { pageNum = Integer.parseInt(page); };
        if (size==null) { sizeNum = 10; } else { sizeNum = Integer.parseInt(size); };

        Pageable pageable = PageRequest.of(pageNum, sizeNum);
        String query = "select s from Student s";
        Map<String, Object> params = new HashMap<>();

        return paginator(pageable, query, "s", params);
    }

    @Override
    public Page<Student> search(String keyword, String page, String size) {
        int pageNum, sizeNum;
        // Set default or set with param
        if (page==null) { pageNum = 0; } else { pageNum = Integer.parseInt(page); };
        if (size==null) { sizeNum = 10; } else { sizeNum = Integer.parseInt(size); };
        Pageable pageable = PageRequest.of(pageNum, sizeNum);

        String query = "select s from Student s " +
                "where s.ho like concat('%',:keyword,'%') " +
                "or s.ten like concat('%',:keyword,'%') " +
                "or s.masv like concat('%',:keyword,'%') " +
                "or s.socmt like concat('%',:keyword,'%') " +
                "or s.email like concat('%',:keyword,'%') " +
                "or s.sdt_canhan like concat('%',:keyword,'%') " +
                "or s.address.province.name like concat('%',:keyword,'%') ";
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);

        return paginator(pageable, query, "s", params);
    }
}
