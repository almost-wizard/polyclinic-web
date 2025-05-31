package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle;

import ru.rsreu.gorobchenko.polyclinicweb.dao.ScheduleDayDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.BaseOracleDao;
import ru.rsreu.gorobchenko.polyclinicweb.factory.ScheduleDayFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.ScheduleDay;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class OracleScheduleDayDao extends BaseOracleDao<ScheduleDay> implements ScheduleDayDao {
    public OracleScheduleDayDao(Connection connection) {
        super(connection, ScheduleDay.NULL, new ScheduleDayFactory());
    }

    @Override
    public List<ScheduleDay> getAll(int doctorId) {
        return super.getAll("schedule-days.all", (PreparedStatement statement) -> {
            statement.setInt(1, doctorId);
        });
    }

    @Override
    public boolean setVacationDay(int doctorId, Date date) {
        return super.update("schedule-days.set-vacation", (PreparedStatement statement) -> {
            statement.setInt(1, doctorId);
            statement.setDate(2, date);
        });
    }

    @Override
    public boolean setWorkDay(int doctorId, Date date) {
        return super.update("schedule-days.set-working-day", (PreparedStatement statement) -> {
            statement.setInt(1, doctorId);
            statement.setDate(2, date);
        });
    }
}
