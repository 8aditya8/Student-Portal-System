// src/main/java/edu/portal/model/Enrollment.java
package edu.portal.model;

import java.time.LocalDateTime;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private String studentName;
    private String courseName;
    private String courseCode;
    private LocalDateTime enrollmentDate;
    private String status; // ENROLLED, PENDING, DROPPED, COMPLETED
    private double grade;
    private String letterGrade;
    private int attendanceCount;
    private int totalClasses;

    // Constructors
    public Enrollment() {}

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDateTime.now();
        this.status = "ENROLLED";
        this.grade = 0.0;
        this.attendanceCount = 0;
        this.totalClasses = 0;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public LocalDateTime getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDateTime enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getGrade() { return grade; }
    public void setGrade(double grade) { 
        this.grade = grade;
        this.letterGrade = calculateLetterGrade(grade);
    }

    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }

    public int getAttendanceCount() { return attendanceCount; }
    public void setAttendanceCount(int attendanceCount) { this.attendanceCount = attendanceCount; }

    public int getTotalClasses() { return totalClasses; }
    public void setTotalClasses(int totalClasses) { this.totalClasses = totalClasses; }

    public double getAttendancePercentage() {
        if (totalClasses == 0) return 0.0;
        return (double) attendanceCount / totalClasses * 100;
    }

    private String calculateLetterGrade(double grade) {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + status + ")";
    }
}