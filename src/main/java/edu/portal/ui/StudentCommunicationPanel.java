// src/main/java/edu/portal/ui/StudentCommunicationPanel.java
package edu.portal.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentCommunicationPanel extends JPanel {
    private JTable messagesTable;
    private DefaultTableModel messagesModel;

    private JTable announcementsTable;
    private DefaultTableModel announcementsModel;
    private JTextArea messageArea;
    private JTextField subjectField;

    public StudentCommunicationPanel() {
        initComponents();
        setupLayout();
        loadSampleData();
    }

    private void initComponents() {
        // Messages table
        String[] messageColumns = {"From", "Subject", "Date", "Status", "Priority"};
        messagesModel = new DefaultTableModel(messageColumns, 0);
        messagesTable = new JTable(messagesModel);
        messagesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Announcements table
        String[] announcementColumns = {"Title", "From", "Date", "Category", "Status"};
        announcementsModel = new DefaultTableModel(announcementColumns, 0);
        announcementsTable = new JTable(announcementsModel);
        announcementsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Message composition components
        subjectField = new JTextField(30);
        messageArea = new JTextArea(8, 40);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel titleLabel = new JLabel("💬 Communication Center", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        add(titleLabel, BorderLayout.NORTH);

        // Main content with tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Messages tab
        JPanel messagesPanel = createMessagesPanel();
        tabbedPane.addTab("📧 Messages", messagesPanel);

        // Compose tab
        JPanel composePanel = createComposePanel();
        tabbedPane.addTab("✏️ Compose", composePanel);

        // Announcements tab
        JPanel announcementsPanel = createAnnouncementsPanel();
        tabbedPane.addTab("📢 Announcements", announcementsPanel);

        // Discussion Forums tab
        JPanel forumsPanel = createForumsPanel();
        tabbedPane.addTab("💭 Discussion Forums", forumsPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Quick stats panel
        JPanel statsPanel = createStatsPanel();
        add(statsPanel, BorderLayout.SOUTH);
    }

    private JPanel createMessagesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Filter panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter:"));
        JComboBox<String> filterCombo = new JComboBox<>(new String[]{"All Messages", "Unread", "From Instructors", "From Admin", "High Priority"});
        filterPanel.add(filterCombo);
        
        JButton refreshBtn = new JButton("🔄 Refresh");
        JButton markReadBtn = new JButton("✅ Mark as Read");
        
        refreshBtn.addActionListener(e -> refreshMessages());
        markReadBtn.addActionListener(e -> markAsRead());
        
        filterPanel.add(refreshBtn);
        filterPanel.add(markReadBtn);
        panel.add(filterPanel, BorderLayout.NORTH);

        // Messages table
        JScrollPane scrollPane = new JScrollPane(messagesTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Inbox"));
        scrollPane.setPreferredSize(new Dimension(700, 300));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Message actions
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton readBtn = new JButton("📖 Read Message");
        JButton replyBtn = new JButton("↩️ Reply");
        JButton forwardBtn = new JButton("➡️ Forward");
        JButton deleteBtn = new JButton("🗑️ Delete");
        JButton archiveBtn = new JButton("📁 Archive");

        readBtn.addActionListener(e -> readMessage());
        replyBtn.addActionListener(e -> replyToMessage());
        forwardBtn.addActionListener(e -> forwardMessage());
        deleteBtn.addActionListener(e -> deleteMessage());
        archiveBtn.addActionListener(e -> archiveMessage());

        actionPanel.add(readBtn);
        actionPanel.add(replyBtn);
        actionPanel.add(forwardBtn);
        actionPanel.add(deleteBtn);
        actionPanel.add(archiveBtn);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createComposePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Compose form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Compose New Message"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // To field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("To:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> toCombo = new JComboBox<>(new String[]{"Select Recipient", "Dr. Smith (MATH201)", "Prof. Johnson (PHYS201)", "Dr. Williams (CHEM301)", "Academic Advisor", "Finance Office", "IT Support"});
        toCombo.setPreferredSize(new Dimension(300, 25));
        formPanel.add(toCombo, gbc);

        // Priority
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Priority:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> priorityCombo = new JComboBox<>(new String[]{"Normal", "High", "Urgent"});
        formPanel.add(priorityCombo, gbc);

        // Subject
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Subject:"), gbc);
        gbc.gridx = 1;
        formPanel.add(subjectField, gbc);

        // Message body
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Message:"), gbc);
        gbc.gridx = 1;
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        messageScrollPane.setPreferredSize(new Dimension(400, 200));
        formPanel.add(messageScrollPane, gbc);

        // Attachment
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Attachment:"), gbc);
        gbc.gridx = 1;
        JPanel attachPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton attachBtn = new JButton("📎 Attach File");
        JLabel attachLabel = new JLabel("No file selected");
        attachBtn.addActionListener(e -> attachFile());
        attachPanel.add(attachBtn);
        attachPanel.add(attachLabel);
        formPanel.add(attachPanel, gbc);

        // Action buttons
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton sendBtn = new JButton("📤 Send Message");
        JButton saveDraftBtn = new JButton("💾 Save Draft");
        JButton clearBtn = new JButton("🗑️ Clear");

        sendBtn.addActionListener(e -> sendMessage());
        saveDraftBtn.addActionListener(e -> saveDraft());
        clearBtn.addActionListener(e -> clearForm());

        buttonPanel.add(sendBtn);
        buttonPanel.add(saveDraftBtn);
        buttonPanel.add(clearBtn);
        formPanel.add(buttonPanel, gbc);

        panel.add(formPanel, BorderLayout.NORTH);

        // Quick templates
        JPanel templatesPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        templatesPanel.setBorder(BorderFactory.createTitledBorder("Quick Templates"));

        String[] templates = {
            "📚 Assignment Question", "🏥 Absence Notification", "📅 Meeting Request",
            "💰 Financial Inquiry", "🔧 Technical Support", "📋 General Inquiry"
        };

        for (String template : templates) {
            JButton templateBtn = new JButton(template);
            templateBtn.addActionListener(e -> useTemplate(template));
            templatesPanel.add(templateBtn);
        }

        panel.add(templatesPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAnnouncementsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Filter panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Category:"));
        JComboBox<String> categoryFilter = new JComboBox<>(new String[]{"All Categories", "Academic", "Administrative", "Events", "Emergency", "General"});
        filterPanel.add(categoryFilter);
        
        JButton filterBtn = new JButton("🔍 Filter");
        JButton markReadBtn = new JButton("✅ Mark as Read");
        
        filterBtn.addActionListener(e -> filterAnnouncements());
        markReadBtn.addActionListener(e -> markAnnouncementRead());
        
        filterPanel.add(filterBtn);
        filterPanel.add(markReadBtn);
        panel.add(filterPanel, BorderLayout.NORTH);

        // Announcements table
        JScrollPane scrollPane = new JScrollPane(announcementsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Announcements"));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Action buttons
        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton readBtn = new JButton("📖 Read Full Announcement");
        JButton subscribeBtn = new JButton("🔔 Subscribe to Category");
        JButton exportBtn = new JButton("📤 Export");

        readBtn.addActionListener(e -> readAnnouncement());
        subscribeBtn.addActionListener(e -> subscribeToCategory());
        exportBtn.addActionListener(e -> exportAnnouncements());

        actionPanel.add(readBtn);
        actionPanel.add(subscribeBtn);
        actionPanel.add(exportBtn);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createForumsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Course Forums Card
        JPanel courseForumsCard = createForumCard("Course Forums", 
            "📚 MATH201 Discussion\n" +
            "   • Latest: Midterm Study Guide\n" +
            "   • Posts: 45 | Replies: 128\n\n" +
            "🔬 PHYS201 Discussion\n" +
            "   • Latest: Lab Report Questions\n" +
            "   • Posts: 32 | Replies: 89\n\n" +
            "🧪 CHEM301 Discussion\n" +
            "   • Latest: Reaction Mechanisms\n" +
            "   • Posts: 28 | Replies: 76");

        // General Forums Card
        JPanel generalForumsCard = createForumCard("General Forums", 
            "🎓 Student Life\n" +
            "   • Latest: Campus Events This Week\n" +
            "   • Posts: 156 | Replies: 423\n\n" +
            "💼 Career Services\n" +
            "   • Latest: Internship Opportunities\n" +
            "   • Posts: 89 | Replies: 234\n\n" +
            "🏠 Housing & Dining\n" +
            "   • Latest: Meal Plan Changes\n" +
            "   • Posts: 67 | Replies: 145");

        // Study Groups Card
        JPanel studyGroupsCard = createForumCard("Study Groups", 
            "👥 MATH201 Study Group\n" +
            "   • Members: 12\n" +
            "   • Next Meeting: Feb 8, 7 PM\n\n" +
            "👥 PHYS201 Lab Partners\n" +
            "   • Members: 8\n" +
            "   • Next Meeting: Feb 10, 3 PM\n\n" +
            "👥 Final Exam Prep\n" +
            "   • Members: 25\n" +
            "   • Next Meeting: TBD");

        // Q&A Forums Card
        JPanel qaForumsCard = createForumCard("Q&A Forums", 
            "❓ Academic Help\n" +
            "   • Latest: Calculus Integration\n" +
            "   • Questions: 234 | Answers: 456\n\n" +
            "🔧 Technical Support\n" +
            "   • Latest: Portal Login Issues\n" +
            "   • Questions: 45 | Answers: 67\n\n" +
            "💡 General Questions\n" +
            "   • Latest: Library Hours\n" +
            "   • Questions: 123 | Answers: 189");

        panel.add(courseForumsCard);
        panel.add(generalForumsCard);
        panel.add(studyGroupsCard);
        panel.add(qaForumsCard);

        return panel;
    }

    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Communication Statistics"));
        panel.setPreferredSize(new Dimension(800, 100));

        panel.add(createStatCard("Unread Messages", "3", new Color(244, 67, 54)));
        panel.add(createStatCard("New Announcements", "2", new Color(255, 152, 0)));
        panel.add(createStatCard("Forum Posts", "12", new Color(33, 150, 243)));
        panel.add(createStatCard("Study Groups", "3", new Color(76, 175, 80)));

        return panel;
    }

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(color);
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(color);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createForumCard(String title, String content) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createTitledBorder(title));
        card.setBackground(Color.WHITE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(content);
        textArea.setBackground(Color.WHITE);
        textArea.setFont(new Font("Arial", Font.PLAIN, 11));

        JScrollPane scrollPane = new JScrollPane(textArea);
        card.add(scrollPane, BorderLayout.CENTER);

        JButton accessBtn = new JButton("🔗 Access Forum");
        accessBtn.addActionListener(e -> accessForum(title));
        card.add(accessBtn, BorderLayout.SOUTH);

        return card;
    }

    private void loadSampleData() {
        // Sample messages data
        messagesModel.addRow(new Object[]{"Dr. Smith", "Assignment 3 Feedback", "2024-01-30", "Unread", "Normal"});
        messagesModel.addRow(new Object[]{"Prof. Johnson", "Lab Report Due Date", "2024-01-29", "Read", "High"});
        messagesModel.addRow(new Object[]{"Academic Advisor", "Course Registration", "2024-01-28", "Unread", "Normal"});
        messagesModel.addRow(new Object[]{"Finance Office", "Payment Reminder", "2024-01-27", "Read", "High"});
        messagesModel.addRow(new Object[]{"IT Support", "Password Reset Confirmation", "2024-01-26", "Read", "Normal"});

        // Sample announcements data
        announcementsModel.addRow(new Object[]{"Spring Break Schedule", "Administration", "2024-01-30", "Academic", "Unread"});
        announcementsModel.addRow(new Object[]{"Library Extended Hours", "Library Services", "2024-01-29", "General", "Read"});
        announcementsModel.addRow(new Object[]{"Career Fair 2024", "Career Services", "2024-01-28", "Events", "Unread"});
        announcementsModel.addRow(new Object[]{"Campus Safety Alert", "Security", "2024-01-27", "Emergency", "Read"});
        announcementsModel.addRow(new Object[]{"New Dining Options", "Dining Services", "2024-01-26", "General", "Read"});
    }

    // Action methods
    private void refreshMessages() {
        JOptionPane.showMessageDialog(this, "Messages refreshed successfully!", "Refresh Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    private void markAsRead() {
        int selectedRow = messagesTable.getSelectedRow();
        if (selectedRow != -1) {
            messagesModel.setValueAt("Read", selectedRow, 3);
            JOptionPane.showMessageDialog(this, "Message marked as read!", "Status Updated", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void readMessage() {
        int selectedRow = messagesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a message to read.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String from = (String) messagesModel.getValueAt(selectedRow, 0);
        String subject = (String) messagesModel.getValueAt(selectedRow, 1);
        
        String messageContent = "From: " + from + "\n" +
                               "Subject: " + subject + "\n" +
                               "Date: " + messagesModel.getValueAt(selectedRow, 2) + "\n\n" +
                               "Dear Student,\n\n" +
                               "This is a sample message content. In a real application, " +
                               "this would display the actual message content from the database.\n\n" +
                               "The message would include all formatting, attachments, and " +
                               "other relevant information.\n\n" +
                               "Best regards,\n" + from;
        
        JTextArea messageArea = new JTextArea(messageContent);
        messageArea.setEditable(false);
        messageArea.setRows(15);
        messageArea.setColumns(50);
        
        JScrollPane scrollPane = new JScrollPane(messageArea);
        JOptionPane.showMessageDialog(this, scrollPane, "Message: " + subject, JOptionPane.INFORMATION_MESSAGE);
        
        // Mark as read
        messagesModel.setValueAt("Read", selectedRow, 3);
    }

    private void replyToMessage() {
        int selectedRow = messagesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a message to reply to.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String subject = "Re: " + messagesModel.getValueAt(selectedRow, 1);
        subjectField.setText(subject);
        messageArea.setText("\n\n--- Original Message ---\n");
        
        JOptionPane.showMessageDialog(this, "Reply template loaded in Compose tab.", "Reply Ready", JOptionPane.INFORMATION_MESSAGE);
    }

    private void forwardMessage() {
        JOptionPane.showMessageDialog(this, "Forward message interface would open here.", "Forward Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteMessage() {
        int selectedRow = messagesTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Delete this message?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                messagesModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Message deleted!", "Delete Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void archiveMessage() {
        JOptionPane.showMessageDialog(this, "Message archived successfully!", "Archive Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sendMessage() {
        String subject = subjectField.getText();
        String message = messageArea.getText();
        
        if (subject.trim().isEmpty() || message.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in subject and message.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Message sent successfully!", "Send Successful", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
    }

    private void saveDraft() {
        JOptionPane.showMessageDialog(this, "Message saved as draft!", "Draft Saved", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
        subjectField.setText("");
        messageArea.setText("");
    }

    private void attachFile() {
        JOptionPane.showMessageDialog(this, "File attachment dialog would open here.", "Attach File", JOptionPane.INFORMATION_MESSAGE);
    }

    private void useTemplate(String template) {
        switch (template) {
            case "📚 Assignment Question":
                subjectField.setText("Question about Assignment");
                messageArea.setText("Dear Professor,\n\nI have a question about the recent assignment...\n\nBest regards,\n[Your Name]");
                break;
            case "🏥 Absence Notification":
                subjectField.setText("Absence Notification");
                messageArea.setText("Dear Professor,\n\nI will be unable to attend class on [date] due to...\n\nBest regards,\n[Your Name]");
                break;
            default:
                messageArea.setText("Template for " + template + " loaded.");
        }
    }

    private void filterAnnouncements() {
        JOptionPane.showMessageDialog(this, "Announcements filtered successfully!", "Filter Applied", JOptionPane.INFORMATION_MESSAGE);
    }

    private void markAnnouncementRead() {
        int selectedRow = announcementsTable.getSelectedRow();
        if (selectedRow != -1) {
            announcementsModel.setValueAt("Read", selectedRow, 4);
            JOptionPane.showMessageDialog(this, "Announcement marked as read!", "Status Updated", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void readAnnouncement() {
        int selectedRow = announcementsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an announcement to read.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String title = (String) announcementsModel.getValueAt(selectedRow, 0);
        JOptionPane.showMessageDialog(this, "Full announcement for '" + title + "' would open here.", "Read Announcement", JOptionPane.INFORMATION_MESSAGE);
    }

    private void subscribeToCategory() {
        JOptionPane.showMessageDialog(this, "Category subscription settings would open here.", "Subscribe to Category", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportAnnouncements() {
        JOptionPane.showMessageDialog(this, "Announcements exported successfully!", "Export Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    private void accessForum(String forumTitle) {
        JOptionPane.showMessageDialog(this, "Accessing " + forumTitle + "...\nForum interface would open here.", "Access Forum", JOptionPane.INFORMATION_MESSAGE);
    }
}
