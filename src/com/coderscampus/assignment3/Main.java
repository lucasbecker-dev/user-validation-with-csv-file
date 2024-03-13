package com.coderscampus.assignment3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int LINES_TO_READ = 4;
        final String DATA_FILEPATH = "src/com/coderscampus/assignment3/data.txt";
        try (Scanner scanner = new Scanner(System.in)) {
            final User[] users = UserService.parseFileIntoUsers(DATA_FILEPATH, LINES_TO_READ);
            UserService.userLogin(scanner, users);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting program due to error.");
        }
    }
}