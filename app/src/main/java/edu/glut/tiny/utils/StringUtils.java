package edu.glut.tiny.utils;


public class StringUtils {
    public static boolean isValidUsername(String username) {
        return username.matches("[a-zA-Z0-9_]*");
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^[0-9]\\w{3,20}$");
    }
}
