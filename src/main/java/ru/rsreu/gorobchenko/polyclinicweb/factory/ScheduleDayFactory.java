package ru.rsreu.gorobchenko.polyclinicweb.factory;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.ModelFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.ScheduleDay;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ScheduleDayFactory implements ModelFactory<ScheduleDay> {
    private static final String PARAM_DATE = "DATE";
    private static final String PARAM_IS_VACATION = "IS_VACATION";

    @Override
    public ScheduleDay fromResultSet(ResultSet resultSet, String parametersPrefix) throws SQLException, ParseException {
        Date date = resultSet.getDate(PARAM_DATE);
        boolean isVacation = resultSet.getInt(PARAM_IS_VACATION) > 0;
        return new ScheduleDay(date, !isVacation);
    }
}
