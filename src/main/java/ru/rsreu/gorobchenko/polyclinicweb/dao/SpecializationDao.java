package ru.rsreu.gorobchenko.polyclinicweb.dao;

import ru.rsreu.gorobchenko.polyclinicweb.model.Specialization;

import java.util.List;

/**
 * Defines the contract for accessing and managing specialization data.
 * This interface provides methods for retrieving specialization records from a data storage.
 */
public interface SpecializationDao {
    /**
     * Retrieves all {@link Specialization} objects from the data storage.
     *
     * @return A list containing all {@link Specialization} objects.
     */
    List<Specialization> getAll();
}
