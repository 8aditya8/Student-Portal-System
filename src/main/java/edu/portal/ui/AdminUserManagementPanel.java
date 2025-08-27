// src/main/java/edu/portal/ui/AdminUserManagementPanel.java
package edu.portal.ui;

import edu.portal.model.User;
import edu.portal.service.UserService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Admin Panel Feature 1: User Management
 * - Create, edit, deactivate users
 * - Assign roles/permissions for Admin, Instructor, and Student
 */
public class AdminUserManagementPanel extends JPanel {
    private UserService userService;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> roleFilter;
    private JButton addUserBtn, editUserBtn, deleteUserBtn, resetPasswordBtn;

    public AdminUserManagementPanel() {
        userService = UserService.getInstance();
        initComponents();
        setupLayout();
        loadUsers();
    }

    private void initComponents() {
        // Search and filter components
        searchField = new JTextField(20);
        roleFilter = new JComboBox<>(new String[]{"All Roles", "ADMIN", "INSTRUCTOR", "STUDENT"});
        
        // Action buttons
        addUserBtn = new JButton("👤 Add User");
        editUserBtn = new JButton("✏️ Edit User");
        deleteUserBtn = new JButton("🗑️ Delete User");
        resetPasswordBtn = new JButton("🔑 Reset Password");
        
        // User table
        String[] columns = {"ID", "Username", "Role", "Status", "Created", "Last Login"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Add action listeners
        addUserBtn.addActionListener(this::addUser);
        editUserBtn.addActionListener(this::editUser);
        deleteUserBtn.addActionListener(this::deleteUser);
        resetPasswordBtn.addActionListener(this::resetPassword);
        searchField.addActionListener(e -> filterUsers());
        roleFilter.addActionListener(e -> filterUsers());
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("👥 User Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Search and filter panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("🔍 Search:"));
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Filter:"));
        searchPanel.add(roleFilter);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Table panel
        JScrollPane tableScrollPane = new JScrollPane(userTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("User List"));
        add(tableScrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addUserBtn);
        buttonPanel.add(editUserBtn);
        buttonPanel.add(deleteUserBtn);
        buttonPanel.add(resetPasswordBtn);
        
        // Role assignment panel
        JPanel rolePanel = new JPanel(new FlowLayout());
        JButton assignRoleBtn = new JButton("🎭 Assign Role");
        JButton managePermissionsBtn = new JButton("🔐 Manage Permissions");
        rolePanel.add(assignRoleBtn);
        rolePanel.add(managePermissionsBtn);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(rolePanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Style buttons
        styleButton(addUserBtn, new Color(40, 167, 69));
        styleButton(editUserBtn, new Color(255, 193, 7));
        styleButton(deleteUserBtn, new Color(220, 53, 69));
        styleButton(resetPasswordBtn, new Color(23, 162, 184));
        styleButton(assignRoleBtn, new Color(111, 66, 193));
        styleButton(managePermissionsBtn, new Color(108, 117, 125));
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(150, 35));
    }

    private void loadUsers() {
        tableModel.setRowCount(0);
        List<User> users = userService.getAllUsers();
        
        for (User user : users) {
            Object[] row = {
                user.getId(),
                user.getUsername(),
                user.getRole(),
                "Active", // Status - could be enhanced with actual status field
                "2024-01-01", // Created date - could be enhanced with actual date
                "Recently" // Last login - could be enhanced with actual tracking
            };
            tableModel.addRow(row);
        }
    }

    private void filterUsers() {
        // Implementation for filtering users based on search and role filter
        String searchText = searchField.getText().toLowerCase();
        String selectedRole = (String) roleFilter.getSelectedItem();
        
        tableModel.setRowCount(0);
        List<User> users = userService.getAllUsers();
        
        for (User user : users) {
            boolean matchesSearch = searchText.isEmpty() || 
                                  user.getUsername().toLowerCase().contains(searchText);
            boolean matchesRole = "All Roles".equals(selectedRole) || 
                                user.getRole().equals(selectedRole);
            
            if (matchesSearch && matchesRole) {
                Object[] row = {
                    user.getId(),
                    user.getUsername(),
                    user.getRole(),
                    "Active",
                    "2024-01-01",
                    "Recently"
                };
                tableModel.addRow(row);
            }
        }
    }

    private void addUser(ActionEvent e) {
        UserDialog dialog = new UserDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add User", null);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            User newUser = dialog.getUser();
            if (userService.addUser(newUser.getUsername(), newUser.getPassword(), newUser.getRole())) {
                loadUsers();
                JOptionPane.showMessageDialog(this, "User created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editUser(ActionEvent e) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (Integer) tableModel.getValueAt(selectedRow, 0);
        User user = userService.getUserById(userId);
        
        if (user != null) {
            UserDialog dialog = new UserDialog((Frame) SwingUtilities.getWindowAncestor(this), "Edit User", user);
            dialog.setVisible(true);
            
            if (dialog.isConfirmed()) {
                User updatedUser = dialog.getUser();
                if (userService.updateUser(user.getId(), updatedUser.getUsername(), updatedUser.getPassword(), updatedUser.getRole())) {
                    loadUsers();
                    JOptionPane.showMessageDialog(this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update user!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void deleteUser(ActionEvent e) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete user: " + username + "?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (userService.deleteUser(userId)) {
                loadUsers();
                JOptionPane.showMessageDialog(this, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetPassword(ActionEvent e) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to reset password.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (Integer) tableModel.getValueAt(selectedRow, 0);
        String username = (String) tableModel.getValueAt(selectedRow, 1);
        String newPassword = JOptionPane.showInputDialog(this, "Enter new password for " + username + ":");
        
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            if (userService.resetPassword(userId, newPassword)) {
                JOptionPane.showMessageDialog(this, "Password reset successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to reset password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}