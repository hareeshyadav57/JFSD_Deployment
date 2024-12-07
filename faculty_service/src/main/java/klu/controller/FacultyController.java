package klu.controller;

import klu.model.Faculty;
import klu.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3030/")
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyRepository facultyRepository;

    // Create a new faculty
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    // Get all faculty records
    @GetMapping
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    // Get a faculty record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        return facultyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a faculty record
    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty updatedFaculty) {
        return facultyRepository.findById(id).map(faculty -> {
            faculty.setFaculty_Name(updatedFaculty.getFaculty_Name());
            faculty.setFaculty_Dept(updatedFaculty.getFaculty_Dept());
            faculty.setFaculty_Contact(updatedFaculty.getFaculty_Contact());
            faculty.setFaculty_Email(updatedFaculty.getFaculty_Email());
            Faculty savedFaculty = facultyRepository.save(faculty);
            return ResponseEntity.ok(savedFaculty);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a faculty record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id) {
        return facultyRepository.findById(id).map(faculty -> {
            facultyRepository.delete(faculty);
            // Return 200 OK with a custom success message
            return ResponseEntity.ok("Faculty record deleted successfully");
        }).orElse(ResponseEntity.notFound().build());
    }
}
