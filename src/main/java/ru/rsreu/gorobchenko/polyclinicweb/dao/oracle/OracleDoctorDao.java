package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle;

import ru.rsreu.gorobchenko.polyclinicweb.dao.DoctorDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.BaseOracleDao;
import ru.rsreu.gorobchenko.polyclinicweb.factory.DoctorFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class OracleDoctorDao extends BaseOracleDao<Doctor> implements DoctorDao {
    public OracleDoctorDao(Connection connection) {
        super(connection, Doctor.NULL, new DoctorFactory());
    }

    @Override
    public Doctor getOne(int id) {
        return super.getOne("doctor.one.by.id", (PreparedStatement statement) -> {
            statement.setInt(1, id);
        });
    }

    @Override
    public List<Doctor> getAll() {
        return super.getAll("doctors.all");
    }

    @Override
    public boolean setSpecialization(int doctorId, int specializationId) {
        return super.update("doctors.set-specialization", (PreparedStatement statement) -> {
            statement.setInt(1, doctorId);
            statement.setInt(2, specializationId);
        });
    }
}
