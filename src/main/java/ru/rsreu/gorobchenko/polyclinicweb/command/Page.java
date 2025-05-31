package ru.rsreu.gorobchenko.polyclinicweb.command;

import ru.rsreu.gorobchenko.polyclinicweb.resource.ConfigurationManager;

public enum Page {
    // Base
    INDEX(ConfigurationManager.get("path.page.index")),
    UNAUTHORIZED(ConfigurationManager.get("path.page.unauthorized")),
    FORBIDDEN(ConfigurationManager.get("path.page.forbidden")),
    NOT_FOUND(ConfigurationManager.get("path.page.not-found")),

    // Authorization
    LOGIN(ConfigurationManager.get("path.page.login")),
    LOGOUT(ConfigurationManager.get("path.page.logout")),

    // Users
    PROFILE(ConfigurationManager.get("path.page.users.profile")),
    ALL_USERS(ConfigurationManager.get("path.page.users.list")),
    CREATE_USER(ConfigurationManager.get("path.page.users.create")),
    EDIT_USER(ConfigurationManager.get("path.page.users.edit")),
    DELETE_USER(ConfigurationManager.get("path.page.users.delete")),

    // Doctors
    ALL_DOCTORS(ConfigurationManager.get("path.page.doctors")),

    // Appointments
    ALL_APPOINTMENTS(ConfigurationManager.get("path.page.appointments.all")),
    DELETE_APPOINTMENT(ConfigurationManager.get("path.page.appointments.delete")),
    CREATE_APPOINTMENT(ConfigurationManager.get("path.page.appointments.create")),

    // Medical card history
    MEDICAL_CARD_HISTORY(ConfigurationManager.get("path.page.medical-card-history.list")),
    CREATE_MEDICAL_CARD_HISTORY(ConfigurationManager.get("path.page.medical-card-history.create")),

    // Schedules
    SCHEDULES(ConfigurationManager.get("path.page.schedules.list")),
    ALL_VACATION_APPLICATIONS(ConfigurationManager.get("page.page.vacation-applications.list")),
    CREATE_VACATION_APPLICATION(ConfigurationManager.get("page.page.vacation-applications.create")),
    ;

    private final String page;

    Page(String page) {
        this.page = page;
    }

    public String get() {
        return page;
    }
}
