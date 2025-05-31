package ru.rsreu.gorobchenko.polyclinicweb.factory;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.ModelFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;
import ru.rsreu.gorobchenko.polyclinicweb.model.Specialization;
import ru.rsreu.gorobchenko.polyclinicweb.service.SpecializationsManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DoctorFactory implements ModelFactory<Doctor> {
    public static final String PARAM_SPECIALIZATIONS = "SPECIALIZATIONS";

    @Override
    public Doctor fromResultSet(final ResultSet resultSet, String parametersPrefix) throws SQLException {
        String specializationsIds = resultSet.getString(PARAM_SPECIALIZATIONS);
        List<Specialization> specializations = SpecializationsManager.getInstance().fromIdsString(specializationsIds);
        return new Doctor(new UserFactory().fromResultSet(resultSet, ""), specializations);
    }
}
