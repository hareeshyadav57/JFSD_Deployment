package klu.controller;

import klu.model.SCFeedback;
import klu.repository.CourseRepository;
import klu.repository.SCFeedbackRepository;
import klu.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import klu.model.Student;
import klu.model.Course;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3030/")
@RequestMapping("/feedback")
public class SCFeedbackController {

	@Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SCFeedbackRepository scFeedbackRepository;

    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody SCFeedback scFeedback) {
        if (scFeedback.getStudent() == null || scFeedback.getStudent().getStudentId() == null || 
            scFeedback.getCourse() == null || scFeedback.getCourse().getCourse_Id() == null) {
            return ResponseEntity.badRequest().body("Valid Student and Course IDs must be provided.");
        }

        // Fetch the Student and Course entities from the database
        Optional<Student> studentOptional = studentRepository.findById(scFeedback.getStudent().getStudentId());
        Optional<Course> courseOptional = courseRepository.findById(scFeedback.getCourse().getCourse_Id());

        if (studentOptional.isEmpty() || courseOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Student or Course not found.");
        }

        // Set the persisted entities to avoid transient errors
        scFeedback.setStudent(studentOptional.get());
        scFeedback.setCourse(courseOptional.get());

        // Save the feedback
        SCFeedback savedFeedback = scFeedbackRepository.save(scFeedback);
        return ResponseEntity.ok(savedFeedback);
    }
    
    // Get all feedback entries
    @GetMapping
    public List<SCFeedback> getAllFeedback() {
        return scFeedbackRepository.findAll();
    }

    // Get feedback by student ID
    @GetMapping("/student/{studentId}")
    public List<SCFeedback> getFeedbackByStudent(@PathVariable Long studentId) {
        return scFeedbackRepository.findByStudentStudentId(studentId);
    }

    // Get feedback by course ID
    @GetMapping("/course/{courseId}")
    public List<SCFeedback> getFeedbackByCourse(@PathVariable Long courseId) {
        return scFeedbackRepository.findByCourseId(courseId);
    }


    // Update feedback
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable Long id, @RequestBody SCFeedback updatedFeedback) {
        return scFeedbackRepository.findById(id).map(feedback -> {
            if (updatedFeedback.getFeedback() != null) {
                feedback.setFeedback(updatedFeedback.getFeedback());
            }
            SCFeedback savedFeedback = scFeedbackRepository.save(feedback);
            return ResponseEntity.ok(savedFeedback);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete feedback
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        return scFeedbackRepository.findById(id).map(feedback -> {
            scFeedbackRepository.delete(feedback);
            return ResponseEntity.ok("Feedback deleted successfully.");
        }).orElse(ResponseEntity.notFound().build());
    }
}
