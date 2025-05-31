package ru.rsreu.gorobchenko.polyclinicweb.dao;

import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.ScheduleDay;

import java.sql.Date;
import java.util.List;

/**
 * Defines the contract for accessing and managing schedule day data.
 * This interface provides methods for retrieving schedule day records and modifying work/vacation status
 * from a data storage.
 */
public interface ScheduleDayDao {
    /**
     * Retrieves all {@link ScheduleDay} objects for a specific doctor.
     *
     * @param doctorId The unique identifier of the doctor.
     * @return A list containing all {@link ScheduleDay} objects for the given doctor.
     */
    List<ScheduleDay> getAll(int doctorId);

    /**
     * Sets a specific date as a vacation day for a doctor.
     *
     * @param doctorId The unique identifier of the doctor.
     * @param date     The date to set as a vacation day.
     * @return {@code true} if the date was successfully set as a vacation day, {@code false} otherwise.
     */
    boolean setVacationDay(int doctorId, Date date);

    /**
     * Sets a specific date as a work day for a doctor.
     *
     * @param doctorId The unique identifier of the doctor.
     * @param date     The date to set as a work day.
     * @return {@code true} if the date was successfully set as a work day, {@code false} otherwise.
     */
    boolean setWorkDay(int doctorId, Date date);
}
