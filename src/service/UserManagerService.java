package service;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import util.Library;

public class UserManagerService {

    private List<User> users;
    private final String dataFileName = "user.dat";
    private Library library;

    public UserManagerService() {
        library = new Library();
        loadUsers();
    }

    private void loadUsers() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataFileName))) {
            users = (List<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new ArrayList<>();
        }
    }

    private void saveUsers() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataFileName))) {
            objectOutputStream.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createUser(String username, String password) {
        while (true) {
            if (username.length() < 5 || username.contains(" ")) {
                System.out.println("You must enter at least 5 characters, and no space for username.");
                username = library.getString("Enter Username"); // Sử dụng thể hiện của Library để lấy đầu vào
            } else if (password.length() < 6 || password.contains(" ")) {
                System.out.println("You must enter at least 6 characters, and no space for password.");
                password = library.getString("Enter Password"); // Sử dụng thể hiện của Library để lấy đầu vào
            } else {
                break;
            }
        }

        if (!isUsernameTaken(username)) {
            users.add(new User(username, password));
            saveUsers();
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
