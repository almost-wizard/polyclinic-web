package ru.rsreu.gorobchenko.polyclinicweb.logic;

import ru.rsreu.gorobchenko.polyclinicweb.dao.AppointmentDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.ScheduleDayDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.exception.DoctorAppointmentExistsException;
import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.ScheduleDay;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SchedulesLogic {
    public static List<List<ScheduleDay>> constructWeeklySchedules(List<ScheduleDay> scheduleDays) {
        List<List<ScheduleDay>> weeklySchedule = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate mondayDate = today.with(DayOfWeek.MONDAY);

        List<ScheduleDay> currentWeek = new ArrayList<>();

        LocalDate firstDate = scheduleDays.get(0).getDate().toLocalDate();
        int diffDays = firstDate.getDayOfWeek().getValue() - mondayDate.getDayOfWeek().getValue();
        for (int i = 0; i < diffDays; i++) {
            scheduleDays.add(0, ScheduleDay.NULL);
        }

        for (ScheduleDay scheduleDay : scheduleDays) {
            currentWeek.add(scheduleDay);

            if (scheduleDay.getDate().toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY && !scheduleDay.isNull()) {
                weeklySchedule.add(currentWeek);
                currentWeek = new ArrayList<>();
            }
        }
        if (!currentWeek.isEmpty()) {
            weeklySchedule.add(currentWeek);
        }

        return weeklySchedule;
    }

    public static void updateScheduleDay(boolean isVacation, boolean isWorkday, int doctorId, Date date) throws DoctorAppointmentExistsException {
        if (SchedulesLogic.hasDoctorAnyAppointments(date, doctorId)) {
            throw new DoctorAppointmentExistsException();
        }

        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        ScheduleDayDao scheduleDayDao = factory.getScheduleDayDao();

        if (isVacation) {
            scheduleDayDao.setVacationDay(doctorId, date);
        } else if (isWorkday) {
            scheduleDayDao.setWorkDay(doctorId, date);
        }
    }

    public static boolean hasDoctorAnyAppointments(Date date, int doctorId) {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        AppointmentDao appointmentDao = factory.getAppointmentDao();
        return !appointmentDao.getAll(date, doctorId).isEmpty();
    }
}
