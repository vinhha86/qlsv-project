package com.niit.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.niit.backend.dao.repository.ClassRepository;
import com.niit.backend.entities.Class;
import com.niit.backend.entities.Student;

@Service
@Transactional
public class ClassService {
	@Autowired
	private ClassRepository classRepository;
	
	/**
     * Lấy ra tất cả Class
     * @return List<Class>
     * */
    public List<Class> findAll() {
        List<Class> classes = classRepository.findAll();
        return classes;
    }

    /**
     * Lấy ra danh sách class có phân trang
     *
     * */
    public Page<Class> pageClass(String page, String size) {
        Page<Class> classPage = classRepository.pageClass(page, size);
        return classPage;
    }

    /**
     * Tìm kiếm và trả về danh sách class có phân trang
     *
     * */
    public Page<Class> searchClass(String keyword,String page, String size) {
        Page<Class> classPage = classRepository.search(keyword, page, size);
        return classPage;
    }

    /**
     * Cập nhật dữ liệu và lưu mới dữ liệu Class
     * @param class
     * @return class or null
     * */
    public Class save(Class classObj) {
        Class savedClass = classRepository.save(classObj);
        if (savedClass != null) {
            return classObj;
        }
        return null;
    }

    /**
     * Tìm class theo id của class
     * @param id
     * @return class or null
     * */
    public Class findClassById(Long id) {
        Class classObj = classRepository.findClassById(id);
        if (classObj != null) {
            return classObj;
        }
        return null;
    }

    /**
     * Xóa class
     * @param id
     * */
    public void deleteClassById(Long id) {
        classRepository.deleteById(id);
    }
    
    public void addStudentToClass(Class classObj, Student student) {
    	if(!classObj.getStudents().contains(student))
    		classObj.getStudents().add(student);
    	classRepository.saveAndFlush(classObj);
    }
    
    public void removeStudentFromClass(Class classObj, Student student) {
    	if(classObj.getStudents().contains(student))
    		classObj.getStudents().remove(student);
    	classRepository.saveAndFlush(classObj);
    }
}
