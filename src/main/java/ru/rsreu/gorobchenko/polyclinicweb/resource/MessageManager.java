package ru.rsreu.gorobchenko.polyclinicweb.resource;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private MessageManager() {
    }

    public static String get(String key) {
        return resourceBundle.getString(key);
    }
}
