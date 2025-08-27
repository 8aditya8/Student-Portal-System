// src/main/java/edu/portal/ui/StudentPanelComponents.java
package edu.portal.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Student Panel Features 2-6
 */

// Feature 2: Enrollment and Applications
class StudentEnrollmentPanel extends JPanel {
    public StudentEnrollmentPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("📝 Enrollment & Applications", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(76, 175, 80));
        add(titleLabel, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("📚 Course Enrollment", createCourseEnrollmentPanel());
        tabs.addTab("📋 Applications", createApplicationsPanel());
        tabs.addTab("🔄 Course Changes", createCourseChangesPanel());
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private JPanel createCourseEnrollmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Available courses
        String[] columns = {"Course Code", "Course Name", "Instructor", "Schedule", "Credits", "Available Slots", "Action"};
        String[][] data = {
            {"CS102", "Data Structures", "Dr. Smith", "MWF 10:00-11:00", "3", "5/30", "Enroll"},
            {"MATH202", "Calculus II", "Prof. Johnson", "TTh 2:00-3:30", "4", "8/25", "Enroll"},
            {"PHYS102", "Modern Physics", "Dr. Brown", "MWF 3:00-4:00", "4", "12/20", "Enroll"},
            {"ENG102", "Advanced Writing", "Prof. Davis", "TTh 11:00-12:30", "3", "15/35", "Enroll"},
            {"CHEM101", "General Chemistry", "Dr. Wilson", "MWF 1:00-2:00", "4", "3/30", "Enroll"}
        };
        
        JTable courseTable = new JTable(data, columns);
        panel.add(new JScrollPane(courseTable), BorderLayout.CENTER);
        
        // Current enrollments
        JPanel currentPanel = new JPanel(new BorderLayout());
        currentPanel.setBorder(BorderFactory.createTitledBorder("Current Enrollments"));
        
        String[] currentColumns = {"Course Code", "Course Name", "Credits", "Status"};
        String[][] currentData = {
            {"CS101", "Introduction to Programming", "3", "Enrolled"},
            {"MATH201", "Calculus I", "4", "Enrolled"},
            {"PHYS101", "General Physics", "4", "Enrolled"},
            {"ENG101", "English Composition", "3", "Enrolled"},
            {"HIST201", "World History", "3", "Enrolled"}
        };
        
        JTable currentTable = new JTable(currentData, currentColumns);
        currentPanel.add(new JScrollPane(currentTable), BorderLayout.CENTER);
        
        JPanel summaryPanel = new JPanel(new FlowLayout());
        summaryPanel.add(new JLabel("Total Credits: 17"));
        summaryPanel.add(new JLabel("Maximum Allowed: 20"));
        summaryPanel.add(new JLabel("Available: 3"));
        currentPanel.add(summaryPanel, BorderLayout.SOUTH);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, currentPanel);
        splitPane.setDividerLocation(300);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(splitPane, BorderLayout.CENTER);
        
