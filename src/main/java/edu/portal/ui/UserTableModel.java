// src/main/java/edu/portal/ui/UserTableModel.java
package edu.portal.ui;

import edu.portal.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UserTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Username", "Role", "Status"};
    private List<User> users = new ArrayList<>();

    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0: return user.getId();
            case 1: return user.getUsername();
            case 2: return user.getRole();
            case 3: return "Active";
            default: return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class;
            case 1:
            case 2:
            case 3: return String.class;
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Make table read-only
    }

    public User getUserAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < users.size()) {
            return users.get(rowIndex);
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
        fireTableRowsInserted(users.size() - 1, users.size() - 1);
    }

    public void updateUser(int rowIndex, User user) {
        if (rowIndex >= 0 && rowIndex < users.size()) {
            users.set(rowIndex, user);
            fireTableRowsUpdated(rowIndex, rowIndex);
        }
    }

    public void removeUser(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < users.size()) {
            users.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}