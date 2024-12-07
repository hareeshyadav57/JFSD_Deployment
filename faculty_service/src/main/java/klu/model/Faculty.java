package klu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long faculty_Id;
    
    private String faculty_Name;
    private String faculty_Dept;
    private String faculty_Contact;
    private String faculty_Email;

    // Getters and Setters
    public Long getFaculty_Id() {
        return faculty_Id;
    }

    public void setFaculty_Id(Long faculty_Id) {
        this.faculty_Id = faculty_Id;
    }

    public String getFaculty_Name() {
        return faculty_Name;
    }

    public void setFaculty_Name(String faculty_Name) {
        this.faculty_Name = faculty_Name;
    }

    public String getFaculty_Dept() {
        return faculty_Dept;
    }

    public void setFaculty_Dept(String faculty_Dept) {
        this.faculty_Dept = faculty_Dept;
    }

    public String getFaculty_Contact() {
        return faculty_Contact;
    }

    public void setFaculty_Contact(String faculty_Contact) {
        this.faculty_Contact = faculty_Contact;
    }

    public String getFaculty_Email() {
        return faculty_Email;
    }

    public void setFaculty_Email(String faculty_Email) {
        this.faculty_Email = faculty_Email;
    }
}
