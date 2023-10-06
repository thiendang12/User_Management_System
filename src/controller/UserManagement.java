package controller;

import model.User;
import service.UserManagerService;
import util.Library;
import view.Menu;

public class UserManagement extends Menu<String> {
    static String[] menuChoice = {"Create a new account", "Login system", "Exit"};
    
    private Library library;
    private UserManagerService userService;

    public UserManagement() {
        super("USER MANAGEMENT SYSTEM", menuChoice);
        library = new Library();
        userService = new UserManagerService();
    }

    @Override
    public boolean execute(int n) {
        switch (n) {
            case 1:
                createUser();
                break;
            case 2:
                loginSystem();
                break;
            case 3:
                return false;
            default:
                break;
        }
        return true;
    }

    private void createUser() {
        do {
            String username = library.getString("Enter username");
            String password = library.getString("Enter password");

            boolean result = userService.createUser(username, password);

            if (result) {
                System.out.println("Account created successfully!");
            } else {
                System.out.println("Username is already taken. Please choose another one.");
            }

            String addMore = library.getString("Do you want to create another account? (Y/N)").trim();
            if (!addMore.equalsIgnoreCase("Y")) {
                break;
            }
        } while (true);
    }

    private void loginSystem() {
        String username = library.getString("Enter username");
        String password = library.getString("Enter password");

        boolean result = userService.login(username, password);

        if (result) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
}
