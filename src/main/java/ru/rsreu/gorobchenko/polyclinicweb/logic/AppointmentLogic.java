package ru.rsreu.gorobchenko.polyclinicweb.logic;

import ru.rsreu.gorobchenko.polyclinicweb.dao.AppointmentDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormat;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormatter;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

public class AppointmentLogic {
    public static List<Appointment> getAll(int doctorId, String dateParam) throws ParseException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        AppointmentDao appointmentDao = factory.getAppointmentDao();

        Date date = new Date(System.currentTimeMillis());
        if (dateParam != null) {
            date = DateFormatter.parse(dateParam, DateFormat.HTML_DATE);
        }
        return appointmentDao.getAllConsiderWeekend(date, doctorId);
    }
}
