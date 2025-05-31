package ru.rsreu.gorobchenko.polyclinicweb.dao;

import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;

import java.util.List;

/**
 * Defines the contract for accessing and managing doctor data.
 * This interface provides methods for retrieving doctor records from a data storage.
 */
public interface DoctorDao {
    /**
     * Retrieves a single {@link Doctor} based on the provided ID.
     *
     * @param id The unique identifier of the doctor.
     * @return The {@link Doctor} object if found, otherwise, returns a null object instance.
     */
    Doctor getOne(int id);

    /**
     * Retrieves all {@link Doctor} objects from the data storage.
     *
     * @return A list containing all {@link Doctor} objects.
     */
    List<Doctor> getAll();

    /**
     * Add specialization to doctor
     * @param doctorId The doctor id
     * @param specializationId The specialization id
     * @return Result - was specialization added or not
     */
    boolean setSpecialization(int doctorId, int specializationId);
}
