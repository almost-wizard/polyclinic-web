package ru.rsreu.gorobchenko.polyclinicweb.dao.core;

import ru.rsreu.gorobchenko.polyclinicweb.dao.*;
import ru.rsreu.gorobchenko.polyclinicweb.dao.VacationApplicationDao;

public abstract class DaoFactory {
    public static DaoFactory getInstance(DbType dbType) {
        return dbType.getDAOFactory();
    }

    public abstract AppointmentDao getAppointmentDao();

    public abstract UserDao getUserDao();

    public abstract DoctorDao getDoctorDao();

    public abstract SpecializationDao getSpecializationDao();

    public abstract MedicalCardHistoryDao getMedicalCardHistoryDao();

    public abstract ScheduleDayDao getScheduleDayDao();

    public abstract VacationApplicationDao getVacationApplicationDao();
}
