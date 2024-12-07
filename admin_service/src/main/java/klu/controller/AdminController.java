package klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import klu.model.Admin;
import klu.model.AdminManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3030", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminManager adminManager;

    // VALIDATING ADMIN
    @GetMapping("/validate/{id}/{password}")
    public ResponseEntity<Map<String, Integer>> validateAdmin(@PathVariable int id, @PathVariable String password) {
        int result = adminManager.validateAdmin(id, password);
        Map<String, Integer> response = new HashMap<String, Integer>();
        response.put("result", result);
        return ResponseEntity.ok(response);
    }

    // GETTING ALL ADMINS DATA
    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminManager.getAllAdmins();
    }

    // GETTING ADMIN DATA BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        return adminManager.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ADDING ADMIN DATA
    @PostMapping("/addAdmin")
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminManager.saveAdmin(admin);
    }

    // UPDATING ADMIN DATA BY ID
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin adminDetails) {
        try {
            Admin updatedAdmin = adminManager.updateAdmin(id, adminDetails);
            return ResponseEntity.ok(updatedAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETING ADMIN DATA BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable int id) {
        adminManager.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }
}
