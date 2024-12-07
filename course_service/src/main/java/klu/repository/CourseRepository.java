package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import klu.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
