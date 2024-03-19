package com.coderscampus.assignment3;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserLoginService {
    public static final int MAX_LOGIN_ATTEMPTS = 5;

    public static UserCredentials createUserCredentials(Scanner scanner) {
        String username;
        String password;
        try {
            System.out.print("Enter username: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();
        } catch (IllegalStateException e) {
            System.out.println("Scanner is closed: " + e.getMessage());
            return null;
        } catch (NoSuchElementException e) {
            System.out.println("No input detected: " + e.getMessage());
            return null;
        }
        return new UserCredentials(username, password);
    }

    public static void userLogin() throws NullPointerException {
        boolean loginSuccess = false;
        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < MAX_LOGIN_ATTEMPTS; i++) {
                UserCredentials userCredentials = createUserCredentials(scanner);
                if (userCredentials == null) {
                    throw new NullPointerException("Error: userCredentials is null");
                }
                User validUser = UserService.getUserByUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
                if (validUser != null) {
                    System.out.println("Welcome " + validUser.getName());
                    loginSuccess = true;
                    break;
                } else {
                    System.out.println("Invalid login, please try again. Remaining login attempts: " + (MAX_LOGIN_ATTEMPTS - (i + 1)));
                }
            }
        }
        if (!loginSuccess) {
            System.out.println("Too many failed login attempts, you are now locked out.");
        }
    }
}
