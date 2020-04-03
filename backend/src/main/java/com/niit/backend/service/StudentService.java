package com.niit.backend.service;

import com.niit.backend.dao.repository.AddressRepository;
import com.niit.backend.dao.repository.FileRepository;
import com.niit.backend.dao.repository.StudentRepository;
import com.niit.backend.entities.FileResponse;
import com.niit.backend.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Student> findAll() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public Student save(Student student) {
        Student savedStudent = studentRepository.save(student);
        if (savedStudent != null) {
            return student;
        }
        return null;
    }

    public Student findStudentById(Long id) {
        Student student = studentRepository.findStudentById(id);
        if (student != null) {
            return student;
        }
        return null;
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}
