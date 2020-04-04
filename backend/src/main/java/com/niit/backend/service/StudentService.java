package com.niit.backend.service;

import com.niit.backend.dao.repository.AddressRepository;
import com.niit.backend.dao.repository.FileRepository;
import com.niit.backend.dao.repository.StudentRepository;
import com.niit.backend.entities.FileResponse;
import com.niit.backend.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Lấy ra tất cả Student
     * @return List<Student>
     * */
    public List<Student> findAll() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    /**
     * Lấy ra danh sách student có phân trang
     *
     * */
    public Page<Student> pageStudent(String page, String size) {
        Page<Student> studentPage = studentRepository.pageStudent(page, size);
        return studentPage;
    }

    /**
     * Tìm kiếm và trả về danh sách student có phân trang
     *
     * */
    public Page<Student> searchStudent(String keyword,String page, String size) {
        Page<Student> studentPage = studentRepository.search(keyword, page, size);
        return studentPage;
    }

    /**
     * Cập nhật dữ liệu và lưu mới dữ liệu Student
     * @param student
     * @return student or null
     * */
    public Student save(Student student) {
        Student savedStudent = studentRepository.save(student);
        if (savedStudent != null) {
            return student;
        }
        return null;
    }

    /**
     * Tìm student theo id của student
     * @param id
     * @return student or null
     * */
    public Student findStudentById(Long id) {
        Student student = studentRepository.findStudentById(id);
        if (student != null) {
            return student;
        }
        return null;
    }

    /**
     * Xóa student
     * @param id
     * */
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}
