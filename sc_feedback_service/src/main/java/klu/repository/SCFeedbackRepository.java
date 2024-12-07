package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import klu.model.SCFeedback;

import java.util.List;

public interface SCFeedbackRepository extends JpaRepository<SCFeedback, Long> {
    List<SCFeedback> findByStudentStudentId(Long studentId);
   
    @Query("SELECT f FROM SCFeedback f WHERE f.course.course_Id = :courseId")
    List<SCFeedback> findByCourseId(@Param("courseId") Long courseId);
}
