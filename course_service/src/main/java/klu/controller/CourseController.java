package klu.controller;

import klu.model.Course;
import klu.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3030/")
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    // Create a new course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setCourse_Name(updatedCourse.getCourse_Name());
            course.setCourse_Year(updatedCourse.getCourse_Year());
            course.setCourse_Description(updatedCourse.getCourse_Description());
            course.setCourse_Credits(updatedCourse.getCourse_Credits());
            Course savedCourse = courseRepository.save(course);
            return ResponseEntity.ok(savedCourse);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a course
 // Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            // Return 200 OK with a custom message
            return ResponseEntity.ok("Course deleted successfully");
        }).orElse(ResponseEntity.notFound().build());
    }

}
