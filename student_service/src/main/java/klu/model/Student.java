package klu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_firstname", nullable = false, length = 100)
    private String studentFirstName;

    @Column(name = "student_lastname", nullable = false, length = 100)
    private String studentLastName;

    @Column(name = "student_password", nullable = false, length = 255)
    private String studentPassword;

    @Column(name = "student_email", nullable = false, unique = true, length = 100)
    private String studentEmail;

    @Column(name = "student_contact", length = 15)
    private String studentContact;

    @Column(name = "student_dept", nullable = false, length = 100)
    private String studentDept;

    @Column(name = "student_year", nullable = false)
    private Integer studentYear;

    @Column(name = "student_gender", nullable = false, length = 10)
    private String studentGender;

    // Getters and Setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getStudentDept() {
        return studentDept;
    }

    public void setStudentDept(String studentDept) {
        this.studentDept = studentDept;
    }

    public Integer getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(Integer studentYear) {
        this.studentYear = studentYear;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", studentFirstName=" + studentFirstName + ", studentLastName="
                + studentLastName + ", studentPassword=" + studentPassword + ", studentEmail=" + studentEmail
                + ", studentContact=" + studentContact + ", studentDept=" + studentDept + ", studentYear=" + studentYear
                + ", studentGender=" + studentGender + "]";
    }
}
