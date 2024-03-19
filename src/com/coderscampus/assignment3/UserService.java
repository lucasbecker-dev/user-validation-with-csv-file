package com.coderscampus.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserService {
    private static User[] users;

    public static void loadUsers(String filePath, int linesToRead) {
        users = FileService.parseFileIntoUsers(filePath, linesToRead);
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
