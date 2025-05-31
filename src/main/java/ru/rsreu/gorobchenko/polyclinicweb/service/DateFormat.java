package ru.rsreu.gorobchenko.polyclinicweb.service;

public enum DateFormat {
    DATE("dd.MM.yyyy"),
    HTML_DATE("yyyy-MM-dd"),
    TIME("HH:mm"),
    FULL("dd.MM.yyyy HH:mm:ss");

    private final String format;

    DateFormat(String format) {
        this.format = format;
    }

    public String get() {
        return format;
    }
}
