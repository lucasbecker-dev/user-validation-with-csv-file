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
            System.out.println("File " + filePath + " was not found.");
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
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println(e.getMessage());
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
}
