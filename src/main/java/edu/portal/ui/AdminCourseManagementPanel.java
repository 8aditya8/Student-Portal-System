// src/main/java/edu/portal/ui/AdminCourseManagementPanel.java
package edu.portal.ui;

import edu.portal.model.Course;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Admin Panel Feature 2: Course and Enrollment Management
 * - Create/update courses, set capacity, assign instructors
 * - Manage student enrollments
 */
public class AdminCourseManagementPanel extends JPanel {
    private JTable courseTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> statusFilter;
    private List<Course> courses;

    public AdminCourseManagementPanel() {
        courses = new ArrayList<>();
        initSampleData();
        initComponents();
        setupLayout();
        loadCourses();
    }

    private void initSampleData() {
        courses.add(new Course("CS101", "Introduction to Programming", "Basic programming concepts", 30, "instructor1", "MWF 9:00-10:00", "Room 101", 3.0));
        courses.add(new Course("MATH201", "Calculus I", "Differential and integral calculus", 25, "instructor2", "TTh 11:00-12:30", "Room 205", 4.0));
        courses.add(new Course("PHYS101", "General Physics", "Mechanics and thermodynamics", 20, "instructor3", "MWF 2:00-3:00", "Lab 301", 4.0));
        courses.add(new Course("ENG101", "English Composition", "Academic writing skills", 35, "instructor4", "TTh 9:30-11:00", "Room 150", 3.0));
        courses.add(new Course("HIST201", "World History", "Global historical perspectives", 40, "instructor5", "MWF 1:00-2:00", "Room 220", 3.0));
    }

