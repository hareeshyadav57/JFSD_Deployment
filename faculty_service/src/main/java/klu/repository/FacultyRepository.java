package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import klu.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
