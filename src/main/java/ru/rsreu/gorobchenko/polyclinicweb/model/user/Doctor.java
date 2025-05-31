package ru.rsreu.gorobchenko.polyclinicweb.model.user;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.Nullable;
import ru.rsreu.gorobchenko.polyclinicweb.model.Specialization;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a doctor in the system, extending the {@link BaseUser} with a list of specializations.
 * This class provides methods to access the doctor's specializations and formats the full name to include them.
 * It also implements the {@link Nullable} interface, providing a null object pattern for doctor instances.
 */
public class Doctor extends BaseUser implements Nullable<Doctor> {
    private final List<Specialization> specializations;

    /**
     * A constant representing the null doctor instance.
     */
    public final static Doctor NULL = new Doctor(NullUser.getInstance(), List.of());

    /**
     * Constructs a new {@code Doctor} with the specified attributes.
     *
     * @param baseUser The {@link BaseUser} representing the doctor's base information.
     * @param specializations A list of the doctor's specializations.
     */
    public Doctor(BaseUser baseUser, List<Specialization> specializations) {
        super(baseUser);
        this.specializations = specializations;
    }

    /**
     * Returns the list of specializations of the doctor.
     *
     * @return The list of {@link Specialization} objects for the doctor.
     */
    public List<Specialization> getSpecializations() {
        return specializations;
    }

    /**
     * Returns a comma-separated string of the doctor's specializations.
     *
     * @return A string representation of the doctor's specializations.
     */
    public String getSpecializationsString() {
        return specializations.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    /**
     * Returns the full name of the doctor, including their specializations.
     *
     * @return The formatted full name of the doctor including specializations.
     */
    @Override
    public String getFullName() {
        return String.format("%s - %s", super.getFullName(), getSpecializationsString());
    }

    /**
     * Returns the null doctor instance.
     *
     * @return The {@link Doctor#NULL} instance.
     */
    @Override
    public Doctor getNull() {
        return Doctor.NULL;
    }
}
