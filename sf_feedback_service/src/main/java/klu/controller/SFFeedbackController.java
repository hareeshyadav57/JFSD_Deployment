package klu.controller;

import klu.model.SFFeedback;
import klu.repository.SFFeedbackRepository;
import klu.repository.StudentRepository;
import klu.repository.FacultyRepository;
import klu.model.Student;
import klu.model.Faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3030/")
@RequestMapping("/student-faculty-feedback")
public class SFFeedbackController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private SFFeedbackRepository sfFeedbackRepository;

    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody SFFeedback sfFeedback) {
        if (sfFeedback.getStudent() == null || sfFeedback.getStudent().getStudentId() == null ||
            sfFeedback.getFaculty() == null || sfFeedback.getFaculty().getFaculty_Id() == null) {
            return ResponseEntity.badRequest().body("Valid Student and Faculty IDs must be provided.");
        }

        // Fetch the Student and Faculty entities from the database
        Optional<Student> studentOptional = studentRepository.findById(sfFeedback.getStudent().getStudentId());
        Optional<Faculty> facultyOptional = facultyRepository.findById(sfFeedback.getFaculty().getFaculty_Id());

        if (studentOptional.isEmpty() || facultyOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Student or Faculty not found.");
        }

        // Set the persisted entities to avoid transient errors
        sfFeedback.setStudent(studentOptional.get());
        sfFeedback.setFaculty(facultyOptional.get());

        // Save the feedback
        SFFeedback savedFeedback = sfFeedbackRepository.save(sfFeedback);
        return ResponseEntity.ok(savedFeedback);
    }

    // Get all feedback entries
    @GetMapping
    public List<SFFeedback> getAllFeedback() {
        return sfFeedbackRepository.findAll();
    }

    // Get feedback by student ID
    @GetMapping("/student/{studentId}")
    public List<SFFeedback> getFeedbackByStudent(@PathVariable Long studentId) {
        return sfFeedbackRepository.findByStudentStudentId(studentId);
    }

    // Get feedback by faculty ID
    @GetMapping("/faculty/{facultyId}")
    public List<SFFeedback> getFeedbackByFaculty(@PathVariable Long facultyId) {
        return sfFeedbackRepository.findByFacultyId(facultyId);
    }

    // Update feedback
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable Long id, @RequestBody SFFeedback updatedFeedback) {
        return sfFeedbackRepository.findById(id).map(feedback -> {
            if (updatedFeedback.getFeedback() != null) {
                feedback.setFeedback(updatedFeedback.getFeedback());
            }
            SFFeedback savedFeedback = sfFeedbackRepository.save(feedback);
            return ResponseEntity.ok(savedFeedback);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete feedback
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        return sfFeedbackRepository.findById(id).map(feedback -> {
            sfFeedbackRepository.delete(feedback);
            return ResponseEntity.ok("Feedback deleted successfully.");
        }).orElse(ResponseEntity.notFound().build());
    }
}
