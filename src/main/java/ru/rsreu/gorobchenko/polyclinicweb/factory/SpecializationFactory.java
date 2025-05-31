package ru.rsreu.gorobchenko.polyclinicweb.factory;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.ModelFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.Specialization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecializationFactory implements ModelFactory<Specialization> {
    public static final String PARAM_ID = "ID";
    public static final String PARAM_NAME = "NAME";

    @Override
    public Specialization fromResultSet(final ResultSet resultSet, String parametersPrefix) throws SQLException {
        int id = resultSet.getInt(PARAM_ID);
        String name = resultSet.getString(PARAM_NAME);
        return new Specialization(id, name);
    }
}
