package ru.rsreu.gorobchenko.polyclinicweb.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("configuration");

    private ConfigurationManager() {
    }

    public static String get(String key) {
        return resourceBundle.getString(key);
    }
}