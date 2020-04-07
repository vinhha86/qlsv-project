package com.niit.backend.rest;

import com.niit.backend.entities.Course;
import com.niit.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseRestController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Course>> findAllByPageAndLimit(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "size", required = false) String size
    ) {
        return ResponseEntity.ok(courseService.pageCourse(page, size));
    }

    @GetMapping("/pageType")
    public ResponseEntity<Page<Course>> findAllByTypeWithPageAndLimit(
            @RequestParam(value = "type") int type,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "size", required = false) String size
    ) {
        return ResponseEntity.ok(courseService.pageCourseByType(type, page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Course>> searchCourse(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "size", required = false) String size
    ) {
        return ResponseEntity.ok(courseService.searchCourse(keyword, page, size));
    }

    @GetMapping("/detail")
    public ResponseEntity<Course> findStudentById(@RequestParam("id") Long id) {
//        Course student = courseService.f(id);
//        if (student != null) {
//            return ResponseEntity.ok(student);
//        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCourse(@RequestParam("id") Long id) {

//        return new ResponseEntity(HttpStatus.OK);
        try {
            courseService.deleteCourseById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        try {
            Course savedCourse = courseService.save(course);
            return ResponseEntity.ok(savedCourse);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
