package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle;

import ru.rsreu.gorobchenko.polyclinicweb.dao.VacationApplicationDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.BaseOracleDao;
import ru.rsreu.gorobchenko.polyclinicweb.exception.SqlProcedureException;
import ru.rsreu.gorobchenko.polyclinicweb.factory.VacationApplicationFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.VacationApplication;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class OracleVacationApplicationDao extends BaseOracleDao<VacationApplication> implements VacationApplicationDao {
    public OracleVacationApplicationDao(Connection connection) {
        super(connection, VacationApplication.NULL, new VacationApplicationFactory());
    }

    @Override
    public List<VacationApplication> getAll() {
        return super.getAll("schedule-appointments.all");
    }

    @Override
    public boolean approve(int doctorId, Date startDate, Date endDate) {
        return super.update("schedule-appointments.approve", (PreparedStatement statement) -> {
            statement.setInt(1, doctorId);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
        });
    }

    @Override
    public boolean reject(int doctorId, Date startDate, Date endDate) {
        return super.update("schedule-appointments.reject", (PreparedStatement statement) -> {
            statement.setInt(1, doctorId);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
        });
    }

    @Override
    public void create(int doctorId, Date startDate, Date endDate) throws SqlProcedureException {
        super.execute("schedule-appointments.create", (CallableStatement statement) -> {
            statement.setInt(1, doctorId);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
        });
    }
}
