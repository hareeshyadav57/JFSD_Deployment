package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import klu.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentManager {

    @Autowired
    private StudentRepository studentRepository;

    public int validateStudent(Long id, String password) {
        Optional<Student> student = studentRepository.findById(id);
        return student.filter(s -> s.getStudentPassword().equals(password)).isPresent() ? 1 : 0;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setStudentFirstName(updatedStudent.getStudentFirstName());
                    existingStudent.setStudentLastName(updatedStudent.getStudentLastName());
                    existingStudent.setStudentPassword(updatedStudent.getStudentPassword());
                    existingStudent.setStudentEmail(updatedStudent.getStudentEmail());
                    existingStudent.setStudentContact(updatedStudent.getStudentContact());
                    existingStudent.setStudentDept(updatedStudent.getStudentDept());
                    existingStudent.setStudentYear(updatedStudent.getStudentYear());
                    existingStudent.setStudentGender(updatedStudent.getStudentGender());
                    return studentRepository.save(existingStudent);
                }).orElseThrow(() -> new RuntimeException("Student not found!"));
    }
}
