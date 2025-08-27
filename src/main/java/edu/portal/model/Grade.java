// src/main/java/edu/portal/model/Grade.java
package edu.portal.model;

import java.time.LocalDateTime;

public class Grade {
    private int id;
    private String studentUsername;
    private String courseCode;
    private String assessmentType; // ASSIGNMENT, QUIZ, MIDTERM, FINAL, PROJECT
    private String assessmentName;
    private double maxPoints;
    private double earnedPoints;
    private String letterGrade;
    private double gpa;
    private String feedback;
    private LocalDateTime gradedAt;
    private String gradedBy;

    public Grade() {}

    public Grade(String studentUsername, String courseCode, String assessmentType, 
                 String assessmentName, double maxPoints, double earnedPoints) {
        this.studentUsername = studentUsername;
        this.courseCode = courseCode;
        this.assessmentType = assessmentType;
        this.assessmentName = assessmentName;
        this.maxPoints = maxPoints;
        this.earnedPoints = earnedPoints;
        this.gradedAt = LocalDateTime.now();
        calculateGrade();
    }

    private void calculateGrade() {
        double percentage = (earnedPoints / maxPoints) * 100;
        
        if (percentage >= 97) {
            letterGrade = "A+";
            gpa = 4.0;
        } else if (percentage >= 93) {
            letterGrade = "A";
            gpa = 4.0;
        } else if (percentage >= 90) {
            letterGrade = "A-";
            gpa = 3.7;
        } else if (percentage >= 87) {
            letterGrade = "B+";
            gpa = 3.3;
        } else if (percentage >= 83) {
            letterGrade = "B";
            gpa = 3.0;
        } else if (percentage >= 80) {
            letterGrade = "B-";
            gpa = 2.7;
        } else if (percentage >= 77) {
            letterGrade = "C+";
            gpa = 2.3;
        } else if (percentage >= 73) {
            letterGrade = "C";
            gpa = 2.0;
        } else if (percentage >= 70) {
            letterGrade = "C-";
            gpa = 1.7;
        } else if (percentage >= 67) {
            letterGrade = "D+";
            gpa = 1.3;
        } else if (percentage >= 65) {
            letterGrade = "D";
            gpa = 1.0;
        } else {
            letterGrade = "F";
            gpa = 0.0;
        }
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getAssessmentType() { return assessmentType; }
    public void setAssessmentType(String assessmentType) { this.assessmentType = assessmentType; }

    public String getAssessmentName() { return assessmentName; }
    public void setAssessmentName(String assessmentName) { this.assessmentName = assessmentName; }

    public double getMaxPoints() { return maxPoints; }
    public void setMaxPoints(double maxPoints) { this.maxPoints = maxPoints; }

    public double getEarnedPoints() { return earnedPoints; }
    public void setEarnedPoints(double earnedPoints) { 
        this.earnedPoints = earnedPoints;
        calculateGrade();
    }

    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public LocalDateTime getGradedAt() { return gradedAt; }
    public void setGradedAt(LocalDateTime gradedAt) { this.gradedAt = gradedAt; }

    public String getGradedBy() { return gradedBy; }
    public void setGradedBy(String gradedBy) { this.gradedBy = gradedBy; }

    public double getPercentage() {
        return (earnedPoints / maxPoints) * 100;
    }

    @Override
    public String toString() {
        return courseCode + " - " + assessmentName + ": " + letterGrade + " (" + 
               String.format("%.1f", getPercentage()) + "%)";
    }
}