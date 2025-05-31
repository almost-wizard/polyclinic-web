package ru.rsreu.gorobchenko.polyclinicweb.model;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.Nullable;

/**
 * Represents a specialization of a doctor in the system.
 * This class holds the ID and name of a specialization and implements the {@link Nullable}
 * interface, providing a null object pattern for specialization instances.
 */
public class Specialization implements Nullable<Specialization> {
    /**
     * A constant representing the null specialization instance.
     */
    public static final Specialization NULL = new Specialization(0, "");
    private final int id;
    private final String name;

    /**
     * Constructs a new {@code Specialization} with the specified ID and name.
     *
     * @param id   The unique identifier of the specialization.
     * @param name The name of the specialization.
     */
    public Specialization(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the specialization.
     *
     * @return The ID of the specialization.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the specialization.
     *
     * @return The name of the specialization.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the specialization, which is its name.
     *
     * @return The name of the specialization.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Returns the null specialization instance.
     *
     * @return The {@link Specialization#NULL} instance.
     */
    @Override
    public Specialization getNull() {
        return Specialization.NULL;
    }
}
