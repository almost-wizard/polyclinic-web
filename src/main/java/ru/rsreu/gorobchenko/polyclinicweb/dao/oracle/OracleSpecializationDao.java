package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle;

import ru.rsreu.gorobchenko.polyclinicweb.dao.SpecializationDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.BaseOracleDao;
import ru.rsreu.gorobchenko.polyclinicweb.factory.SpecializationFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.Specialization;

import java.sql.Connection;
import java.util.List;

public class OracleSpecializationDao extends BaseOracleDao<Specialization> implements SpecializationDao {
    public OracleSpecializationDao(Connection connection) {
        super(connection, Specialization.NULL, new SpecializationFactory());
    }

    @Override
    public List<Specialization> getAll() {
        return super.getAll("specializations.all");
    }
}
