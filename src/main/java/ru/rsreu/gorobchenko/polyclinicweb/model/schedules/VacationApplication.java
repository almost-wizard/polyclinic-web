package ru.rsreu.gorobchenko.polyclinicweb.model.schedules;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.Nullable;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;

import java.sql.Date;
import java.time.temporal.ChronoUnit;


/**
 * Represents a vacation application in the system.
 * This class stores information about the start and end dates of the vacation,
 * whether it's approved, and the doctor who applied for the vacation.
 * It also implements the {@link Nullable} interface, providing a null object pattern for vacation application instances.
 */
public class VacationApplication implements Nullable<VacationApplication> {
    private final Date startDate;
    private final Date endDate;
    private final boolean isApproved;
    private final Doctor doctor;

    /**
     * A constant representing the null vacation application instance.
     */
    public static final VacationApplication NULL = new VacationApplication(
            new Date(System.currentTimeMillis()),
            new Date(System.currentTimeMillis()),
            false,
            Doctor.NULL
    );

    /**
     * Constructs a new {@code VacationApplication} with the specified attributes.
     *
     * @param startDate  The start date of the vacation.
     * @param endDate    The end date of the vacation.
     * @param isApproved Indicates whether the vacation application is approved.
     * @param doctor     The {@link Doctor} who applied for the vacation.
     */
    public VacationApplication(Date startDate, Date endDate, boolean isApproved, Doctor doctor) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isApproved = isApproved;
        this.doctor = doctor;
    }

    /**
     * Returns the start date of the vacation.
     *
     * @return The {@link Date} object representing the start date of the vacation.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the vacation.
     *
     * @return The {@link Date} object representing the end date of the vacation.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Checks if the vacation application is approved.
     *
     * @return {@code true} if the application is approved, {@code false} otherwise.
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Returns the {@link Doctor} who applied for the vacation.
     *
     * @return The {@link Doctor} object representing the applicant.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Calculates the duration of the vacation in days.
     *
     * @return The duration of the vacation in days.
     */
    public long getDaysDuration() {
        return ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate()) + 1;
    }

    /**
     * Returns the null vacation application instance.
     *
     * @return The {@link VacationApplication#NULL} instance.
     */
    @Override
    public VacationApplication getNull() {
        return VacationApplication.NULL;
    }
}
