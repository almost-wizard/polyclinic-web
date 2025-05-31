package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.schedules;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.DoctorDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.ScheduleDayDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.logic.SchedulesLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.ScheduleDay;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;
import java.util.List;

@RoleRequires({UserRole.DOCTOR, UserRole.ADMINISTRATOR})
public class GetSchedulesCommand extends ActionCommand {
    private static final String PARAM_NAME_DOCTOR_ID = "doctorId";

    public GetSchedulesCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        ScheduleDayDao scheduleDayDao = factory.getScheduleDayDao();
        DoctorDao doctorDao = factory.getDoctorDao();

        List<ScheduleDay> scheduleDays;
        Doctor doctor;
        try {
            int doctorId = Integer.parseInt(request.getParameter(PARAM_NAME_DOCTOR_ID));
            scheduleDays = scheduleDayDao.getAll(doctorId);
            doctor = doctorDao.getOne(doctorId);
        } catch (Exception e) {
            forward(Page.NOT_FOUND);
            return;
        }

        var weeklySchedule = SchedulesLogic.constructWeeklySchedules(scheduleDays);
        request.setAttribute("weeklySchedule", weeklySchedule);
        request.setAttribute("doctor", doctor);
        forward(Page.SCHEDULES);
    }
}