    private void initComponents() {
        searchField = new JTextField(20);
        statusFilter = new JComboBox<>(new String[]{"All Courses", "ACTIVE", "INACTIVE", "COMPLETED"});
        
        String[] columns = {"Course Code", "Course Name", "Instructor", "Capacity", "Enrolled", "Schedule", "Room", "Credits", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        courseTable = new JTable(tableModel);
        courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        searchField.addActionListener(e -> filterCourses());
        statusFilter.addActionListener(e -> filterCourses());
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("📚 Course & Enrollment Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Search and filter panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("🔍 Search:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Status:"));
        searchPanel.add(statusFilter);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Table panel
        JScrollPane tableScrollPane = new JScrollPane(courseTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Course List"));
        add(tableScrollPane, BorderLayout.CENTER);

        // Button panels
        JPanel courseButtonPanel = new JPanel(new FlowLayout());
        JButton addCourseBtn = new JButton("➕ Add Course");
        JButton editCourseBtn = new JButton("✏️ Edit Course");
        JButton deleteCourseBtn = new JButton("🗑️ Delete Course");
        JButton assignInstructorBtn = new JButton("👨‍🏫 Assign Instructor");
        
        courseButtonPanel.add(addCourseBtn);
        courseButtonPanel.add(editCourseBtn);
        courseButtonPanel.add(deleteCourseBtn);
        courseButtonPanel.add(assignInstructorBtn);

        JPanel enrollmentButtonPanel = new JPanel(new FlowLayout());
        JButton manageEnrollmentBtn = new JButton("📝 Manage Enrollments");
        JButton viewEnrolledBtn = new JButton("👥 View Enrolled Students");
        JButton setCapacityBtn = new JButton("🎯 Set Capacity");
        JButton enrollmentReportBtn = new JButton("📊 Enrollment Report");
        
        enrollmentButtonPanel.add(manageEnrollmentBtn);
        enrollmentButtonPanel.add(viewEnrolledBtn);
        enrollmentButtonPanel.add(setCapacityBtn);
        enrollmentButtonPanel.add(enrollmentReportBtn);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(courseButtonPanel, BorderLayout.NORTH);
        bottomPanel.add(enrollmentButtonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Style buttons
        styleButton(addCourseBtn, new Color(40, 167, 69));
        styleButton(editCourseBtn, new Color(255, 193, 7));
        styleButton(deleteCourseBtn, new Color(220, 53, 69));
        styleButton(assignInstructorBtn, new Color(111, 66, 193));
        styleButton(manageEnrollmentBtn, new Color(23, 162, 184));
        styleButton(viewEnrolledBtn, new Color(108, 117, 125));
        styleButton(setCapacityBtn, new Color(255, 140, 0));
        styleButton(enrollmentReportBtn, new Color(32, 201, 151));

        // Add action listeners
        addCourseBtn.addActionListener(this::addCourse);
        editCourseBtn.addActionListener(this::editCourse);
        deleteCourseBtn.addActionListener(this::deleteCourse);
        assignInstructorBtn.addActionListener(this::assignInstructor);
        manageEnrollmentBtn.addActionListener(this::manageEnrollment);
        viewEnrolledBtn.addActionListener(this::viewEnrolledStudents);
        setCapacityBtn.addActionListener(this::setCapacity);
        enrollmentReportBtn.addActionListener(this::generateEnrollmentReport);
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(160, 35));
    }

    private void loadCourses() {
        tableModel.setRowCount(0);
        for (Course course : courses) {
            Object[] row = {
                course.getCourseCode(),
                course.getCourseName(),
                course.getInstructorUsername(),
                course.getCapacity(),
                course.getEnrolledCount(),
                course.getSchedule(),
                course.getRoom(),
                course.getCredits(),
                course.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    private void filterCourses() {
        String searchText = searchField.getText().toLowerCase();
        String selectedStatus = (String) statusFilter.getSelectedItem();
        
        tableModel.setRowCount(0);
        for (Course course : courses) {
            boolean matchesSearch = searchText.isEmpty() || 
                                  course.getCourseCode().toLowerCase().contains(searchText) ||
                                  course.getCourseName().toLowerCase().contains(searchText);
            boolean matchesStatus = "All Courses".equals(selectedStatus) || 
                                  course.getStatus().equals(selectedStatus);
            
            if (matchesSearch && matchesStatus) {
                Object[] row = {
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getInstructorUsername(),
                    course.getCapacity(),
                    course.getEnrolledCount(),
                    course.getSchedule(),
                    course.getRoom(),
                    course.getCredits(),
                    course.getStatus()
                };
                tableModel.addRow(row);
            }
        }
    }

    private void addCourse(ActionEvent e) {
        CourseDialog dialog = new CourseDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add Course", null);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Course newCourse = dialog.getCourse();
            courses.add(newCourse);
            loadCourses();
            JOptionPane.showMessageDialog(this, "Course added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editCourse(ActionEvent e) {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Course course = courses.get(selectedRow);
        CourseDialog dialog = new CourseDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit Course", course);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Course updatedCourse = dialog.getCourse();
            courses.set(selectedRow, updatedCourse);
            loadCourses();
            JOptionPane.showMessageDialog(this, "Course updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteCourse(ActionEvent e) {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Course course = courses.get(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete course: " + course.getCourseCode() + "?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            courses.remove(selectedRow);
            loadCourses();
            JOptionPane.showMessageDialog(this, "Course deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void assignInstructor(ActionEvent e) {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Course course = courses.get(selectedRow);
        String newInstructor = JOptionPane.showInputDialog(this, 
            "Enter instructor username for " + course.getCourseCode() + ":", 
            course.getInstructorUsername());
        
        if (newInstructor != null && !newInstructor.trim().isEmpty()) {
            course.setInstructorUsername(newInstructor.trim());
            loadCourses();
            JOptionPane.showMessageDialog(this, "Instructor assigned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void manageEnrollment(ActionEvent e) {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Course course = courses.get(selectedRow);
        EnrollmentDialog dialog = new EnrollmentDialog((Frame) SwingUtilities.getWindowAncestor(this), course);
        dialog.setVisible(true);
        loadCourses();
    }

    private void viewEnrolledStudents(ActionEvent e) {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Course course = courses.get(selectedRow);
        StringBuilder studentList = new StringBuilder();
        studentList.append("Enrolled Students in ").append(course.getCourseCode()).append(":\n\n");
        
        if (course.getEnrolledStudents().isEmpty()) {
            studentList.append("No students enrolled yet.");
        } else {
            for (int i = 0; i < course.getEnrolledStudents().size(); i++) {
                studentList.append(i + 1).append(". ").append(course.getEnrolledStudents().get(i)).append("\n");
            }
        }
        
        JTextArea textArea = new JTextArea(studentList.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Enrolled Students", JOptionPane.INFORMATION_MESSAGE);
    }

    private void setCapacity(ActionEvent e) {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Course course = courses.get(selectedRow);
        String capacityStr = JOptionPane.showInputDialog(this, 
            "Enter new capacity for " + course.getCourseCode() + ":", 
            course.getCapacity());
        
        if (capacityStr != null) {
            try {
                int newCapacity = Integer.parseInt(capacityStr.trim());
                if (newCapacity >= course.getEnrolledCount()) {
                    course.setCapacity(newCapacity);
                    loadCourses();
                    JOptionPane.showMessageDialog(this, "Capacity updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Capacity cannot be less than enrolled students (" + course.getEnrolledCount() + ")", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void generateEnrollmentReport(ActionEvent e) {
        StringBuilder report = new StringBuilder();
        report.append("ENROLLMENT REPORT\n");
        report.append("=================\n\n");
        
        int totalCourses = courses.size();
        int totalCapacity = courses.stream().mapToInt(Course::getCapacity).sum();
        int totalEnrolled = courses.stream().mapToInt(Course::getEnrolledCount).sum();
        double utilizationRate = totalCapacity > 0 ? (double) totalEnrolled / totalCapacity * 100 : 0;
        
        report.append("Summary:\n");
        report.append("- Total Courses: ").append(totalCourses).append("\n");
        report.append("- Total Capacity: ").append(totalCapacity).append("\n");
        report.append("- Total Enrolled: ").append(totalEnrolled).append("\n");
        report.append("- Utilization Rate: ").append(String.format("%.1f%%", utilizationRate)).append("\n\n");
        
        report.append("Course Details:\n");
        for (Course course : courses) {
            double courseUtilization = course.getCapacity() > 0 ? 
                (double) course.getEnrolledCount() / course.getCapacity() * 100 : 0;
            report.append("- ").append(course.getCourseCode()).append(": ")
                  .append(course.getEnrolledCount()).append("/").append(course.getCapacity())
                  .append(" (").append(String.format("%.1f%%", courseUtilization)).append(")\n");
        }
        
        JTextArea textArea = new JTextArea(report.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Enrollment Report", JOptionPane.INFORMATION_MESSAGE);
    }

    // Inner classes for dialogs would be implemented here
    private static class CourseDialog extends JDialog {
        private Course course;
        private boolean confirmed = false;
        private JTextField codeField, nameField, descField, instructorField, scheduleField, roomField, creditsField;
        private JSpinner capacitySpinner;

        public CourseDialog(Frame parent, String title, Course course) {
            super(parent, title, true);
            this.course = course;
            initComponents();
            setupLayout();
            if (course != null) {
                populateFields();
            }
            pack();
            setLocationRelativeTo(parent);
        }

        private void initComponents() {
            codeField = new JTextField(10);
            nameField = new JTextField(20);
            descField = new JTextField(30);
            instructorField = new JTextField(15);
            scheduleField = new JTextField(15);
            roomField = new JTextField(10);
            creditsField = new JTextField(5);
            capacitySpinner = new JSpinner(new SpinnerNumberModel(20, 1, 100, 1));
        }

        private void setupLayout() {
            setLayout(new BorderLayout());
            
            JPanel formPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            
            gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(new JLabel("Course Code:"), gbc);
            gbc.gridx = 1;
            formPanel.add(codeField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 1;
            formPanel.add(new JLabel("Course Name:"), gbc);
            gbc.gridx = 1;
            formPanel.add(nameField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 2;
            formPanel.add(new JLabel("Description:"), gbc);
            gbc.gridx = 1;
            formPanel.add(descField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 3;
            formPanel.add(new JLabel("Instructor:"), gbc);
            gbc.gridx = 1;
            formPanel.add(instructorField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 4;
            formPanel.add(new JLabel("Schedule:"), gbc);
            gbc.gridx = 1;
            formPanel.add(scheduleField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 5;
            formPanel.add(new JLabel("Room:"), gbc);
            gbc.gridx = 1;
            formPanel.add(roomField, gbc);
            
            gbc.gridx = 0; gbc.gridy = 6;
            formPanel.add(new JLabel("Capacity:"), gbc);
            gbc.gridx = 1;
            formPanel.add(capacitySpinner, gbc);
            
            gbc.gridx = 0; gbc.gridy = 7;
            formPanel.add(new JLabel("Credits:"), gbc);
            gbc.gridx = 1;
            formPanel.add(creditsField, gbc);
            
            add(formPanel, BorderLayout.CENTER);
            
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Cancel");
            
            okButton.addActionListener(e -> {
                if (validateAndSave()) {
                    confirmed = true;
                    dispose();
                }
            });
            
            cancelButton.addActionListener(e -> dispose());
            
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        private void populateFields() {
            codeField.setText(course.getCourseCode());
            nameField.setText(course.getCourseName());
            descField.setText(course.getDescription());
            instructorField.setText(course.getInstructorUsername());
            scheduleField.setText(course.getSchedule());
            roomField.setText(course.getRoom());
            capacitySpinner.setValue(course.getCapacity());
            creditsField.setText(String.valueOf(course.getCredits()));
        }

        private boolean validateAndSave() {
            try {
                String code = codeField.getText().trim();
                String name = nameField.getText().trim();
                String desc = descField.getText().trim();
                String instructor = instructorField.getText().trim();
                String schedule = scheduleField.getText().trim();
                String room = roomField.getText().trim();
                int capacity = (Integer) capacitySpinner.getValue();
                double credits = Double.parseDouble(creditsField.getText().trim());
                
                if (code.isEmpty() || name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Course code and name are required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                
                if (course == null) {
                    course = new Course();
                }
                
                course.setCourseCode(code);
                course.setCourseName(name);
                course.setDescription(desc);
                course.setInstructorUsername(instructor);
                course.setSchedule(schedule);
                course.setRoom(room);
                course.setCapacity(capacity);
                course.setCredits(credits);
                
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for credits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        public boolean isConfirmed() { return confirmed; }
        public Course getCourse() { return course; }
    }

    private static class EnrollmentDialog extends JDialog {
        private Course course;
        private DefaultListModel<String> availableModel, enrolledModel;
        private JList<String> availableList, enrolledList;

        public EnrollmentDialog(Frame parent, Course course) {
            super(parent, "Manage Enrollment - " + course.getCourseCode(), true);
            this.course = course;
            initComponents();
            setupLayout();
            loadData();
            pack();
            setLocationRelativeTo(parent);
        }

        private void initComponents() {
            availableModel = new DefaultListModel<>();
            enrolledModel = new DefaultListModel<>();
            availableList = new JList<>(availableModel);
            enrolledList = new JList<>(enrolledModel);
        }

        private void setupLayout() {
            setLayout(new BorderLayout());
            
            JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 0));
            
            // Available students panel
            JPanel availablePanel = new JPanel(new BorderLayout());
            availablePanel.setBorder(BorderFactory.createTitledBorder("Available Students"));
            availablePanel.add(new JScrollPane(availableList), BorderLayout.CENTER);
            
            JButton enrollButton = new JButton("Enroll →");
            enrollButton.addActionListener(e -> enrollStudent());
            availablePanel.add(enrollButton, BorderLayout.SOUTH);
            
            // Enrolled students panel
            JPanel enrolledPanel = new JPanel(new BorderLayout());
            enrolledPanel.setBorder(BorderFactory.createTitledBorder("Enrolled Students"));
            enrolledPanel.add(new JScrollPane(enrolledList), BorderLayout.CENTER);
            
            JButton unenrollButton = new JButton("← Unenroll");
            unenrollButton.addActionListener(e -> unenrollStudent());
            enrolledPanel.add(unenrollButton, BorderLayout.SOUTH);
            
            mainPanel.add(availablePanel);
            mainPanel.add(enrolledPanel);
            add(mainPanel, BorderLayout.CENTER);
            
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(closeButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        private void loadData() {
            // Load sample available students
            String[] sampleStudents = {"student1", "student2", "student3", "john_doe", "jane_smith", "alice_brown", "bob_wilson"};
            
            availableModel.clear();
            for (String student : sampleStudents) {
                if (!course.getEnrolledStudents().contains(student)) {
                    availableModel.addElement(student);
                }
            }
            
            enrolledModel.clear();
            for (String student : course.getEnrolledStudents()) {
                enrolledModel.addElement(student);
            }
        }

        private void enrollStudent() {
            String selectedStudent = availableList.getSelectedValue();
            if (selectedStudent != null) {
                if (course.enrollStudent(selectedStudent)) {
                    availableModel.removeElement(selectedStudent);
                    enrolledModel.addElement(selectedStudent);
                } else {
                    JOptionPane.showMessageDialog(this, "Cannot enroll student. Course may be full.", "Enrollment Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private void unenrollStudent() {
            String selectedStudent = enrolledList.getSelectedValue();
            if (selectedStudent != null) {
                if (course.unenrollStudent(selectedStudent)) {
                    enrolledModel.removeElement(selectedStudent);
                    availableModel.addElement(selectedStudent);
                }
            }
        }
    }
}