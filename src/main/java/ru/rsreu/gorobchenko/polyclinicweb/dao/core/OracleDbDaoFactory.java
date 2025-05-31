package ru.rsreu.gorobchenko.polyclinicweb.dao.core;

import ru.rsreu.gorobchenko.polyclinicweb.dao.*;
import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.*;
import ru.rsreu.gorobchenko.polyclinicweb.resource.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class OracleDbDaoFactory extends DaoFactory {
    private static volatile OracleDbDaoFactory instance;
    private Connection connection;

    private OracleDbDaoFactory() {
    }

    public static OracleDbDaoFactory getInstance() throws ClassNotFoundException, SQLException {
        OracleDbDaoFactory factory = instance;
        if (instance == null) {
            synchronized (OracleDbDaoFactory.class) {
                factory = new OracleDbDaoFactory();
                instance = factory;
                factory.connected();
            }
        }
        return factory;
    }

    private void connected() throws ClassNotFoundException, SQLException {
        Locale.setDefault(Locale.ENGLISH);
        String url = ConfigurationManager.get("db.url");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        this.connection = DriverManager.getConnection(url);
    }

    @Override
    public AppointmentDao getAppointmentDao() {
        return new OracleAppointmentDao(this.connection);
    }

    @Override
    public UserDao getUserDao() {
        return new OracleUserDao(this.connection);
    }

    @Override
    public DoctorDao getDoctorDao() {
        return new OracleDoctorDao(this.connection);
    }

    @Override
    public SpecializationDao getSpecializationDao() {
        return new OracleSpecializationDao(this.connection);
    }

    @Override
    public MedicalCardHistoryDao getMedicalCardHistoryDao() {
        return new OracleMedicalCardHistoryDao(this.connection);
    }

    @Override
    public ScheduleDayDao getScheduleDayDao() {
        return new OracleScheduleDayDao(this.connection);
    }

    @Override
    public VacationApplicationDao getVacationApplicationDao() {
        return new OracleVacationApplicationDao(this.connection);
    }
}
