package com.niit.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.backend.entities.Class;
import com.niit.backend.entities.FileResponse;
import com.niit.backend.entities.Student;
import com.niit.backend.service.ClassService;
import com.niit.backend.service.StudentService;

@RestController
@RequestMapping("/api/class")
public class ClassRestController {
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/all")
    public ResponseEntity<List<Class>> findAll() {
        return ResponseEntity.ok(classService.findAll());
    }
	
	@GetMapping("/page")
    public ResponseEntity<Page<Class>> findAllByPageAndLimit(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "size", required = false) String size
    ) {
        return ResponseEntity.ok(classService.pageClass(page, size));
    }
	
	// CustomClassRepository search() method return null;
	@GetMapping("/search")
    public ResponseEntity<Page<Class>> searchClass(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "size", required = false) String size
    ) {
        return ResponseEntity.ok(classService.searchClass(keyword, page, size));
    }
	
	@GetMapping("/detail")
    public ResponseEntity<Class> findClassById(@RequestParam("id") Long id) {
        Class classObj = classService.findClassById(id);
        if (classObj != null) {
            return ResponseEntity.ok(classObj);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	@DeleteMapping("/delete")
    public ResponseEntity deleteClass(@RequestParam("id") Long id) {
        Class classObj = classService.findClassById(id);
        if (classObj != null) {
        	classService.deleteClassById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
	
	@GetMapping("/addstudent")
    public ResponseEntity<Class> addStudent(@RequestParam("classid") Long classid, @RequestParam("studentid") Long studentid) {
        Class classObj = classService.findClassById(classid);
        Student student = studentService.findStudentById(studentid);
        classService.addStudentToClass(classObj, student);
        if (classObj != null) {
            return ResponseEntity.ok(classObj);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	@GetMapping("/removestudent")
    public ResponseEntity<Class> removeStudent(@RequestParam("classid") Long classid, @RequestParam("studentid") Long studentid) {
        Class classObj = classService.findClassById(classid);
        Student student = studentService.findStudentById(studentid);
        classService.removeStudentFromClass(classObj, student);
        if (classObj != null) {
            return ResponseEntity.ok(classObj);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
}
