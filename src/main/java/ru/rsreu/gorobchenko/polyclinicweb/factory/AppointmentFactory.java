package ru.rsreu.gorobchenko.polyclinicweb.factory;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.ModelFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.EmptyAppointment;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormat;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormatter;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class AppointmentFactory implements ModelFactory<Appointment> {
    public static final String PARAM_APPOINTMENT_ID = "APPOINTMENT_ID";
    public static final String PARAM_DATETIME = "DATETIME";

    @Override
    public Appointment fromResultSet(ResultSet resultSet, String parametersPrefix) throws SQLException, ParseException {
        if (resultSet.getObject(AppointmentFactory.PARAM_APPOINTMENT_ID) == null) {
            return AppointmentFactory.emptyFromResultSet(resultSet);
        }
        return AppointmentFactory.fullFilledFromResultSet(resultSet);
    }

    private static Appointment fullFilledFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        int appointmentId = resultSet.getInt(PARAM_APPOINTMENT_ID);
        Date datetime = DateFormatter.parse(resultSet.getString(PARAM_DATETIME), DateFormat.FULL);
        User doctor = new UserFactory().fromResultSet(resultSet, "DOCTOR_");
        User patient = new UserFactory().fromResultSet(resultSet, "PATIENT_");
        return new Appointment(appointmentId, doctor, patient, datetime);
    }

    private static EmptyAppointment emptyFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        Date datetime = DateFormatter.parse(resultSet.getString(PARAM_DATETIME), DateFormat.FULL);
        return new EmptyAppointment(datetime);
    }
}
