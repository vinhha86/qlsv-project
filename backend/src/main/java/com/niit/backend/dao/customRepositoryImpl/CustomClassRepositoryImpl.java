package com.niit.backend.dao.customRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.niit.backend.dao.customRepository.CustomClassRepository;
import com.niit.backend.entities.Class;

public class CustomClassRepositoryImpl extends CustomRepositoryImpl<Class> implements CustomClassRepository{

	public CustomClassRepositoryImpl() {
		super(Class.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Class> pageClass(String page, String size) {
		int pageNum, sizeNum;
        // Set default or set with param
        if (page==null) { pageNum = 0; } else { pageNum = Integer.parseInt(page); };
        if (size==null) { sizeNum = 10; } else { sizeNum = Integer.parseInt(size); };

        Pageable pageable = PageRequest.of(pageNum, sizeNum);
        String query = "select s from Class s";
        Map<String, Object> params = new HashMap<>();

        return paginator(pageable, query, "s", params);
	}

	@Override
	public Page<Class> search(String keyword, String page, String size) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
