package ru.rsreu.gorobchenko.polyclinicweb.model.appointment;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.Nullable;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormat;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormatter;

import java.sql.Date;

/**
 * Represents an appointment in the system.
 * This class stores information about the appointment, including the doctor, patient, and date/time.
 * It also implements the {@link Nullable} interface, providing a null object pattern for appointment instances.
 */
public class Appointment implements Nullable<Appointment> {
    private final int id;
    private final User doctor;
    private final User patient;
    private final Date datetime;

    /**
     * Constructs a new {@code Appointment} with the specified attributes.
     *
     * @param id       The unique identifier for the appointment.
     * @param doctor   The {@link User} representing the doctor for the appointment.
     * @param patient  The {@link User} representing the patient for the appointment.
     * @param datetime The date and time of the appointment.
     */
    public Appointment(int id, User doctor, User patient, Date datetime) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.datetime = datetime;
    }

    /**
     * Returns the unique identifier of the appointment.
     *
     * @return The ID of the appointment.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the {@link User} representing the doctor for the appointment.
     *
     * @return The doctor {@link User} object.
     */
    public User getDoctor() {
        return doctor;
    }

    /**
     * Returns the {@link User} representing the patient for the appointment.
     *
     * @return The patient {@link User} object.
     */
    public User getPatient() {
        return patient;
    }

    /**
     * Returns the date and time of the appointment.
     *
     * @return The {@link Date} object representing the appointment time.
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Returns a formatted string representation of the date and time of the appointment using {@link DateFormatter#format(Date, DateFormat)}.
     *
     * @return The formatted date and time string.
     */
    public String getDatetimeString() {
        return DateFormatter.format(datetime, DateFormat.FULL);
    }

    /**
     * Returns a formatted string representation of the time of the appointment using {@link DateFormatter#format(Date, DateFormat)}.
     *
     * @return The formatted time string.
     */
    public String getTime() {
        return DateFormatter.format(datetime, DateFormat.TIME);
    }

    /**
     * Checks if the appointment is an empty appointment.
     *
     * @return {@code true} if the appointment is an instance of {@link EmptyAppointment}, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this instanceof EmptyAppointment;
    }

    /**
     * Returns the null appointment instance.
     *
     * @return The {@link NullAppointment} instance.
     */
    @Override
    public Appointment getNull() {
        return NullAppointment.getInstance();
    }
}
