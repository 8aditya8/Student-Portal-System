// src/main/java/edu/portal/ui/AdminPanelComponents.java
package edu.portal.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Placeholder classes for Admin Panel Features 3-6
 */

// Feature 3: Attendance and Grade Oversight
class AdminAttendanceGradesPanel extends JPanel {
    public AdminAttendanceGradesPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("📋 Attendance & Grade Oversight", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        add(titleLabel, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("📊 Attendance Reports", createAttendanceReportsPanel());
        tabs.addTab("📈 Grade Summaries", createGradeSummariesPanel());
        tabs.addTab("📋 Reporting Dashboard", createReportingDashboardPanel());
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private JPanel createAttendanceReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("ATTENDANCE OVERVIEW\n" +
                        "==================\n\n" +
                        "📊 Overall Attendance Rate: 89.5%\n" +
                        "📈 This Week: 92.3%\n" +
                        "📉 Last Week: 87.1%\n\n" +
                        "TOP PERFORMING COURSES:\n" +
                        "• Mathematics: 95.2%\n" +
                        "• Physics: 93.8%\n" +
                        "• Chemistry: 91.5%\n\n" +
                        "COURSES NEEDING ATTENTION:\n" +
                        "• History: 82.1%\n" +
                        "• Literature: 84.3%");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createGradeSummariesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("GRADE DISTRIBUTION SUMMARY\n" +
                        "=========================\n\n" +
                        "📊 Overall GPA: 3.42\n" +
                        "🎯 Grade Distribution:\n" +
                        "• A Grades: 28%\n" +
                        "• B Grades: 35%\n" +
                        "• C Grades: 25%\n" +
                        "• D Grades: 8%\n" +
                        "• F Grades: 4%\n\n" +
                        "TOP PERFORMING STUDENTS:\n" +
                        "• student1: 3.95 GPA\n" +
                        "• jane_smith: 3.87 GPA\n" +
                        "• john_doe: 3.75 GPA");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createReportingDashboardPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(createReportCard("📊 Attendance Analytics", "Generate detailed attendance reports", new Color(33, 150, 243)));
        panel.add(createReportCard("📈 Grade Analytics", "Comprehensive grade analysis", new Color(76, 175, 80)));
        panel.add(createReportCard("📋 Performance Reports", "Student performance summaries", new Color(255, 152, 0)));
        panel.add(createReportCard("��� Trend Analysis", "Historical data trends", new Color(156, 39, 176)));
        
        return panel;
    }
    
    private JPanel createReportCard(String title, String description, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(color);
        card.add(titleLabel, BorderLayout.NORTH);
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(descLabel, BorderLayout.CENTER);
        
        JButton actionBtn = new JButton("Generate Report");
        actionBtn.setBackground(color);
        actionBtn.setForeground(Color.WHITE);
        actionBtn.setFocusPainted(false);
        card.add(actionBtn, BorderLayout.SOUTH);
        
        return card;
    }
}

// Feature 4: Scheduling and Timetable Control
class AdminSchedulingPanel extends JPanel {
    public AdminSchedulingPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("📅 Scheduling & Timetable Control", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        add(titleLabel, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("📅 Class Schedules", createSchedulePanel());
        tabs.addTab("⚠️ Conflict Monitor", createConflictPanel());
        tabs.addTab("🏢 Room Allocation", createRoomAllocationPanel());
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private JPanel createSchedulePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Create a simple timetable grid
        String[] columns = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[][] data = {
            {"9:00-10:00", "CS101 - Room 101", "MATH201 - Room 205", "CS101 - Room 101", "", "MATH201 - Room 205"},
            {"10:00-11:00", "", "PHYS101 - Lab 301", "", "ENG101 - Room 150", ""},
            {"11:00-12:00", "HIST201 - Room 220", "", "PHYS101 - Lab 301", "MATH201 - Room 205", "HIST201 - Room 220"},
            {"1:00-2:00", "PHYS101 - Lab 301", "ENG101 - Room 150", "HIST201 - Room 220", "", "CS101 - Room 101"},
            {"2:00-3:00", "", "", "", "PHYS101 - Lab 301", ""}
        };
        
        JTable scheduleTable = new JTable(data, columns);
        scheduleTable.setRowHeight(40);
        panel.add(new JScrollPane(scheduleTable), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addScheduleBtn = new JButton("➕ Add Schedule");
        JButton editScheduleBtn = new JButton("✏️ Edit Schedule");
        JButton deleteScheduleBtn = new JButton("🗑️ Delete Schedule");
        
        buttonPanel.add(addScheduleBtn);
        buttonPanel.add(editScheduleBtn);
        buttonPanel.add(deleteScheduleBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createConflictPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("SCHEDULE CONFLICT MONITOR\n" +
                        "========================\n\n" +
                        "🟢 No conflicts detected in current schedule\n\n" +
                        "RECENT CONFLICT RESOLUTIONS:\n" +
                        "• Resolved: CS101 room conflict (moved to Room 102)\n" +
                        "• Resolved: MATH201 instructor double-booking\n" +
                        "• Resolved: Lab 301 equipment conflict\n\n" +
                        "OPTIMIZATION SUGGESTIONS:\n" +
                        "• Room 205 underutilized on Fridays\n" +
                        "• Consider moving ENG101 to larger room\n" +
                        "• Lab 301 could accommodate one more session");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createRoomAllocationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"Room", "Capacity", "Type", "Equipment", "Current Usage", "Availability"};
        String[][] data = {
            {"Room 101", "30", "Classroom", "Projector, Whiteboard", "CS101", "Available"},
            {"Room 150", "35", "Classroom", "Projector, Audio System", "ENG101", "Available"},
            {"Room 205", "25", "Classroom", "Projector, Whiteboard", "MATH201", "Available"},
            {"Room 220", "40", "Lecture Hall", "Projector, Microphone", "HIST201", "Available"},
            {"Lab 301", "20", "Laboratory", "Computers, Equipment", "PHYS101", "Busy"}
        };
        
        JTable roomTable = new JTable(data, columns);
        panel.add(new JScrollPane(roomTable), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton allocateBtn = new JButton("🏢 Allocate Room");
        JButton optimizeBtn = new JButton("⚡ Optimize Allocation");
        JButton reportBtn = new JButton("📊 Usage Report");
        
        buttonPanel.add(allocateBtn);
        buttonPanel.add(optimizeBtn);
        buttonPanel.add(reportBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
}

// Feature 5: Fee and Invoicing Tools
class AdminFeeManagementPanel extends JPanel {
    public AdminFeeManagementPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("💰 Fee & Invoicing Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        add(titleLabel, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("💳 Fee Tracking", createFeeTrackingPanel());
        tabs.addTab("📄 Invoice Generation", createInvoicePanel());
        tabs.addTab("💰 Payment Monitoring", createPaymentPanel());
        tabs.addTab("📊 Financial Reports", createFinancialReportsPanel());
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private JPanel createFeeTrackingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"Student", "Fee Type", "Amount", "Due Date", "Status", "Balance"};
        String[][] data = {
            {"student1", "Tuition", "$2,500", "2024-01-15", "PAID", "$0"},
            {"john_doe", "Tuition", "$2,500", "2024-01-15", "PARTIAL", "$1,200"},
            {"jane_smith", "Lab Fee", "$150", "2024-01-10", "OVERDUE", "$150"},
            {"student1", "Library Fee", "$50", "2024-01-20", "PENDING", "$50"},
            {"john_doe", "Transport", "$200", "2024-01-25", "PENDING", "$200"}
        };
        
        JTable feeTable = new JTable(data, columns);
        panel.add(new JScrollPane(feeTable), BorderLayout.CENTER);
        
        JPanel summaryPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Fee Summary"));
        
        summaryPanel.add(createSummaryCard("Total Due", "$15,750", new Color(220, 53, 69)));
        summaryPanel.add(createSummaryCard("Collected", "$12,150", new Color(40, 167, 69)));
        summaryPanel.add(createSummaryCard("Pending", "$2,400", new Color(255, 193, 7)));
        summaryPanel.add(createSummaryCard("Overdue", "$1,200", new Color(220, 53, 69)));
        
        panel.add(summaryPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createSummaryCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        card.add(titleLabel, BorderLayout.NORTH);
        
        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        valueLabel.setForeground(color);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createInvoicePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("INVOICE GENERATION SYSTEM\n" +
                        "=========================\n\n" +
                        "📄 Recent Invoices Generated:\n" +
                        "• INV-2024-001: student1 - Tuition Fee\n" +
                        "• INV-2024-002: john_doe - Lab Fee\n" +
                        "• INV-2024-003: jane_smith - Library Fee\n\n" +
                        "📊 Invoice Statistics:\n" +
                        "• Total Invoices: 156\n" +
                        "• Paid Invoices: 142\n" +
                        "• Pending Invoices: 14\n" +
                        "• Collection Rate: 91.0%");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton generateBtn = new JButton("📄 Generate Invoice");
        JButton bulkBtn = new JButton("📋 Bulk Generate");
        JButton templateBtn = new JButton("📝 Manage Templates");
        
        buttonPanel.add(generateBtn);
        buttonPanel.add(bulkBtn);
        buttonPanel.add(templateBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("PAYMENT MONITORING DASHBOARD\n" +
                        "============================\n\n" +
                        "💰 Today's Payments: $3,450\n" +
                        "📈 This Week: $12,750\n" +
                        "📊 This Month: $45,200\n\n" +
                        "PAYMENT METHODS:\n" +
                        "• Online: 65%\n" +
                        "• Bank Transfer: 25%\n" +
                        "• Cash: 8%\n" +
                        "• Cheque: 2%\n\n" +
                        "RECENT TRANSACTIONS:\n" +
                        "• $2,500 - student1 (Online)\n" +
                        "• $150 - jane_smith (Bank Transfer)\n" +
                        "• $800 - john_doe (Online)");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createFinancialReportsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(createReportCard("📊 Revenue Report", "Monthly revenue analysis", new Color(40, 167, 69)));
        panel.add(createReportCard("📈 Collection Report", "Fee collection statistics", new Color(23, 162, 184)));
        panel.add(createReportCard("⚠️ Outstanding Report", "Overdue payments tracking", new Color(220, 53, 69)));
        panel.add(createReportCard("📋 Summary Report", "Comprehensive financial summary", new Color(111, 66, 193)));
        
        return panel;
    }
    
    private JPanel createReportCard(String title, String description, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(color);
        card.add(titleLabel, BorderLayout.NORTH);
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(descLabel, BorderLayout.CENTER);
        
        JButton actionBtn = new JButton("Generate");
        actionBtn.setBackground(color);
        actionBtn.setForeground(Color.WHITE);
        actionBtn.setFocusPainted(false);
        card.add(actionBtn, BorderLayout.SOUTH);
        
        return card;
    }
}

// Feature 6: Audit Logs and Notifications
class AdminAuditPanel extends JPanel {
    public AdminAuditPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("🔍 Audit Logs & Notifications", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        add(titleLabel, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("📋 Activity Logs", createActivityLogsPanel());
        tabs.addTab("🔒 Security Logs", createSecurityLogsPanel());
        tabs.addTab("📢 Notifications", createNotificationsPanel());
        tabs.addTab("📊 Compliance Reports", createCompliancePanel());
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private JPanel createActivityLogsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"Timestamp", "User", "Action", "Entity", "Details", "IP Address"};
        String[][] data = {
            {"2024-01-15 10:30:15", "admin", "LOGIN", "USER", "Successful login", "192.168.1.100"},
            {"2024-01-15 10:32:45", "admin", "CREATE", "USER", "Created user: student2", "192.168.1.100"},
            {"2024-01-15 10:35:20", "instructor1", "UPDATE", "GRADE", "Updated grade for CS101", "192.168.1.105"},
            {"2024-01-15 10:40:10", "student1", "VIEW", "GRADES", "Accessed grade report", "192.168.1.110"},
            {"2024-01-15 10:45:30", "admin", "DELETE", "COURSE", "Deleted course: OLD101", "192.168.1.100"}
        };
        
        JTable logTable = new JTable(data, columns);
        panel.add(new JScrollPane(logTable), BorderLayout.CENTER);
        
        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.add(new JLabel("Filter by:"));
        filterPanel.add(new JComboBox<>(new String[]{"All Actions", "LOGIN", "CREATE", "UPDATE", "DELETE", "VIEW"}));
        filterPanel.add(new JLabel("User:"));
        filterPanel.add(new JTextField(10));
        filterPanel.add(new JButton("🔍 Filter"));
        filterPanel.add(new JButton("📊 Export"));
        
        panel.add(filterPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createSecurityLogsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("SECURITY EVENT LOG\n" +
                        "==================\n\n" +
                        "🔐 Recent Security Events:\n" +
                        "2024-01-15 10:30:15 - Successful admin login from 192.168.1.100\n" +
                        "2024-01-15 09:45:23 - Failed login attempt for 'admin' from 192.168.1.200\n" +
                        "2024-01-15 09:30:12 - Password changed for user 'student1'\n" +
                        "2024-01-15 08:15:45 - User 'instructor1' session expired\n" +
                        "2024-01-15 08:00:00 - Daily security scan completed - No threats\n\n" +
                        "🛡️ Security Summary:\n" +
                        "• Failed Login Attempts (24h): 3\n" +
                        "• Active Sessions: 5\n" +
                        "• Security Score: 95/100\n" +
                        "• Last Security Scan: 08:00 AM\n" +
                        "• Threat Level: LOW");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton scanBtn = new JButton("🛡️ Security Scan");
        JButton alertBtn = new JButton("🚨 Security Alert");
        JButton reportBtn = new JButton("📊 Security Report");
        
        buttonPanel.add(scanBtn);
        buttonPanel.add(alertBtn);
        buttonPanel.add(reportBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createNotificationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel composePanel = new JPanel(new BorderLayout());
        composePanel.setBorder(BorderFactory.createTitledBorder("Broadcast Notification"));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Recipients:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JComboBox<>(new String[]{"All Users", "Students Only", "Instructors Only", "Admins Only"}), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Subject:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JTextField(30), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Message:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 0.3;
        formPanel.add(new JScrollPane(new JTextArea(5, 30)), gbc);
        
        composePanel.add(formPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton sendBtn = new JButton("📧 Send Notification");
        JButton scheduleBtn = new JButton("⏰ Schedule");
        JButton templateBtn = new JButton("📝 Use Template");
        
        buttonPanel.add(sendBtn);
        buttonPanel.add(scheduleBtn);
        buttonPanel.add(templateBtn);
        composePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        panel.add(composePanel, BorderLayout.NORTH);
        
        // Recent notifications
        JTextArea recentArea = new JTextArea();
        recentArea.setEditable(false);
        recentArea.setText("RECENT NOTIFICATIONS\n" +
                          "===================\n\n" +
                          "📧 Sent Today:\n" +
                          "• System Maintenance Notice (All Users)\n" +
                          "• Grade Release Announcement (Students)\n" +
                          "• Faculty Meeting Reminder (Instructors)\n\n" +
                          "📊 Notification Statistics:\n" +
                          "• Total Sent: 45\n" +
                          "• Delivery Rate: 98.5%\n" +
                          "• Open Rate: 87.2%");
        
        JScrollPane recentScrollPane = new JScrollPane(recentArea);
        recentScrollPane.setBorder(BorderFactory.createTitledBorder("Recent Activity"));
        panel.add(recentScrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createCompliancePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("COMPLIANCE & AUDIT REPORTS\n" +
                        "==========================\n\n" +
                        "📋 Compliance Status: ✅ COMPLIANT\n\n" +
                        "🔍 Recent Audit Activities:\n" +
                        "• Data Privacy Audit: PASSED\n" +
                        "• Security Compliance: PASSED\n" +
                        "• Access Control Review: PASSED\n" +
                        "• Data Backup Verification: PASSED\n\n" +
                        "📊 Audit Metrics:\n" +
                        "• User Access Reviews: 100% Complete\n" +
                        "• Data Retention Policy: Compliant\n" +
                        "• Security Policies: Up to Date\n" +
                        "• Backup Success Rate: 100%\n\n" +
                        "📅 Next Scheduled Audits:\n" +
                        "• Quarterly Security Review: Feb 15, 2024\n" +
                        "• Annual Compliance Audit: Jun 30, 2024");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton auditBtn = new JButton("🔍 Run Audit");
        JButton reportBtn = new JButton("📊 Generate Report");
        JButton exportBtn = new JButton("📤 Export Data");
        
        buttonPanel.add(auditBtn);
        buttonPanel.add(reportBtn);
        buttonPanel.add(exportBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
}