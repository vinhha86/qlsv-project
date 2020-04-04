package com.niit.backend.dao.customRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CustomRepository<T> {
    /**
     * pagination với dữ liệu trong câu truy vấn
     * @param pageable đối tượng phân trang của spring
     * @param query câu truy vấn
     * @param alias alias của persistence class
     * @param params danh sách tham số truyền vào
     * @return Page danh sách thực thể kiểu dữ liệu T
     * */
    Page<T> paginator(Pageable pageable, String query, String alias, Map<String, Object> params);
}
