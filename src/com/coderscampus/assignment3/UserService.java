package com.coderscampus.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserService {
    public static final int MAX_LOGIN_ATTEMPTS = 5;

    public static User[] parseFileIntoUsers(String filePath, int linesToRead) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            User[] users = new User[linesToRead];
            int currentLineCount = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null && currentLineCount < linesToRead) {
                String[] splitLine = line.split(",");
                users[currentLineCount] = new User(splitLine[0], splitLine[1], splitLine[2]);
                ++currentLineCount;
            }
            return users;
        } catch (IOException e) {
            System.out.println("File " + filePath + " was not found: " + e.getMessage());
            return null;
        }
    }

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

    public static User validateUserCredentials(User[] users, UserCredentials userCredentials) {
        String formattedUsername = userCredentials.getUsername().toUpperCase();

        for (User user : users) {
            if (user.getUsername().toUpperCase().equals(formattedUsername) && user.getPassword().equals(userCredentials.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public static void userLogin(Scanner scanner, User[] users) throws NullPointerException {
        if (users == null) {
            throw new NullPointerException("Error: users array is null!");
        }
        boolean loginSuccess = false;
        for (int i = 0; i < MAX_LOGIN_ATTEMPTS; i++) {
            UserCredentials userCredentials = createUserCredentials(scanner);
            if (userCredentials == null) {
                throw new NullPointerException("Error: userCredentials is null");
            }
            User validUser = validateUserCredentials(users, userCredentials);
            if (validUser != null) {
                System.out.println("Welcome " + validUser.getName());
                loginSuccess = true;
                break;
            } else {
                System.out.println("Invalid login, please try again. Remaining login attempts: " + (MAX_LOGIN_ATTEMPTS - (i + 1)));
            }
        }
        if (!loginSuccess) {
            System.out.println("Too many failed login attempts, you are now locked out.");
        }
    }
}
