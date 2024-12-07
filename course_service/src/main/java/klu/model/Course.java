package klu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long course_Id;
    
    private String course_Name;
    private int course_Year;
    private String course_Description;
    private int course_Credits;

    // Getters and Setters
    public Long getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(Long course_Id) {
        this.course_Id = course_Id;
    }

    public String getCourse_Name() {
        return course_Name;
    }

    public void setCourse_Name(String course_Name) {
        this.course_Name = course_Name;
    }

    public int getCourse_Year() {
        return course_Year;
    }

    public void setCourse_Year(int course_Year) {
        this.course_Year = course_Year;
    }

    public String getCourse_Description() {
        return course_Description;
    }

    public void setCourse_Description(String course_Description) {
        this.course_Description = course_Description;
    }

    public int getCourse_Credits() {
        return course_Credits;
    }

    public void setCourse_Credits(int course_Credits) {
        this.course_Credits = course_Credits;
    }
}
