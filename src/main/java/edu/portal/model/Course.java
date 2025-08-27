// src/main/java/edu/portal/model/Course.java
package edu.portal.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Course {
    private int id;
    private String courseCode;
    private String courseName;
    private String description;
    private int capacity;
    private int enrolledCount;
    private String instructorUsername;
    private String schedule;
    private String room;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double credits;
    private String status; // ACTIVE, INACTIVE, COMPLETED
    private List<String> enrolledStudents;

    public Course() {
        this.enrolledStudents = new ArrayList<>();
    }

    public Course(String courseCode, String courseName, String description, int capacity, 
                  String instructorUsername, String schedule, String room, double credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.capacity = capacity;
        this.instructorUsername = instructorUsername;
        this.schedule = schedule;
        this.room = room;
        this.credits = credits;
        this.status = "ACTIVE";
        this.enrolledCount = 0;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getEnrolledCount() { return enrolledCount; }
    public void setEnrolledCount(int enrolledCount) { this.enrolledCount = enrolledCount; }

    public String getInstructorUsername() { return instructorUsername; }
    public void setInstructorUsername(String instructorUsername) { this.instructorUsername = instructorUsername; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public double getCredits() { return credits; }
    public void setCredits(double credits) { this.credits = credits; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<String> getEnrolledStudents() { return enrolledStudents; }
    public void setEnrolledStudents(List<String> enrolledStudents) { this.enrolledStudents = enrolledStudents; }

    public boolean hasAvailableSlots() {
        return enrolledCount < capacity;
    }

    public boolean enrollStudent(String studentUsername) {
        if (hasAvailableSlots() && !enrolledStudents.contains(studentUsername)) {
            enrolledStudents.add(studentUsername);
            enrolledCount++;
            return true;
        }
        return false;
    }

    public boolean unenrollStudent(String studentUsername) {
        if (enrolledStudents.remove(studentUsername)) {
            enrolledCount--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + enrolledCount + "/" + capacity + ")";
    }
}