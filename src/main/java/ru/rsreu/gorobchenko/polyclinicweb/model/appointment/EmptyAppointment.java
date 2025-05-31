package ru.rsreu.gorobchenko.polyclinicweb.model.appointment;

import ru.rsreu.gorobchenko.polyclinicweb.model.user.NullUser;

import java.sql.Date;

public class EmptyAppointment extends Appointment {
    public EmptyAppointment(Date datetime) {
        super(0, NullUser.getInstance(), NullUser.getInstance(), datetime);
    }
}
