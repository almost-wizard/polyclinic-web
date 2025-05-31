package ru.rsreu.gorobchenko.polyclinicweb.factory;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.ModelFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.VacationApplication;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VacationApplicationFactory implements ModelFactory<VacationApplication> {

    public static final String PARAMETER_START_DATE = "START_DATE";
    public static final String PARAMETER_END_DATE = "END_DATE";
    public static final String PARAMETER_IS_APPROVED = "IS_APPROVED";

    @Override
    public VacationApplication fromResultSet(ResultSet resultSet, String parametersPrefix) throws SQLException {
        Date startDate = resultSet.getDate(PARAMETER_START_DATE);
        Date endDate = resultSet.getDate(PARAMETER_END_DATE);
        boolean isApproved = resultSet.getBoolean(PARAMETER_IS_APPROVED);
        Doctor doctor = new DoctorFactory().fromResultSet(resultSet, "");
        return new VacationApplication(startDate, endDate, isApproved, doctor);
    }
}
