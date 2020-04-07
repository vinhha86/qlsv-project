package com.niit.backend.dao.customRepository;

import org.springframework.data.domain.Page;

import com.niit.backend.entities.Class;

public interface CustomClassRepository extends CustomRepository<Class> {
	/**
     * Danh sách có phân trang
     * @param page
     * @param size
     * @return
     * */
    Page<Class> pageClass(String page, String size);

    /**
     * Danh sách tìm kiếm với từ khóa có phân trang
     * @param keyword từ khóa tìm kiếm
     * @param page trang
     * @param size kích thước trang
     * @return
     * */
    Page<Class> search(String keyword,String page,String size);
}
