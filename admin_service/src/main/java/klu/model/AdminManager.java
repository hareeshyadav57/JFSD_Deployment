package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import klu.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminManager {

    @Autowired
    private AdminRepository adminRepository;

    // Validate Admin by ID and Password
    public int validateAdmin(int id, String password) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.filter(a -> a.getPassword().equals(password)).isPresent() ? 1 : 0;
    }

    // Get all Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get Admin by ID
    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    // Save a new Admin
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Delete Admin by ID
    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

    // Update an existing Admin
    public Admin updateAdmin(int id, Admin updatedAdmin) {
        return adminRepository.findById(id)
                .map(existingAdmin -> {
                    existingAdmin.setName(updatedAdmin.getName());
                    existingAdmin.setEmailId(updatedAdmin.getEmailId());
                    existingAdmin.setPassword(updatedAdmin.getPassword());
                    return adminRepository.save(existingAdmin);
                }).orElseThrow(() -> new RuntimeException("Admin not found!"));
    }
}
