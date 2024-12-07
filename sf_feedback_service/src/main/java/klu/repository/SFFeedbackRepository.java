package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import klu.model.SFFeedback;

import java.util.List;

public interface SFFeedbackRepository extends JpaRepository<SFFeedback, Long> {

    List<SFFeedback> findByStudentStudentId(Long studentId);

    @Query("SELECT f FROM SFFeedback f WHERE f.faculty.faculty_Id = :facultyId")
    List<SFFeedback> findByFacultyId(@Param("facultyId") Long facultyId);
}
