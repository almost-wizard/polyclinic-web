package ru.rsreu.gorobchenko.polyclinicweb.model.appointment;

import ru.rsreu.gorobchenko.polyclinicweb.model.user.NullUser;

import java.sql.Date;

public class NullAppointment extends Appointment {
    private static final NullAppointment instance = new NullAppointment();

    public NullAppointment() {
        super(0, NullUser.getInstance(), NullUser.getInstance(), new Date(System.currentTimeMillis()));
    }

    public static NullAppointment getInstance() {
        return instance;
    }
}
