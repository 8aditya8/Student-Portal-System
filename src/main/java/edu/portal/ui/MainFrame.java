// src/main/java/edu/portal/ui/MainFrame.java
package edu.portal.ui;

import edu.portal.model.User;
import edu.portal.service.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainFrame extends JFrame {
    private User currentUser;
    private JTabbedPane tabbedPane;

    public MainFrame(User user) {
        this.currentUser = user;
        initComponents();
        setupLayout();
        setTitle("Portal - Welcome " + user.getUsername() + " (" + user.getRole() + ")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        
        // Add tabs based on user role
        if ("ADMIN".equals(currentUser.getRole())) {
            // Admin Panel - Main Features
            tabbedPane.addTab("📊 Dashboard", new AdminDashboardPanel());
            tabbedPane.addTab("📊 Statistics", new AdminStatisticsPanel());
            tabbedPane.addTab("👥 Activity Tracker", new AdminActivityTrackerPanel());
            tabbedPane.addTab("📈 Analytics", new AdminAnalyticsPanel());
            tabbedPane.addTab("🔒 Security", new AdminSecurityPanel());
            tabbedPane.addTab("👥 User Management", new AdminUserManagementPanel());
            tabbedPane.addTab("📚 Course Management", new AdminCourseManagementPanel());
            tabbedPane.addTab("📋 Attendance & Grades", new AdminAttendanceGradesPanel());
            tabbedPane.addTab("📅 Scheduling", new AdminSchedulingPanel());
            tabbedPane.addTab("💰 Fee Management", new AdminFeeManagementPanel());
            tabbedPane.addTab("🔍 Audit & Notifications", new AdminAuditPanel());
        } else if ("INSTRUCTOR".equals(currentUser.getRole())) {
            tabbedPane.addTab("📚 My Courses", new InstructorCoursesPanel());
            tabbedPane.addTab("👥 Students", new InstructorStudentsPanel());
            tabbedPane.addTab("📋 Attendance", new InstructorAttendancePanel());
            tabbedPane.addTab("📝 Grades", new InstructorGradesPanel());
        } else {
            // Student Panel - 5 Key Features
            tabbedPane.addTab("🎓 Dashboard", new StudentDashboardPanel());
            tabbedPane.addTab("📝 Enrollment", new StudentEnrollmentPanel());
            tabbedPane.addTab("📊 Attendance & Grades", new StudentAttendanceGradesPanel());
            tabbedPane.addTab("💳 Payments", new StudentPaymentsPanel());
            tabbedPane.addTab("💬 Communication", new StudentCommunicationPanel());
        }
        
        tabbedPane.addTab("👤 Profile", new ProfilePanel());
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> logout());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Status bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        statusBar.add(new JLabel("Logged in as: " + currentUser.getUsername() + " | Role: " + currentUser.getRole()));
        add(statusBar, BorderLayout.SOUTH);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 

            
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            SwingUtilities.invokeLater(() -> {
                new LoginFrame().setVisible(true);
            });
        }

    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this, 
            "Portal Management System\nVersion 2.0\n\nEnhanced with Advanced Dashboards", 
            "About", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Simplified Admin Dashboard - Overview only
    private class AdminDashboardPanel extends JPanel {
        private UserService userService;
        private JLabel totalUsersLabel;
        private JLabel adminCountLabel;
        private JLabel studentCountLabel;
        private JLabel instructorCountLabel;
        private Timer refreshTimer;

        public AdminDashboardPanel() {
            userService = UserService.getInstance();
            initComponents();
            setupLayout();
            loadDashboardData();
            startAutoRefresh();
        }

        private void initComponents() {
            totalUsersLabel = new JLabel("0");
            adminCountLabel = new JLabel("0");
            studentCountLabel = new JLabel("0");
            instructorCountLabel = new JLabel("0");
        }

        private void setupLayout() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setBackground(new Color(248, 249, 250));

            // Header Panel
            JPanel headerPanel = createAnimatedHeader();
            add(headerPanel, BorderLayout.NORTH);

            // Main Dashboard Content - Overview only
            JPanel overviewPanel = createDashboardOverviewPanel();
            add(overviewPanel, BorderLayout.CENTER);

            // Status Bar
            JPanel statusPanel = createStatusBar();
            add(statusPanel, BorderLayout.SOUTH);
        }

        private JPanel createAnimatedHeader() {
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(new Color(41, 128, 185));
            headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
            
            JLabel titleLabel = new JLabel("🚀 Admin Dashboard Overview");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
            titleLabel.setForeground(Color.WHITE);
            headerPanel.add(titleLabel, BorderLayout.WEST);
            
            JLabel timeLabel = new JLabel();
            timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            timeLabel.setForeground(new Color(236, 240, 241));
            
            Timer clockTimer = new Timer(1000, e -> {
                timeLabel.setText(java.time.LocalDateTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy - HH:mm:ss")));
            });
            clockTimer.start();
            timeLabel.setText(java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy - HH:mm:ss")));
            
            headerPanel.add(timeLabel, BorderLayout.EAST);
            
            return headerPanel;
        }

        private JPanel createDashboardOverviewPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            
            // Statistics Overview
            JPanel statsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
            statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            statsPanel.add(createStatCard("Total Users", totalUsersLabel, new Color(70, 130, 180), "👥"));
            statsPanel.add(createStatCard("Administrators", adminCountLabel, new Color(220, 20, 60), "👑"));
            statsPanel.add(createStatCard("Students", studentCountLabel, new Color(34, 139, 34), "🎓"));
            statsPanel.add(createStatCard("Instructors", instructorCountLabel, new Color(255, 140, 0), "👨‍🏫"));

            panel.add(statsPanel, BorderLayout.NORTH);
            
            // System Status
            JTextArea statusArea = new JTextArea();
            statusArea.setEditable(false);
            statusArea.setText("SYSTEM STATUS OVERVIEW\n" +
                              "======================\n\n" +
                              "🟢 System Status: Online\n" +
                              "📊 Performance: Optimal\n" +
                              "🔒 Security: Active\n" +
                              "💾 Database: Connected\n" +
                              "⚡ Response Time: 245ms\n" +
                              "📈 Uptime: 99.8%\n\n" +
                              "Recent Activities:\n" +
                              "• Admin login successful\n" +
                              "• User statistics updated\n" +
                              "• System monitoring active\n" +
                              "• Security scan passed");
            statusArea.setBackground(new Color(248, 249, 250));
            
            JScrollPane statusScrollPane = new JScrollPane(statusArea);
            statusScrollPane.setBorder(BorderFactory.createTitledBorder("System Status"));
            panel.add(statusScrollPane, BorderLayout.CENTER);
            
            return panel;
        }

        private JPanel createStatCard(String title, JLabel valueLabel, Color color, String icon) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
            card.setBackground(Color.WHITE);

            JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            headerPanel.setBackground(Color.WHITE);
            
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            headerPanel.add(iconLabel);
            
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            titleLabel.setForeground(color);
            headerPanel.add(titleLabel);
            
            card.add(headerPanel, BorderLayout.NORTH);

            JPanel valuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            valuePanel.setBackground(Color.WHITE);
            
            valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
            valueLabel.setForeground(color);
            valuePanel.add(valueLabel);
            
            card.add(valuePanel, BorderLayout.CENTER);

            return card;
        }

        private JPanel createStatusBar() {
            JPanel statusPanel = new JPanel(new BorderLayout());
            statusPanel.setBackground(new Color(52, 58, 64));
            statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            JLabel statusLabel = new JLabel("🟢 System Online");
            statusLabel.setForeground(Color.WHITE);
            statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
            statusPanel.add(statusLabel, BorderLayout.WEST);

            JLabel versionLabel = new JLabel("v2.0.0");
            versionLabel.setForeground(new Color(108, 117, 125));
            versionLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            statusPanel.add(versionLabel, BorderLayout.EAST);

            return statusPanel;
        }

        private void loadDashboardData() {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                private int totalUsers, adminCount, studentCount, instructorCount;

                @Override
                protected Void doInBackground() throws Exception {
                    List<User> users = userService.getAllUsers();
                    totalUsers = users.size();
                    adminCount = (int) users.stream().filter(u -> "ADMIN".equals(u.getRole())).count();
                    studentCount = (int) users.stream().filter(u -> "STUDENT".equals(u.getRole())).count();
                    instructorCount = (int) users.stream().filter(u -> "INSTRUCTOR".equals(u.getRole())).count();
                    return null;
                }

                @Override
                protected void done() {
                    totalUsersLabel.setText(String.valueOf(totalUsers));
                    adminCountLabel.setText(String.valueOf(adminCount));
                    studentCountLabel.setText(String.valueOf(studentCount));
                    instructorCountLabel.setText(String.valueOf(instructorCount));
                    repaint();
                }
            };
            worker.execute();
        }

        private void startAutoRefresh() {
            refreshTimer = new Timer(30000, e -> loadDashboardData());
            refreshTimer.start();
        }
    }

    // New Admin Panel Classes for Top-Level Tabs
    private class AdminStatisticsPanel extends JPanel {
        private UserService userService;
        private JLabel totalUsersLabel;
        private JLabel adminCountLabel;
        private JLabel studentCountLabel;
        private JLabel instructorCountLabel;

        public AdminStatisticsPanel() {
            userService = UserService.getInstance();
            initComponents();
            setupLayout();
            loadData();
        }

        private void initComponents() {
            totalUsersLabel = new JLabel("0");
            adminCountLabel = new JLabel("0");
            studentCountLabel = new JLabel("0");
            instructorCountLabel = new JLabel("0");
        }

        private void setupLayout() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            setBackground(new Color(248, 249, 250));

            JLabel titleLabel = new JLabel("📊 Statistics Overview", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(new Color(41, 128, 185));
            add(titleLabel, BorderLayout.NORTH);

            JPanel statsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
            statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            statsPanel.add(createStatCard("Total Users", totalUsersLabel, new Color(70, 130, 180), "👥"));
            statsPanel.add(createStatCard("Administrators", adminCountLabel, new Color(220, 20, 60), "👑"));
            statsPanel.add(createStatCard("Students", studentCountLabel, new Color(34, 139, 34), "🎓"));
            statsPanel.add(createStatCard("Instructors", instructorCountLabel, new Color(255, 140, 0), "👨‍🏫"));

            add(statsPanel, BorderLayout.CENTER);
        }

        private JPanel createStatCard(String title, JLabel valueLabel, Color color, String icon) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
            card.setBackground(Color.WHITE);

            JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            headerPanel.setBackground(Color.WHITE);
            
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            headerPanel.add(iconLabel);
            
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
            titleLabel.setForeground(color);
            headerPanel.add(titleLabel);
            
            card.add(headerPanel, BorderLayout.NORTH);

            JPanel valuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            valuePanel.setBackground(Color.WHITE);
            
            valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
            valueLabel.setForeground(color);
            valuePanel.add(valueLabel);
            
            card.add(valuePanel, BorderLayout.CENTER);

            return card;
        }

        private void loadData() {
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                private int totalUsers, adminCount, studentCount, instructorCount;

                @Override
                protected Void doInBackground() throws Exception {
                    List<User> users = userService.getAllUsers();
                    totalUsers = users.size();
                    adminCount = (int) users.stream().filter(u -> "ADMIN".equals(u.getRole())).count();
                    studentCount = (int) users.stream().filter(u -> "STUDENT".equals(u.getRole())).count();
                    instructorCount = (int) users.stream().filter(u -> "INSTRUCTOR".equals(u.getRole())).count();
                    return null;
                }

                @Override
                protected void done() {
                    totalUsersLabel.setText(String.valueOf(totalUsers));
                    adminCountLabel.setText(String.valueOf(adminCount));
                    studentCountLabel.setText(String.valueOf(studentCount));
                    instructorCountLabel.setText(String.valueOf(instructorCount));
                    repaint();
                }
            };
            worker.execute();
        }
    }

    private class AdminActivityTrackerPanel extends JPanel {
        public AdminActivityTrackerPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel titleLabel = new JLabel("👥 Activity Tracker", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(new Color(41, 128, 185));
            add(titleLabel, BorderLayout.NORTH);

            DefaultListModel<String> activitiesModel = new DefaultListModel<>();
            activitiesModel.addElement("🔐 Admin login at " + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
            activitiesModel.addElement("📊 Dashboard accessed");
            activitiesModel.addElement("🔄 System auto-refresh enabled");
            activitiesModel.addElement("👥 User statistics updated");
            activitiesModel.addElement("📈 Analytics panel viewed");
            activitiesModel.addElement("🔒 Security status checked");

            JList<String> activitiesList = new JList<>(activitiesModel);
            JScrollPane scrollPane = new JScrollPane(activitiesList);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Recent Activities"));
            
            add(scrollPane, BorderLayout.CENTER);

            JPanel controlsPanel = new JPanel(new FlowLayout());
            JButton refreshBtn = new JButton("🔄 Refresh");
            refreshBtn.addActionListener(e -> {
                activitiesModel.addElement("🔄 Manual refresh at " + 
                    java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
            });
            controlsPanel.add(refreshBtn);
            
            add(controlsPanel, BorderLayout.SOUTH);
        }
    }

    private class AdminAnalyticsPanel extends JPanel {
        public AdminAnalyticsPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel titleLabel = new JLabel("📈 Analytics Dashboard", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(new Color(41, 128, 185));
            add(titleLabel, BorderLayout.NORTH);

            JPanel analyticsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
            analyticsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            analyticsPanel.add(createAnalyticsCard("Login Analytics", 
                "📊 Login Statistics:\n\n• Today's Logins: 15\n• This Week: 89\n• Success Rate: 96.5%"));
            analyticsPanel.add(createAnalyticsCard("User Growth", 
                "📈 Growth Metrics:\n\n• New Users Today: 3\n• This Week: 12\n• Growth Rate: +8.2%"));
            analyticsPanel.add(createAnalyticsCard("Performance", 
                "⚡ Performance Data:\n\n• Response Time: 245ms\n• Uptime: 99.8%\n• Error Rate: 0.2%"));
            analyticsPanel.add(createAnalyticsCard("Resources", 
                "💻 Resource Usage:\n\n• Memory: 2.1GB / 8GB\n• CPU: 45% average\n• Connections: 45"));

            add(analyticsPanel, BorderLayout.CENTER);
        }

        private JPanel createAnalyticsCard(String title, String content) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createTitledBorder(title));
            card.setBackground(Color.WHITE);
            
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText(content);
            textArea.setBackground(Color.WHITE);
            
            card.add(new JScrollPane(textArea), BorderLayout.CENTER);
            return card;
        }
    }

    private class AdminSecurityPanel extends JPanel {
        public AdminSecurityPanel() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel titleLabel = new JLabel("🔒 Security Dashboard", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(new Color(41, 128, 185));
            add(titleLabel, BorderLayout.NORTH);

            JPanel statusPanel = new JPanel(new GridLayout(2, 2, 15, 15));
            statusPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            statusPanel.add(createSecurityCard("Security Score", "🛡️ 95/100", new Color(40, 167, 69)));
            statusPanel.add(createSecurityCard("Failed Logins", "⚠️ 3", new Color(255, 193, 7)));
            statusPanel.add(createSecurityCard("Active Sessions", "👥 1", new Color(23, 162, 184)));
            statusPanel.add(createSecurityCard("Security Alerts", "🚨 0", new Color(40, 167, 69)));
            
            add(statusPanel, BorderLayout.NORTH);
            
            JTextArea securityLog = new JTextArea(10, 50);
            securityLog.setEditable(false);
            securityLog.setText("🔐 Security Events:\n\n" +
                              java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")) + " - Admin login successful\n" +
                              "09:15:23 - Password policy updated\n" +
                              "08:45:12 - Failed login attempt blocked\n" +
                              "08:30:45 - User session expired\n" +
                              "08:15:30 - Security scan completed - No threats found");
            
            JScrollPane logScrollPane = new JScrollPane(securityLog);
            logScrollPane.setBorder(BorderFactory.createTitledBorder("Security Log"));
            add(logScrollPane, BorderLayout.CENTER);
        }

        private JPanel createSecurityCard(String title, String value, Color color) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createTitledBorder(title));
            card.setBackground(Color.WHITE);
            
            JLabel valueLabel = new JLabel(value, JLabel.CENTER);
            valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
            valueLabel.setForeground(color);
            card.add(valueLabel, BorderLayout.CENTER);
            
            return card;
        }
    }

    // Enhanced Student Dashboard with 6 Unique Features
    private class StudentDashboardPanel extends JPanel {
        private JLabel totalCoursesLabel;
        private JLabel completedCoursesLabel;
        private JLabel currentGPALabel;
        private JLabel attendanceLabel;
        private JLabel upcomingAssignmentsLabel;
        private JLabel studyHoursLabel;

        public StudentDashboardPanel() {
            initStudentComponents();
            setupStudentLayout();
        }

        private void initStudentComponents() {
            totalCoursesLabel = new JLabel("5");
            completedCoursesLabel = new JLabel("3");
            currentGPALabel = new JLabel("3.75");
            attendanceLabel = new JLabel("92%");
            upcomingAssignmentsLabel = new JLabel("4");
            studyHoursLabel = new JLabel("28h");
        }

        private void setupStudentLayout() {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            setBackground(new Color(248, 249, 250));

            // Student Header Panel
            JPanel headerPanel = createStudentHeader();
            add(headerPanel, BorderLayout.NORTH);

            // Main Content with Tabbed Interface
            JTabbedPane studentTabs = new JTabbedPane();
            studentTabs.setFont(new Font("Arial", Font.BOLD, 12));
            
            // Feature 1: Academic Overview Dashboard
            studentTabs.addTab("📊 Academic Overview", createAcademicOverviewPanel());
            
            // Feature 2: Course Progress Tracker
            studentTabs.addTab("📈 Progress Tracker", createProgressTrackerPanel());
            
            // Feature 3: Assignment Manager
            studentTabs.addTab("📝 Assignments", createAssignmentManagerPanel());
            
            // Feature 4: Grade Analytics
            studentTabs.addTab("🎯 Grade Analytics", createGradeAnalyticsPanel());
            
            // Feature 5: Study Planner
            studentTabs.addTab("📅 Study Planner", createStudyPlannerPanel());
            
            // Feature 6: Student Resources Hub
            studentTabs.addTab("📚 Resources", createResourcesHubPanel());

            add(studentTabs, BorderLayout.CENTER);

            // Student Status Bar
            JPanel statusPanel = createStudentStatusBar();
            add(statusPanel, BorderLayout.SOUTH);
        }

        private JPanel createStudentHeader() {
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(new Color(76, 175, 80));
            headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
            
            JLabel titleLabel = new JLabel("🎓 Student Learning Dashboard");
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
            titleLabel.setForeground(Color.WHITE);
            headerPanel.add(titleLabel, BorderLayout.WEST);
            
            JLabel welcomeLabel = new JLabel("Welcome back, " + currentUser.getUsername() + "!");
            welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            welcomeLabel.setForeground(new Color(236, 240, 241));
            headerPanel.add(welcomeLabel, BorderLayout.EAST);
            
            return headerPanel;
        }

        // Feature 1: Academic Overview Dashboard
        private JPanel createAcademicOverviewPanel() {
            JPanel panel = new JPanel(new GridLayout(2, 3, 15, 15));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            panel.add(createStudentStatCard("Total Courses", totalCoursesLabel, new Color(33, 150, 243), "📚"));
            panel.add(createStudentStatCard("Completed", completedCoursesLabel, new Color(76, 175, 80), "✅"));
            panel.add(createStudentStatCard("Current GPA", currentGPALabel, new Color(255, 152, 0), "🎯"));
            panel.add(createStudentStatCard("Attendance", attendanceLabel, new Color(156, 39, 176), "📊"));
            panel.add(createStudentStatCard("Pending Tasks", upcomingAssignmentsLabel, new Color(244, 67, 54), "📝"));
            panel.add(createStudentStatCard("Study Hours", studyHoursLabel, new Color(96, 125, 139), "⏰"));

            return panel;
        }

        private JPanel createStudentStatCard(String title, JLabel valueLabel, Color color, String icon) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
            card.setBackground(Color.WHITE);

            JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            headerPanel.setBackground(Color.WHITE);
            
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            headerPanel.add(iconLabel);
            
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
            titleLabel.setForeground(color);
            headerPanel.add(titleLabel);
            
            card.add(headerPanel, BorderLayout.NORTH);

            JPanel valuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            valuePanel.setBackground(Color.WHITE);
            
            valueLabel.setFont(new Font("Arial", Font.BOLD, 28));
            valueLabel.setForeground(color);
            valuePanel.add(valueLabel);
            
            card.add(valuePanel, BorderLayout.CENTER);

            return card;
        }

        // Feature 2: Course Progress Tracker
        private JPanel createProgressTrackerPanel() {
            JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            String[] courses = {"Mathematics", "Physics", "Chemistry", "Literature", "History"};
            int[] progress = {85, 92, 78, 88, 95};
            Color[] colors = {
                new Color(33, 150, 243), new Color(76, 175, 80), new Color(255, 152, 0),
                new Color(156, 39, 176), new Color(244, 67, 54)
            };
            
            for (int i = 0; i < courses.length; i++) {
                panel.add(createCourseProgressCard(courses[i], progress[i], colors[i]));
            }

            return panel;
        }

        private JPanel createCourseProgressCard(String courseName, int progress, Color color) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 224, 224)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
            card.setBackground(Color.WHITE);
            
            JLabel nameLabel = new JLabel(courseName);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            card.add(nameLabel, BorderLayout.WEST);
            
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue(progress);
            progressBar.setStringPainted(true);
            progressBar.setString(progress + "%");
            progressBar.setForeground(color);
            progressBar.setPreferredSize(new Dimension(200, 25));
            card.add(progressBar, BorderLayout.CENTER);
            
            String grade = progress >= 90 ? "A" : progress >= 80 ? "B" : progress >= 70 ? "C" : "D";
            JLabel gradeLabel = new JLabel(grade);
            gradeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            gradeLabel.setForeground(color);
            card.add(gradeLabel, BorderLayout.EAST);
            
            return card;
        }

        // Feature 3: Assignment Manager
        private JPanel createAssignmentManagerPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            DefaultListModel<String> assignmentsModel = new DefaultListModel<>();
            assignmentsModel.addElement("📝 Math Assignment - Due: Tomorrow");
            assignmentsModel.addElement("📊 Physics Lab Report - Due: Friday");
            assignmentsModel.addElement("📚 Literature Essay - Due: Next Week");
            assignmentsModel.addElement("🧪 Chemistry Project - Due: Dec 15");

            JList<String> assignmentsList = new JList<>(assignmentsModel);
            JScrollPane scrollPane = new JScrollPane(assignmentsList);
            scrollPane.setBorder(BorderFactory.createTitledBorder("Upcoming Assignments"));
            
            panel.add(scrollPane, BorderLayout.CENTER);

            JPanel controlsPanel = new JPanel(new FlowLayout());
            JButton markCompleteBtn = new JButton("✅ Mark Complete");
            markCompleteBtn.addActionListener(e -> {
                int selectedIndex = assignmentsList.getSelectedIndex();
                if (selectedIndex != -1) {
                    assignmentsModel.removeElementAt(selectedIndex);
                    upcomingAssignmentsLabel.setText(String.valueOf(assignmentsModel.getSize()));
                    JOptionPane.showMessageDialog(this, "Assignment marked as complete!");
                }
            });
            controlsPanel.add(markCompleteBtn);
            
            panel.add(controlsPanel, BorderLayout.SOUTH);

            return panel;
        }

        // Feature 4: Grade Analytics
        private JPanel createGradeAnalyticsPanel() {
            JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            panel.add(createStudentAnalyticsCard("GPA Trend", 
                "📈 GPA Analysis:\n\n• Current GPA: 3.75\n• Previous Semester: 3.68\n• Improvement: +0.07\n• Class Rank: 15/120"));
            panel.add(createStudentAnalyticsCard("Subject Performance", 
                "📊 Subject Breakdown:\n\n• Mathematics: A- (92%)\n• Physics: A (95%)\n• Chemistry: B+ (88%)\n• Literature: A- (91%)"));
            panel.add(createStudentAnalyticsCard("Attendance Analysis", 
                "📅 Attendance Report:\n\n• Overall: 92%\n• Mathematics: 95%\n• Physics: 90%\n• Chemistry: 88%"));
            panel.add(createStudentAnalyticsCard("Study Habits", 
                "📚 Study Analytics:\n\n• Weekly Hours: 28h\n• Most Productive: 7-9 PM\n• Favorite Subject: Physics\n• Study Streak: 12 days"));

            return panel;
        }

        private JPanel createStudentAnalyticsCard(String title, String content) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createTitledBorder(title));
            card.setBackground(Color.WHITE);
            
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setText(content);
            textArea.setBackground(Color.WHITE);
            
            card.add(new JScrollPane(textArea), BorderLayout.CENTER);
            return card;
        }

        // Feature 5: Study Planner
        private JPanel createStudyPlannerPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JPanel calendarPanel = new JPanel(new GridLayout(6, 7, 2, 2));
            calendarPanel.setBorder(BorderFactory.createTitledBorder("Study Calendar"));
            
            String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
            for (String day : days) {
                JLabel dayLabel = new JLabel(day, JLabel.CENTER);
                dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
                dayLabel.setOpaque(true);
                dayLabel.setBackground(new Color(230, 230, 230));
                calendarPanel.add(dayLabel);
            }
            
            for (int i = 1; i <= 35; i++) {
                JButton dayBtn = new JButton(String.valueOf(i <= 30 ? i : ""));
                dayBtn.setPreferredSize(new Dimension(40, 40));
                if (i <= 30 && i % 3 == 0) {
                    dayBtn.setBackground(new Color(76, 175, 80));
                    dayBtn.setForeground(Color.WHITE);
                }
                calendarPanel.add(dayBtn);
            }
            
            panel.add(calendarPanel, BorderLayout.CENTER);

            return panel;
        }

        // Feature 6: Student Resources Hub
        private JPanel createResourcesHubPanel() {
            JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            String[] resources = {
                "📚 Digital Library", "🎥 Video Lectures", "💬 Discussion Forums",
                "📊 Practice Tests", "🏆 Achievements", "📞 Academic Support"
            };

            Color[] colors = {
                new Color(33, 150, 243), new Color(244, 67, 54), new Color(76, 175, 80),
                new Color(255, 152, 0), new Color(156, 39, 176), new Color(96, 125, 139)
            };

            for (int i = 0; i < resources.length; i++) {
                JButton button = new JButton(resources[i]);
                button.setBackground(colors[i]);
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Arial", Font.BOLD, 12));
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createRaisedBevelBorder());
                button.setPreferredSize(new Dimension(200, 100));
                
                final String resource = resources[i];
                button.addActionListener(e -> {
                    JOptionPane.showMessageDialog(this, "Opening: " + resource, "Resource Access", JOptionPane.INFORMATION_MESSAGE);
                });
                
                panel.add(button);
            }

            return panel;
        }

        private JPanel createStudentStatusBar() {
            JPanel statusPanel = new JPanel(new BorderLayout());
            statusPanel.setBackground(new Color(76, 175, 80));
            statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            JLabel statusLabel = new JLabel("🎓 Student Portal Active");
            statusLabel.setForeground(Color.WHITE);
            statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
            statusPanel.add(statusLabel, BorderLayout.WEST);

            JLabel semesterLabel = new JLabel("📅 Fall 2024");
            semesterLabel.setForeground(Color.WHITE);
            semesterLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            statusPanel.add(semesterLabel, BorderLayout.EAST);

            return statusPanel;
        }
    }

    private class ProfilePanel extends JPanel {
        public ProfilePanel() {
            setLayout(new BorderLayout());
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            
            gbc.gridx = 0; gbc.gridy = 0;
            panel.add(new JLabel("Username:"), gbc);
            gbc.gridx = 1;
            panel.add(new JLabel(currentUser.getUsername()), gbc);
            
            gbc.gridx = 0; gbc.gridy = 1;
            panel.add(new JLabel("Role:"), gbc);
            gbc.gridx = 1;
            panel.add(new JLabel(currentUser.getRole()), gbc);
            
            add(panel, BorderLayout.CENTER);
        }
    }
}