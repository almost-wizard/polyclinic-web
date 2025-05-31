package ru.rsreu.gorobchenko.polyclinicweb.model.schedules;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.Nullable;

import java.sql.Date;
import java.time.LocalDate;


/**
 * Represents a schedule day in the system.
 * This class stores information about a specific date and whether it's a working day or not.
 * It also implements the {@link Nullable} interface, providing a null object pattern for schedule day instances.
 */
public class ScheduleDay implements Nullable<ScheduleDay> {
    private final Date date;
    private final boolean isWorking;

    /**
     * A constant representing the null schedule day instance.
     */
    public static final ScheduleDay NULL = new ScheduleDay(new Date(System.currentTimeMillis()), false);

    /**
     * Constructs a new {@code ScheduleDay} with the specified date and working status.
     *
     * @param date      The date of the schedule day.
     * @param isWorking Indicates whether the day is a working day or not.
     */
    public ScheduleDay(Date date, boolean isWorking) {
        this.date = date;
        this.isWorking = isWorking;
    }

    /**
     * Returns the date of the schedule day.
     *
     * @return The {@link Date} object representing the schedule day.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Checks if the schedule day is a working day.
     *
     * @return {@code true} if the day is a working day, {@code false} otherwise.
     */
    public boolean isWorking() {
        return isWorking;
    }

    /**
     * Returns the null schedule day instance.
     *
     * @return The {@link ScheduleDay#NULL} instance.
     */
    @Override
    public ScheduleDay getNull() {
        return ScheduleDay.NULL;
    }

    /**
     * Checks if this schedule day instance is the null schedule day.
     *
     * @return {@code true} if this is the null schedule day instance, {@code false} otherwise.
     */
    public boolean isNull() {
        return this == ScheduleDay.NULL;
    }

    /**
     * Checks if the schedule day is today's date.
     *
     * @return {@code true} if the schedule day is today, {@code false} otherwise.
     */
    public boolean isToday() {
        return date.toLocalDate().equals(LocalDate.now());
    }
}
