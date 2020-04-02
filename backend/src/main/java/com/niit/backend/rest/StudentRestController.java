package com.niit.backend.rest;

import com.niit.backend.entities.Student;
import com.niit.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student student1 = student;
        return ResponseEntity.ok(studentService.save(student));
    }
}