        return mainPanel;
    }
    
    private JPanel createApplicationsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Submit New Application"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Application Type:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JComboBox<>(new String[]{"Course Enrollment", "Course Drop", "Grade Appeal", "Transfer Request", "Leave of Absence"}), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Course/Subject:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JTextField(20), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Reason:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 0.3;
        formPanel.add(new JScrollPane(new JTextArea(4, 20)), gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton submitBtn = new JButton("📤 Submit Application");
        JButton saveBtn = new JButton("💾 Save Draft");
        buttonPanel.add(submitBtn);
        buttonPanel.add(saveBtn);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weighty = 0;
        formPanel.add(buttonPanel, gbc);
        
        panel.add(formPanel, BorderLayout.NORTH);
        
        // Application history
        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setText("APPLICATION HISTORY\n" +
                           "==================\n\n" +
                           "📋 Recent Applications:\n" +
                           "• APP-2024-001: Course Enrollment (CS102) - PENDING\n" +
                           "• APP-2024-002: Grade Appeal (MATH201) - APPROVED\n" +
                           "• APP-2023-045: Course Drop (HIST101) - APPROVED\n\n" +
                           "📊 Application Statistics:\n" +
                           "• Total Applications: 8\n" +
                           "• Approved: 6\n" +
                           "• Pending: 1\n" +
                           "• Rejected: 1\n" +
                           "• Success Rate: 75%");
        
        JScrollPane historyScrollPane = new JScrollPane(historyArea);
        historyScrollPane.setBorder(BorderFactory.createTitledBorder("Application History"));
        panel.add(historyScrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createCourseChangesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("COURSE CHANGE MANAGEMENT\n" +
                        "========================\n\n" +
                        "📅 Add/Drop Period: January 15-22, 2024\n" +
                        "⏰ Status: OPEN\n\n" +
                        "🔄 Available Actions:\n" +
                        "• Add new courses (within credit limit)\n" +
                        "• Drop courses (with approval)\n" +
                        "• Change course sections\n" +
                        "• Audit to credit conversion\n\n" +
                        "📋 Recent Changes:\n" +
                        "• Dropped: HIST101 (Approved)\n" +
                        "• Added: CS102 (Pending approval)\n" +
                        "• Section change: MATH201 (Approved)\n\n" +
                        "⚠️ Important Notes:\n" +
                        "• Changes after deadline require special approval\n" +
                        "• Dropping courses may affect financial aid\n" +
                        "• Consult advisor before making changes");
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("➕ Add Course");
        JButton dropBtn = new JButton("➖ Drop Course");
        JButton sectionBtn = new JButton("🔄 Change Section");
        JButton advisorBtn = new JButton("👨‍🏫 Contact Advisor");
        
        buttonPanel.add(addBtn);
        buttonPanel.add(dropBtn);
        buttonPanel.add(sectionBtn);
        buttonPanel.add(advisorBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
}

// Feature 3: Attendance and Grades
class StudentAttendanceGradesPanel extends JPanel {
    public StudentAttendanceGradesPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("📊 Attendance & Grades", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(76, 175, 80));
        add(titleLabel, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("📅 Attendance Summary", createAttendanceSummaryPanel());
        tabs.addTab("📊 Grade Report", createGradeReportPanel());
        tabs.addTab("📈 Progress Tracking", createProgressTrackingPanel());
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private JPanel createAttendanceSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Attendance overview
        JPanel overviewPanel = new JPanel(new GridLayout(1, 5, 10, 0));
        overviewPanel.setBorder(BorderFactory.createTitledBorder("Attendance Overview"));
        
        overviewPanel.add(createAttendanceCard("Overall", "92%", new Color(76, 175, 80)));
        overviewPanel.add(createAttendanceCard("Mathematics", "95%", new Color(33, 150, 243)));
        overviewPanel.add(createAttendanceCard("Physics", "90%", new Color(255, 152, 0)));
        overviewPanel.add(createAttendanceCard("Chemistry", "88%", new Color(156, 39, 176)));
        overviewPanel.add(createAttendanceCard("Literature", "94%", new Color(244, 67, 54)));
        
        panel.add(overviewPanel, BorderLayout.NORTH);
        
        // Detailed attendance
        String[] columns = {"Course", "Total Classes", "Attended", "Absent", "Late", "Percentage"};
        String[][] data = {
            {"CS101", "45", "43", "2", "0", "95.6%"},
            {"MATH201", "40", "38", "1", "1", "95.0%"},
            {"PHYS101", "36", "32", "3", "1", "88.9%"},
            {"ENG101", "30", "28", "2", "0", "93.3%"},
            {"HIST201", "38", "36", "1", "1", "94.7%"}
        };
        
        JTable attendanceTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(attendanceTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Detailed Attendance"));
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Attendance alerts
        JTextArea alertsArea = new JTextArea(4, 0);
        alertsArea.setEditable(false);
        alertsArea.setText("📢 Attendance Alerts:\n" +
                          "• Physics: Attendance below 90% - Contact instructor\n" +
                          "• Overall: Good attendance record - Keep it up!\n" +
                          "• Reminder: Minimum 75% attendance required for exam eligibility");
        alertsArea.setBackground(new Color(255, 248, 220));
        
        JScrollPane alertsScrollPane = new JScrollPane(alertsArea);
        alertsScrollPane.setBorder(BorderFactory.createTitledBorder("Alerts & Reminders"));
        panel.add(alertsScrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createAttendanceCard(String course, String percentage, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);
        
        JLabel courseLabel = new JLabel(course, JLabel.CENTER);
        courseLabel.setFont(new Font("Arial", Font.BOLD, 12));
        card.add(courseLabel, BorderLayout.NORTH);
        
        JLabel percentLabel = new JLabel(percentage, JLabel.CENTER);
        percentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        percentLabel.setForeground(color);
        card.add(percentLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createGradeReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // GPA Summary
        JPanel gpaPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        gpaPanel.setBorder(BorderFactory.createTitledBorder("GPA Summary"));
        
        gpaPanel.add(createGPACard("Current GPA", "3.75", new Color(76, 175, 80)));
        gpaPanel.add(createGPACard("Previous Semester", "3.68", new Color(33, 150, 243)));
        gpaPanel.add(createGPACard("Cumulative GPA", "3.72", new Color(255, 152, 0)));
        gpaPanel.add(createGPACard("Class Rank", "15/120", new Color(156, 39, 176)));
        
        panel.add(gpaPanel, BorderLayout.NORTH);
        
        // Detailed grades
        String[] columns = {"Course", "Midterm", "Final", "Assignments", "Participation", "Overall Grade", "Credits"};
        String[][] data = {
            {"CS101", "A-", "A", "A", "A", "A-", "3"},
            {"MATH201", "A", "A-", "A", "A-", "A", "4"},
            {"PHYS101", "B+", "A-", "B+", "A", "A-", "4"},
            {"ENG101", "A-", "A", "A-", "A", "A-", "3"},
            {"HIST201", "A", "A", "A", "A", "A", "3"}
        };
        
        JTable gradesTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(gradesTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Course Grades"));
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Grade analysis
        JTextArea analysisArea = new JTextArea(4, 0);
        analysisArea.setEditable(false);
        analysisArea.setText("📊 Grade Analysis:\n" +
                            "• Strongest Subject: History (A average)\n" +
                            "• Most Improved: Physics (+0.3 from last semester)\n" +
                            "• Dean's List: Qualified (GPA > 3.5)\n" +
                            "• Honor Roll: Achieved for 2 consecutive semesters");
        analysisArea.setBackground(new Color(240, 248, 255));
        
        JScrollPane analysisScrollPane = new JScrollPane(analysisArea);
        analysisScrollPane.setBorder(BorderFactory.createTitledBorder("Performance Analysis"));
        panel.add(analysisScrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createGPACard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 11));
        card.add(titleLabel, BorderLayout.NORTH);
        
        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        valueLabel.setForeground(color);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createProgressTrackingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Progress charts (simulated with progress bars)
        JPanel chartsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        chartsPanel.setBorder(BorderFactory.createTitledBorder("Course Progress"));
        
        String[] courses = {"CS101", "MATH201", "PHYS101", "ENG101", "HIST201"};
        int[] progress = {85, 92, 78, 88, 95};
        Color[] colors = {
            new Color(33, 150, 243), new Color(76, 175, 80), new Color(255, 152, 0),
            new Color(156, 39, 176), new Color(244, 67, 54)
        };
        
        for (int i = 0; i < courses.length; i++) {
            JPanel coursePanel = new JPanel(new BorderLayout());
            coursePanel.add(new JLabel(courses[i]), BorderLayout.WEST);
            
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue(progress[i]);
            progressBar.setStringPainted(true);
            progressBar.setString(progress[i] + "%");
            progressBar.setForeground(colors[i]);
            coursePanel.add(progressBar, BorderLayout.CENTER);
            
            String grade = progress[i] >= 90 ? "A" : progress[i] >= 80 ? "B" : progress[i] >= 70 ? "C" : "D";
            coursePanel.add(new JLabel(grade), BorderLayout.EAST);
            
            chartsPanel.add(coursePanel);
        }
        
        panel.add(chartsPanel, BorderLayout.CENTER);
        
        return panel;
    }
}

// Feature 4: Payments and Invoices
class StudentPaymentsPanel extends JPanel {
    public StudentPaymentsPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("💳 Payments & Invoices", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(76, 175, 80));
        add(titleLabel, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("💰 Fee Summary", createFeeSummaryPanel());
        tabs.addTab("📄 Invoices", createInvoicesPanel());
        tabs.addTab("💳 Payment Gateway", createPaymentPanel());
        tabs.addTab("📊 Payment History", createPaymentHistoryPanel());
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private JPanel createFeeSummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Fee overview cards
        JPanel overviewPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        overviewPanel.setBorder(BorderFactory.createTitledBorder("Fee Overview"));
        
        overviewPanel.add(createFeeCard("Total Due", "$3,200", new Color(220, 53, 69)));
        overviewPanel.add(createFeeCard("Paid", "$2,500", new Color(40, 167, 69)));
        overviewPanel.add(createFeeCard("Pending", "$700", new Color(255, 193, 7)));
        overviewPanel.add(createFeeCard("Overdue", "$0", new Color(108, 117, 125)));
        
        panel.add(overviewPanel, BorderLayout.NORTH);
        
        // Detailed fee breakdown
        String[] columns = {"Fee Type", "Amount", "Due Date", "Status", "Action"};
        String[][] data = {
            {"Tuition Fee", "$2,500", "2024-01-15", "PAID", "View Receipt"},
            {"Lab Fee", "$150", "2024-01-20", "PENDING", "Pay Now"},
            {"Library Fee", "$50", "2024-01-25", "PENDING", "Pay Now"},
            {"Transport Fee", "$200", "2024-02-01", "PENDING", "Pay Now"},
            {"Activity Fee", "$100", "2024-02-05", "PENDING", "Pay Now"}
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Only action column is editable
            }
        };
        
        JTable feeTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(feeTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Fee Details"));
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Payment reminder
        JTextArea reminderArea = new JTextArea(3, 0);
        reminderArea.setEditable(false);
        reminderArea.setText("💡 Payment Reminders:\n" +
                            "• Lab Fee due in 5 days - Pay now to avoid late charges\n" +
                            "• Set up auto-pay to never miss a payment deadline");
        reminderArea.setBackground(new Color(255, 248, 220));
        
        JScrollPane reminderScrollPane = new JScrollPane(reminderArea);
        reminderScrollPane.setBorder(BorderFactory.createTitledBorder("Reminders"));
        panel.add(reminderScrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createFeeCard(String title, String amount, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        card.add(titleLabel, BorderLayout.NORTH);
        
        JLabel amountLabel = new JLabel(amount, JLabel.CENTER);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        amountLabel.setForeground(color);
        card.add(amountLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createInvoicesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Invoice filter
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter by Status:"));
        filterPanel.add(new JComboBox<>(new String[]{"All", "PAID", "PENDING", "OVERDUE"}));
        filterPanel.add(new JLabel("Year:"));
        filterPanel.add(new JComboBox<>(new String[]{"2024", "2023", "2022"}));
        filterPanel.add(new JButton("🔍 Filter"));
        
        panel.add(filterPanel, BorderLayout.NORTH);
        
        // Invoice list
        String[] columns = {"Invoice #", "Date", "Description", "Amount", "Due Date", "Status", "Action"};
        String[][] data = {
            {"INV-2024-001", "2024-01-01", "Tuition Fee - Spring 2024", "$2,500", "2024-01-15", "PAID", "Download"},
            {"INV-2024-002", "2024-01-10", "Lab Fee - Physics", "$150", "2024-01-20", "PENDING", "Pay Now"},
            {"INV-2024-003", "2024-01-10", "Library Fee", "$50", "2024-01-25", "PENDING", "Pay Now"},
            {"INV-2023-045", "2023-12-15", "Tuition Fee - Fall 2023", "$2,500", "2023-12-30", "PAID", "Download"},
            {"INV-2023-044", "2023-12-01", "Activity Fee", "$100", "2023-12-15", "PAID", "Download"}
        };
        
        JTable invoiceTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(invoiceTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Invoice History"));
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Invoice actions
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton downloadBtn = new JButton("📥 Download Selected");
        JButton printBtn = new JButton("🖨️ Print");
        JButton emailBtn = new JButton("📧 Email Invoice");
        
        actionPanel.add(downloadBtn);
        actionPanel.add(printBtn);
        actionPanel.add(emailBtn);
        panel.add(actionPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Payment form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Make Payment"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Select Fee:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JComboBox<>(new String[]{"Lab Fee - $150", "Library Fee - $50", "Transport Fee - $200", "Activity Fee - $100"}), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Payment Method:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(new JComboBox<>(new String[]{"Credit Card", "Debit Card", "Bank Transfer", "Digital Wallet"}), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField amountField = new JTextField("$150.00");
        amountField.setEditable(false);
        formPanel.add(amountField, gbc);
        
        // Payment gateway buttons
        JPanel gatewayPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        gatewayPanel.setBorder(BorderFactory.createTitledBorder("Payment Gateways"));
        
        JButton paypalBtn = new JButton("💳 PayPal");
        JButton stripeBtn = new JButton("💳 Stripe");
        JButton bankBtn = new JButton("🏦 Bank Transfer");
        JButton walletBtn = new JButton("📱 Digital Wallet");
        
        paypalBtn.setBackground(new Color(0, 48, 135));
        paypalBtn.setForeground(Color.WHITE);
        stripeBtn.setBackground(new Color(99, 91, 255));
        stripeBtn.setForeground(Color.WHITE);
        bankBtn.setBackground(new Color(40, 167, 69));
        bankBtn.setForeground(Color.WHITE);
        walletBtn.setBackground(new Color(255, 152, 0));
        walletBtn.setForeground(Color.WHITE);
        
        gatewayPanel.add(paypalBtn);
        gatewayPanel.add(stripeBtn);
        gatewayPanel.add(bankBtn);
        gatewayPanel.add(walletBtn);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(gatewayPanel, gbc);
        
        panel.add(formPanel, BorderLayout.NORTH);
        
        // Payment security info
        JTextArea securityArea = new JTextArea();
        securityArea.setEditable(false);
        securityArea.setText("🔒 SECURE PAYMENT INFORMATION\n" +
                            "============================\n\n" +
                            "✅ SSL Encrypted Connection\n" +
                            "✅ PCI DSS Compliant\n" +
                            "✅ 256-bit Encryption\n" +
                            "✅ Fraud Protection\n\n" +
                            "💡 Payment Tips:\n" +
                            "• Payments are processed instantly\n" +
                            "• Receipt will be emailed automatically\n" +
                            "• Contact support for payment issues\n" +
                            "• Set up auto-pay for convenience");
        securityArea.setBackground(new Color(240, 248, 255));
        
        JScrollPane securityScrollPane = new JScrollPane(securityArea);
        securityScrollPane.setBorder(BorderFactory.createTitledBorder("Security & Information"));
        panel.add(securityScrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createPaymentHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Payment history table
        String[] columns = {"Date", "Description", "Amount", "Method", "Transaction ID", "Status"};
        String[][] data = {
            {"2024-01-15", "Tuition Fee Payment", "$2,500", "Credit Card", "TXN-2024-001", "SUCCESS"},
            {"2023-12-20", "Activity Fee", "$100", "Bank Transfer", "TXN-2023-089", "SUCCESS"},
            {"2023-12-15", "Lab Fee", "$150", "PayPal", "TXN-2023-088", "SUCCESS"},
            {"2023-11-30", "Library Fee", "$50", "Debit Card", "TXN-2023-087", "SUCCESS"},
            {"2023-11-15", "Transport Fee", "$200", "Digital Wallet", "TXN-2023-086", "SUCCESS"}
        };
        
        JTable historyTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(historyTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Payment History"));
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Payment statistics
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Payment Statistics"));
        
        statsPanel.add(createStatsCard("Total Paid", "$5,500", new Color(40, 167, 69)));
        statsPanel.add(createStatsCard("This Year", "$2,650", new Color(33, 150, 243)));
        statsPanel.add(createStatsCard("Transactions", "12", new Color(255, 152, 0)));
        statsPanel.add(createStatsCard("Success Rate", "100%", new Color(76, 175, 80)));
        
        panel.add(statsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createStatsCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 11));
        card.add(titleLabel, BorderLayout.NORTH);
        
        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        valueLabel.setForeground(color);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
}


// Feature 6: Learning Resources Access
class StudentResourcesPanel extends JPanel {
    public StudentResourcesPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("📚 Learning Resources Hub", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(76, 175, 80));
        add(titleLabel, BorderLayout.NORTH);
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("📖 Digital Library", createDigitalLibraryPanel());
        tabs.addTab("🎥 Video Lectures", createVideoLecturesPanel());
        tabs.addTab("📅 Academic Calendar", createAcademicCalendarPanel());
        tabs.addTab("🏆 Achievements", createAchievementsPanel());
        
        add(tabs, BorderLayout.CENTER);
    }
    
    private JPanel createDigitalLibraryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("🔍 Search Resources:"));
        searchPanel.add(new JTextField(25));
        searchPanel.add(new JLabel("Type:"));
        searchPanel.add(new JComboBox<>(new String[]{"All", "Books", "Journals", "Articles", "Videos", "Presentations"}));
        searchPanel.add(new JButton("Search"));
        
        panel.add(searchPanel, BorderLayout.NORTH);
        
        // Resource categories
        JPanel categoriesPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        categoriesPanel.setBorder(BorderFactory.createTitledBorder("Resource Categories"));
        
        JButton booksBtn = new JButton("📚 E-Books");
        JButton journalsBtn = new JButton("📰 Journals");
        JButton articlesBtn = new JButton("📄 Research Articles");
        JButton videosBtn = new JButton("🎥 Educational Videos");
        JButton presentationsBtn = new JButton("📊 Presentations");
        JButton databasesBtn = new JButton("🗄️ Databases");
        
        booksBtn.setBackground(new Color(33, 150, 243));
        booksBtn.setForeground(Color.WHITE);
        journalsBtn.setBackground(new Color(76, 175, 80));
        journalsBtn.setForeground(Color.WHITE);
        articlesBtn.setBackground(new Color(255, 152, 0));
        articlesBtn.setForeground(Color.WHITE);
        videosBtn.setBackground(new Color(244, 67, 54));
        videosBtn.setForeground(Color.WHITE);
        presentationsBtn.setBackground(new Color(156, 39, 176));
        presentationsBtn.setForeground(Color.WHITE);
        databasesBtn.setBackground(new Color(96, 125, 139));
        databasesBtn.setForeground(Color.WHITE);
        
        categoriesPanel.add(booksBtn);
        categoriesPanel.add(journalsBtn);
        categoriesPanel.add(articlesBtn);
        categoriesPanel.add(videosBtn);
        categoriesPanel.add(presentationsBtn);
        categoriesPanel.add(databasesBtn);
        
        panel.add(categoriesPanel, BorderLayout.CENTER);
        
        // Recent resources
        JTextArea recentArea = new JTextArea();
        recentArea.setEditable(false);
        recentArea.setText("📚 RECENTLY ACCESSED RESOURCES\n" +
                          "==============================\n\n" +
                          "📖 Books:\n" +
                          "• \"Introduction to Algorithms\" - Cormen et al.\n" +
                          "• \"Calculus: Early Transcendentals\" - Stewart\n" +
                          "• \"Physics for Scientists\" - Serway\n\n" +
                          "📰 Journals:\n" +
                          "• IEEE Computer Society\n" +
                          "• Journal of Mathematical Analysis\n" +
                          "• Physical Review Letters\n\n" +
                          "🎥 Videos:\n" +
                          "• \"Data Structures Explained\" - CS101\n" +
                          "• \"Calculus Fundamentals\" - MATH201\n" +
                          "• \"Physics Lab Techniques\" - PHYS101");
        
        JScrollPane recentScrollPane = new JScrollPane(recentArea);
        recentScrollPane.setBorder(BorderFactory.createTitledBorder("Recent Activity"));
        recentScrollPane.setPreferredSize(new Dimension(0, 200));
        panel.add(recentScrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createVideoLecturesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Course filter
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Course:"));
        filterPanel.add(new JComboBox<>(new String[]{"All Courses", "CS101", "MATH201", "PHYS101", "ENG101", "HIST201"}));
        filterPanel.add(new JLabel("Topic:"));
        filterPanel.add(new JTextField(15));
        filterPanel.add(new JButton("🔍 Filter"));
        
        panel.add(filterPanel, BorderLayout.NORTH);
        
        // Video list
        String[] columns = {"Course", "Title", "Duration", "Date Added", "Views", "Action"};
        String[][] data = {
            {"CS101", "Introduction to Programming Concepts", "45:30", "2024-01-10", "156", "Watch"},
            {"CS101", "Data Types and Variables", "32:15", "2024-01-12", "142", "Watch"},
            {"MATH201", "Limits and Continuity", "38:45", "2024-01-08", "189", "Watch"},
            {"MATH201", "Derivatives and Applications", "42:20", "2024-01-15", "167", "Watch"},
            {"PHYS101", "Newton's Laws of Motion", "35:10", "2024-01-09", "134", "Watch"},
            {"PHYS101", "Energy and Work", "40:25", "2024-01-14", "145", "Watch"},
            {"ENG101", "Essay Writing Techniques", "28:50", "2024-01-11", "98", "Watch"},
            {"HIST201", "World War I Overview", "55:30", "2024-01-13", "112", "Watch"}
        };
        
        JTable videoTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(videoTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Available Video Lectures"));
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Video player placeholder
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBorder(BorderFactory.createTitledBorder("Video Player"));
        playerPanel.setPreferredSize(new Dimension(0, 150));
        
        JLabel playerLabel = new JLabel("🎥 Select a video to start watching", JLabel.CENTER);
        playerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        playerPanel.add(playerLabel, BorderLayout.CENTER);
        
        JPanel controlsPanel = new JPanel(new FlowLayout());
        JButton playBtn = new JButton("▶️ Play");
        JButton pauseBtn = new JButton("⏸️ Pause");
        JButton stopBtn = new JButton("⏹️ Stop");
        JButton fullscreenBtn = new JButton("🔍 Fullscreen");
        
        controlsPanel.add(playBtn);
        controlsPanel.add(pauseBtn);
        controlsPanel.add(stopBtn);
        controlsPanel.add(fullscreenBtn);
        playerPanel.add(controlsPanel, BorderLayout.SOUTH);
        
        panel.add(playerPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createAcademicCalendarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Calendar view (simplified)
        JPanel calendarPanel = new JPanel(new GridLayout(7, 7, 2, 2));
        calendarPanel.setBorder(BorderFactory.createTitledBorder("January 2024"));
        
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
            dayLabel.setOpaque(true);
            dayLabel.setBackground(new Color(230, 230, 230));
            calendarPanel.add(dayLabel);
        }
        
        for (int i = 1; i <= 42; i++) {
            JButton dayBtn = new JButton();
            if (i <= 31) {
                dayBtn.setText(String.valueOf(i));
                if (i == 15 || i == 20 || i == 25) {
                    dayBtn.setBackground(new Color(255, 193, 7));
                    dayBtn.setToolTipText("Important Event");
                }
            }
            dayBtn.setPreferredSize(new Dimension(40, 40));
            calendarPanel.add(dayBtn);
        }
        
        panel.add(calendarPanel, BorderLayout.CENTER);
        
        // Events list
        JTextArea eventsArea = new JTextArea();
        eventsArea.setEditable(false);
        eventsArea.setText("📅 UPCOMING EVENTS & DEADLINES\n" +
                          "==============================\n\n" +
                          "📚 Academic Events:\n" +
                          "• Jan 15: Assignment Due - CS101\n" +
                          "• Jan 20: Midterm Exam - MATH201\n" +
                          "• Jan 25: Project Presentation - PHYS101\n" +
                          "• Jan 30: Essay Submission - ENG101\n\n" +
                          "🎓 University Events:\n" +
                          "• Jan 18: Guest Lecture - AI in Modern World\n" +
                          "• Jan 22: Career Fair\n" +
                          "• Jan 28: Research Symposium\n\n" +
                          "📋 Administrative:\n" +
                          "• Jan 31: Course Registration Deadline\n" +
                          "• Feb 05: Fee Payment Due\n" +
                          "• Feb 10: Add/Drop Period Ends");
        
        JScrollPane eventsScrollPane = new JScrollPane(eventsArea);
        eventsScrollPane.setBorder(BorderFactory.createTitledBorder("Events & Deadlines"));
        eventsScrollPane.setPreferredSize(new Dimension(0, 200));
        panel.add(eventsScrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createAchievementsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Achievement summary
        JPanel summaryPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Achievement Summary"));
        
        summaryPanel.add(createAchievementCard("Total Points", "2,450", new Color(255, 193, 7)));
        summaryPanel.add(createAchievementCard("Badges Earned", "12", new Color(40, 167, 69)));
        summaryPanel.add(createAchievementCard("Level", "Advanced", new Color(33, 150, 243)));
        summaryPanel.add(createAchievementCard("Rank", "Top 10%", new Color(156, 39, 176)));
        
        panel.add(summaryPanel, BorderLayout.NORTH);
        
        // Achievements grid
        JPanel achievementsGrid = new JPanel(new GridLayout(3, 4, 10, 10));
        achievementsGrid.setBorder(BorderFactory.createTitledBorder("Earned Achievements"));
        
        String[] achievements = {
            "🏆 Dean's List", "📚 Bookworm", "⏰ Perfect Attendance", "🎯 High Achiever",
            "💡 Problem Solver", "🤝 Team Player", "📈 Consistent Performer", "🔬 Lab Expert",
            "✍️ Writing Excellence", "🧮 Math Wizard", "🔬 Science Star", "📖 Literature Lover"
        };
        
        Color[] colors = {
            new Color(255, 193, 7), new Color(76, 175, 80), new Color(33, 150, 243), new Color(244, 67, 54),
            new Color(156, 39, 176), new Color(255, 152, 0), new Color(96, 125, 139), new Color(32, 201, 151),
            new Color(220, 53, 69), new Color(23, 162, 184), new Color(111, 66, 193), new Color(108, 117, 125)
        };
        
        for (int i = 0; i < achievements.length; i++) {
            JButton achievementBtn = new JButton(achievements[i]);
            achievementBtn.setBackground(colors[i]);
            achievementBtn.setForeground(Color.WHITE);
            achievementBtn.setFont(new Font("Arial", Font.BOLD, 10));
            achievementBtn.setFocusPainted(false);
            achievementBtn.setPreferredSize(new Dimension(120, 60));
            achievementsGrid.add(achievementBtn);
        }
        
        panel.add(achievementsGrid, BorderLayout.CENTER);
        
        // Progress towards next achievement
        JTextArea progressArea = new JTextArea();
        progressArea.setEditable(false);
        progressArea.setText("🎯 PROGRESS TOWARDS NEXT ACHIEVEMENTS\n" +
                            "====================================\n\n" +
                            "🏅 Scholar Badge (85% complete)\n" +
                            "• Maintain GPA above 3.8 for 2 semesters ✅\n" +
                            "• Complete 5 research projects (4/5) 🔄\n" +
                            "• Attend 3 academic conferences (2/3) 🔄\n\n" +
                            "🌟 Leadership Badge (60% complete)\n" +
                            "• Lead a study group ✅\n" +
                            "• Organize campus event (0/1) ⏳\n" +
                            "• Mentor junior students (2/3) 🔄\n\n" +
                            "💎 Excellence Badge (40% complete)\n" +
                            "• Achieve perfect attendance ✅\n" +
                            "• Score 95%+ in 3 courses (1/3) 🔄\n" +
                            "• Complete honors project (0/1) ⏳");
        
        JScrollPane progressScrollPane = new JScrollPane(progressArea);
        progressScrollPane.setBorder(BorderFactory.createTitledBorder("Progress Tracking"));
        progressScrollPane.setPreferredSize(new Dimension(0, 150));
        panel.add(progressScrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createAchievementCard(String title, String value, Color color) {
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
}

// Placeholder classes for instructor panels
class InstructorDashboardPanel extends JPanel {
    public InstructorDashboardPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Instructor Dashboard - Coming Soon", JLabel.CENTER), BorderLayout.CENTER);
    }
}

class InstructorCoursesPanel extends JPanel {
    public InstructorCoursesPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Instructor Courses - Coming Soon", JLabel.CENTER), BorderLayout.CENTER);
    }
}

class InstructorStudentsPanel extends JPanel {
    public InstructorStudentsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Instructor Students - Coming Soon", JLabel.CENTER), BorderLayout.CENTER);
    }
}

class InstructorAttendancePanel extends JPanel {
    public InstructorAttendancePanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Instructor Attendance - Coming Soon", JLabel.CENTER), BorderLayout.CENTER);
    }
}

class InstructorGradesPanel extends JPanel {
    public InstructorGradesPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Instructor Grades - Coming Soon", JLabel.CENTER), BorderLayout.CENTER);
    }
}
