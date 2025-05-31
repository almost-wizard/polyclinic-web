package ru.rsreu.gorobchenko.polyclinicweb.model;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.Nullable;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.NullAppointment;

/**
 * Represents a medical card history entry in the system.
 * This class stores information about an appointment, diagnosis, and therapy related to a medical card history entry.
 * It also implements the {@link Nullable} interface, providing a null object pattern for medical card history instances.
 */
public class MedicalCardHistory implements Nullable<MedicalCardHistory> {
    private final Appointment appointment;
    private final String diagnosis;
    private final String therapy;

    /**
     * A constant representing the null medical card history instance.
     */
    public static final MedicalCardHistory NULL = new MedicalCardHistory(NullAppointment.getInstance(), "", "");

    /**
     * Constructs a new {@code MedicalCardHistory} with the specified attributes.
     *
     * @param appointment The {@link Appointment} associated with this history entry.
     * @param diagnosis   The diagnosis made during the appointment.
     * @param therapy     The therapy prescribed during the appointment.
     */
    public MedicalCardHistory(Appointment appointment, String diagnosis, String therapy) {
        this.appointment = appointment;
        this.diagnosis = diagnosis;
        this.therapy = therapy;
    }

    /**
     * Returns the ID of the associated {@link Appointment}.
     *
     * @return The ID of the associated appointment.
     */
    public int getId() {
        return appointment.getId();
    }

    /**
     * Returns the {@link Appointment} associated with this medical card history entry.
     *
     * @return The associated appointment.
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Returns the diagnosis made during the appointment.
     *
     * @return The diagnosis as a string.
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Returns the therapy prescribed during the appointment.
     *
     * @return The therapy as a string.
     */
    public String getTherapy() {
        return therapy;
    }

    /**
     * Returns the null medical card history instance.
     *
     * @return The {@link MedicalCardHistory#NULL} instance.
     */
    @Override
    public MedicalCardHistory getNull() {
        return MedicalCardHistory.NULL;
    }
}
