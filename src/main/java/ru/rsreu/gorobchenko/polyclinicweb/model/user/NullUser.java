package ru.rsreu.gorobchenko.polyclinicweb.model.user;

import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.sql.Date;

public class NullUser extends User {
    private static final NullUser instance = new NullUser();

    private NullUser() {
        super(0, "", "", "", "", "", UserRole.PATIENT.getDatabaseRoleId(), new Date(System.currentTimeMillis()), true, false);
    }

    public static NullUser getInstance() {
        return instance;
    }
}
