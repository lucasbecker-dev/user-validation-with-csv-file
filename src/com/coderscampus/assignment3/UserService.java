package com.coderscampus.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserService {
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
            throw new RuntimeException(e);
        }
    }

    public static UserCredentials getUserCredentials(Scanner scanner) {
        String username;
        String password;
        System.out.print("Enter username: ");
        username = scanner.nextLine();
        System.out.print("Enter password: ");
        password = scanner.nextLine();
        return new UserCredentials(username, password);
    }

    public static boolean validateUserCredentials(User user, UserCredentials userCredentials) {
        // TODO: finish this method
        return false;
    }
}
