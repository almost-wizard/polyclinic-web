package ru.rsreu.gorobchenko.polyclinicweb.factory;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.ModelFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.MedicalCardHistory;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class MedicalCardHistoryFactory implements ModelFactory<MedicalCardHistory> {
    private static final String PARAM_DIAGNOSIS = "DIAGNOSIS";
    private static final String PARAM_THERAPY = "THERAPY";

    @Override
    public MedicalCardHistory fromResultSet(ResultSet resultSet, String parametersPrefix) throws SQLException, ParseException {
        Appointment appointment = new AppointmentFactory().fromResultSet(resultSet, "");
        String diagnosis = resultSet.getString(PARAM_DIAGNOSIS);
        String therapy = resultSet.getString(PARAM_THERAPY);
        return new MedicalCardHistory(appointment, diagnosis, therapy);
    }
}
