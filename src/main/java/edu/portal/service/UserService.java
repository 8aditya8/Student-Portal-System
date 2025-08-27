// src/main/java/edu/portal/service/UserService.java
package edu.portal.service;

import edu.portal.dao.UserDAO;
import edu.portal.model.User;
import java.util.List;

public class UserService {
    private static UserService instance = new UserService();
    private UserDAO userDAO = new UserDAO();

    private UserService() {}

    public static UserService getInstance() {
        return instance;
    }

    public User authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean addUser(String username, String password, String role) {
        if (userDAO.userExists(username)) {
            return false; // Username already exists
        }
        User user = new User(username, password, role);
        return userDAO.addUser(user);
    }

    public boolean updateUser(int id, String username, String password, String role) {
        User user = new User(username, password, role);
        user.setId(id);
        return userDAO.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        return userDAO.deleteUser(userId);
    }

    public boolean validateUserData(String username, String password, String role) {
        return username != null && !username.trim().isEmpty() &&
               password != null && !password.trim().isEmpty() &&
               role != null && (role.equals("ADMIN") || role.equals("STUDENT") || role.equals("INSTRUCTOR"));
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public List<User> searchUsers(String searchTerm, String role) {
        return userDAO.searchUsers(searchTerm, role);
    }

    public boolean resetPassword(int userId, String newPassword) {
        User user = userDAO.getUserById(userId);
        if (user != null) {
            return userDAO.updateUser(new User(user.getId(), user.getUsername(), newPassword, user.getRole()));
        }
        return false;
    }

    public boolean isUsernameAvailable(String username, int excludeUserId) {
        List<User> users = userDAO.getAllUsers();
        return users.stream().noneMatch(user -> 
            user.getUsername().equals(username) && user.getId() != excludeUserId);
    }
}