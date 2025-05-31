package ru.rsreu.gorobchenko.polyclinicweb.dao;

import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;

import java.sql.Date;
import java.util.List;

/**
 * Defines the contract for accessing and managing appointment data.
 * This interface provides methods for retrieving, creating, and deleting appointment records
 * from a data storage.
 */
public interface AppointmentDao {
    /**
     * Retrieves a single {@link Appointment} based on the provided ID.
     *
     * @param id The unique identifier of the appointment.
     * @return The {@link Appointment} object if found, otherwise, returns a null object instance.
     */
    Appointment getOne(int id);

    /**
     * Retrieves all {@link Appointment} objects for a specific date and doctor, considering weekends.
     *
     * @param datetime The date of the appointments.
     * @param doctorId The unique identifier of the doctor.
     * @return A list containing all {@link Appointment} objects for the given date and doctor, considering weekends.
     */
    List<Appointment> getAllConsiderWeekend(Date datetime, int doctorId);

    /**
     * Retrieves all {@link Appointment} objects for a specific date and doctor.
     *
     * @param datetime The date of the appointments.
     * @param doctorId The unique identifier of the doctor.
     * @return A list containing all {@link Appointment} objects for the given date and doctor.
     */
    List<Appointment> getAll(Date datetime, int doctorId);

    /**
     * Deletes an {@link Appointment} from the data storage based on its ID.
     *
     * @param id The unique identifier of the appointment to be deleted.
     * @return {@code true} if the appointment was deleted successfully, {@code false} otherwise.
     */
    boolean delete(int id);

    /**
     * Creates a new {@link Appointment} in the data storage.
     *
     * @param patientId The unique identifier of the patient.
     * @param doctorId  The unique identifier of the doctor.
     * @param datetime  The date and time of the appointment.
     * @return {@code true} if the appointment was created successfully, {@code false} otherwise.
     */
    boolean create(int patientId, int doctorId, Date datetime);
}
