package com.niit.backend.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.backend.entities.FileResponse;
import com.niit.backend.entities.Student;
import com.niit.backend.service.FileStorageService;
import com.niit.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<Student> findStudentById(@RequestParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") Long id) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            studentService.deleteStudentById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> save(@RequestParam("student") String student, @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Student newStudent = mapper.readValue(student, Student.class);

            if (avatar != null) {
                FileResponse studentAvatar = fileStorageService.storeFile(avatar);
                newStudent.setAvatar(studentAvatar);
            }
            return ResponseEntity.ok(studentService.save(newStudent));
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("edit")
    public ResponseEntity<Student> edit(@RequestParam("student") String student, @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Student editStudent = mapper.readValue(student, Student.class);

            if (avatar != null) {
                FileResponse studentAvatar = fileStorageService.storeFile(avatar);
                editStudent.setAvatar(studentAvatar);
            }
            return ResponseEntity.ok(studentService.save(editStudent));
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
