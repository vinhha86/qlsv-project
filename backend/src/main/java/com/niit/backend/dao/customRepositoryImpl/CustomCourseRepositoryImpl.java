package com.niit.backend.dao.customRepositoryImpl;

import com.niit.backend.dao.customRepository.CustomCourseRepository;
import com.niit.backend.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public class CustomCourseRepositoryImpl extends CustomRepositoryImpl<Course> implements CustomCourseRepository {

    public CustomCourseRepositoryImpl() {
        super(Course.class);
    }

    @Override
    public Page<Course> pageCourse(String page, String size) {
        int pageNum, sizeNum;
        // Set default or set with param
        if (page==null) { pageNum = 0; } else { pageNum = Integer.parseInt(page); };
        if (size==null) { sizeNum = 10; } else { sizeNum = Integer.parseInt(size); };

        Pageable pageable = PageRequest.of(pageNum, sizeNum);
        String query = "select c from Course c order by c.id desc";
        Map<String, Object> params = new HashMap<>();

        return paginator(pageable, query, "c", params);
    }

    @Override
    public Page<Course> findAllByType(int type, String page, String size) {
        int pageNum, sizeNum;
        // Set default or set with param
        if (page==null) { pageNum = 0; } else { pageNum = Integer.parseInt(page); };
        if (size==null) { sizeNum = 10; } else { sizeNum = Integer.parseInt(size); };
        Pageable pageable = PageRequest.of(pageNum, sizeNum);

        String query = "select c from Course c " +
                "where c.kieuKhoaHoc = :type";
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);

        return paginator(pageable, query, "c", params);
    }

    @Override
    public Page<Course> search(String keyword, String page, String size) {
        int pageNum, sizeNum;
        // Set default or set with param
        if (page==null) { pageNum = 0; } else { pageNum = Integer.parseInt(page); };
        if (size==null) { sizeNum = 10; } else { sizeNum = Integer.parseInt(size); };
        Pageable pageable = PageRequest.of(pageNum, sizeNum);

        String query = "select c from Course c " +
                "where c.maKhoaHoc like concat('%',:keyword,'%') " +
                "or c.tenKhoaHoc like concat('%',:keyword,'%') " +
                "or c.kieuKhoaHoc like concat('%',:keyword,'%')";
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);

        return paginator(pageable, query, "c", params);
    }
}
