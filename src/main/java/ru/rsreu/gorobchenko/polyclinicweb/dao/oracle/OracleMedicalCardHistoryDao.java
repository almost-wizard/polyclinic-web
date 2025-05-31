package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle;

import ru.rsreu.gorobchenko.polyclinicweb.dao.MedicalCardHistoryDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.BaseOracleDao;
import ru.rsreu.gorobchenko.polyclinicweb.factory.MedicalCardHistoryFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.MedicalCardHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class OracleMedicalCardHistoryDao extends BaseOracleDao<MedicalCardHistory> implements MedicalCardHistoryDao {
    public OracleMedicalCardHistoryDao(Connection connection) {
        super(connection, MedicalCardHistory.NULL, new MedicalCardHistoryFactory());
    }

    @Override
    public List<MedicalCardHistory> getAll(int patientId) {
        return super.getAll("medical-card-history.all", (PreparedStatement statement) -> {
            statement.setInt(1, patientId);
        });
    }

    @Override
    public boolean create(int appointmentId, String diagnosis, String therapy) {
        return super.update("medical-card-history.create", (PreparedStatement statement) -> {
            statement.setInt(1, appointmentId);
            statement.setInt(2, appointmentId);
            statement.setString(3, diagnosis);
            statement.setString(4, therapy);
        });
    }
}
