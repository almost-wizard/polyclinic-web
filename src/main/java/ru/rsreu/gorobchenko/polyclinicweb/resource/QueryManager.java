package ru.rsreu.gorobchenko.polyclinicweb.resource;

import java.util.ResourceBundle;

public class QueryManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("queries");

    private QueryManager() {
    }

    public static String get(String key) {
        return resourceBundle.getString(key);
    }
}
