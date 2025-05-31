package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle;

import ru.rsreu.gorobchenko.polyclinicweb.dao.AppointmentDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.BaseOracleDao;
import ru.rsreu.gorobchenko.polyclinicweb.factory.AppointmentFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.NullAppointment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class OracleAppointmentDao extends BaseOracleDao<Appointment> implements AppointmentDao {
    public OracleAppointmentDao(Connection connection) {
        super(connection, NullAppointment.getInstance(), new AppointmentFactory());
    }

    @Override
    public Appointment getOne(int id) {
        return super.getOne("appointment.one.by.id", (PreparedStatement statement) -> {
            statement.setInt(1, id);
        });
    }

    @Override
    public List<Appointment> getAllConsiderWeekend(Date datetime, int doctorId) {
        return super.getAll("appointment.all.as-schedule", (PreparedStatement statement) -> {
            statement.setDate(1, datetime);
            statement.setDate(2, datetime);
            statement.setInt(3, doctorId);
            statement.setInt(4, doctorId);
        });
    }

    @Override
    public List<Appointment> getAll(Date datetime, int doctorId) {
        return super.getAll("appointment.all", (PreparedStatement statement) -> {
            statement.setInt(1, doctorId);
            statement.setDate(2, datetime);
        });
    }

    @Override
    public boolean delete(int id) {
        return super.update("appointment.delete", (PreparedStatement statement) -> {
            statement.setInt(1, id);
        });
    }

    @Override
    public boolean create(int patientId, int doctorId, Date datetime) {
        return super.update("appointment.create", (PreparedStatement statement) -> {
            statement.setInt(1, doctorId);
            statement.setInt(2, patientId);
            statement.setDate(3, datetime);
        });
    }
}
