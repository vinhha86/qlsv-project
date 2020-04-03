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
}
