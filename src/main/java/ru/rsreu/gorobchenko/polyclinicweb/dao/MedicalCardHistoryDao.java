package ru.rsreu.gorobchenko.polyclinicweb.dao;

import ru.rsreu.gorobchenko.polyclinicweb.model.MedicalCardHistory;

import java.util.List;

/**
 * Defines the contract for accessing and managing medical card history data.
 * This interface provides methods for retrieving and creating medical card history records
 * from a data storage.
 */
public interface MedicalCardHistoryDao {
    /**
     * Retrieves all {@link MedicalCardHistory} objects for a specific patient.
     *
     * @param patientId The unique identifier of the patient.
     * @return A list containing all {@link MedicalCardHistory} objects for the given patient.
     */
    List<MedicalCardHistory> getAll(int patientId);

    /**
     * Creates a new {@link MedicalCardHistory} in the data storage.
     *
     * @param appointmentId The unique identifier of the associated appointment.
     * @param diagnosis    The diagnosis for this history entry.
     * @param therapy      The therapy for this history entry.
     * @return {@code true} if the medical card history entry was created successfully, {@code false} otherwise.
     */
    boolean create(int appointmentId, String diagnosis, String therapy);
}
