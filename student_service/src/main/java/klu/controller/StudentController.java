package klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import klu.model.Student;
import klu.model.StudentManager;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3030/")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentManager studentManager;
    
    //GETTING ALL STUDENTS DATA
    @GetMapping
    public List<Student> getAllStudents() {
        return studentManager.getAllStudents();
    }

    //GETTING STUDENT DATA BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentManager.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //ADDING STUDENT DATA
    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentManager.saveStudent(student);
    }

    //UPDATING STUDENT DATA BY ID
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentManager.updateStudent(id, studentDetails);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETING STUDENT DATA BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentManager.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
    
}
