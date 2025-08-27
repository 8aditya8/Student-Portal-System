// src/main/java/edu/portal/model/Attendance.java
package edu.portal.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Attendance {
    private int id;
    private String studentUsername;
    private String courseCode;
    private LocalDate attendanceDate;
    private String status; // PRESENT, ABSENT, LATE, EXCUSED
    private String remarks;
    private LocalDateTime recordedAt;
    private String recordedBy;

    public Attendance() {}

    public Attendance(String studentUsername, String courseCode, LocalDate attendanceDate, String status) {
        this.studentUsername = studentUsername;
        this.courseCode = courseCode;
        this.attendanceDate = attendanceDate;
        this.status = status;
        this.recordedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentUsername() { return studentUsername; }
    public void setStudentUsername(String studentUsername) { this.studentUsername = studentUsername; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public String getRecordedBy() { return recordedBy; }
    public void setRecordedBy(String recordedBy) { this.recordedBy = recordedBy; }

    @Override
    public String toString() {
        return studentUsername + " - " + courseCode + " (" + attendanceDate + "): " + status;
    }
}