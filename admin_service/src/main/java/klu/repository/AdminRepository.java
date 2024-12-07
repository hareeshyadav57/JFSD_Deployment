package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import klu.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    // You can add custom query methods if needed, for example:
    Admin findByEmailId(String emailId);
}
