package com.niit.backend.dao.repository;

import com.niit.backend.dao.customRepository.CustomStudentRepository;
import com.niit.backend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, CustomStudentRepository {
    Student findStudentById(Long id);
}
