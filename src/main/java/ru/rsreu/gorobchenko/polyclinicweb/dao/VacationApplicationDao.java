package ru.rsreu.gorobchenko.polyclinicweb.dao;

import ru.rsreu.gorobchenko.polyclinicweb.exception.SqlProcedureException;
import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.VacationApplication;

import java.sql.Date;
import java.util.List;

/**
 * Defines the contract for accessing and managing vacation application data.
 * This interface provides methods for retrieving, creating, approving, and rejecting vacation applications
 * from a data storage.
 */
public interface VacationApplicationDao {
    /**
     * Retrieves all {@link VacationApplication} objects from the data storage.
     *
     * @return A list containing all {@link VacationApplication} objects.
     */
    List<VacationApplication> getAll();

    /**
     * Approves a vacation application for a specific doctor and date range.
     *
     * @param doctorId  The unique identifier of the doctor.
     * @param startDate The start date of the vacation.
     * @param endDate   The end date of the vacation.
     * @return {@code true} if the application was approved successfully, {@code false} otherwise.
     */
    boolean approve(int doctorId, Date startDate, Date endDate);

    /**
     * Rejects a vacation application for a specific doctor and date range.
     *
     * @param doctorId  The unique identifier of the doctor.
     * @param startDate The start date of the vacation.
     * @param endDate   The end date of the vacation.
     * @return {@code true} if the application was rejected successfully, {@code false} otherwise.
     */
    boolean reject(int doctorId, Date startDate, Date endDate);

    /**
     * Creates a new {@link VacationApplication} in the data storage.
     *
     * @param doctorId  The unique identifier of the doctor.
     * @param startDate The start date of the vacation.
     * @param endDate   The end date of the vacation.
     * @throws SqlProcedureException if an error occurs during the execution of a SQL procedure.
     */
    void create(int doctorId, Date startDate, Date endDate) throws SqlProcedureException;
}
