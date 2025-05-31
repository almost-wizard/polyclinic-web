package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.schedules;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.dao.VacationApplicationDao;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.resource.MessageManager;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormat;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormatter;

import java.io.IOException;
import java.sql.Date;

@RoleRequires(UserRole.ADMINISTRATOR)
public class RejectVacationCommand extends ActionCommand {
    public static final String PARAMETER_DOCTOR_ID = "doctorId";
    public static final String PARAMETER_START_DATE = "startDate";
    public static final String PARAMETER_END_DATE = "endDate";

    public RejectVacationCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        VacationApplicationDao vacationApplicationDao = factory.getVacationApplicationDao();

        boolean isRejected = false;
        try {
            int doctorId = Integer.parseInt(request.getParameter(PARAMETER_DOCTOR_ID));
            Date startDate = DateFormatter.parse(request.getParameter(PARAMETER_START_DATE), DateFormat.HTML_DATE);
            Date endDate = DateFormatter.parse(request.getParameter(PARAMETER_END_DATE), DateFormat.HTML_DATE);
            isRejected = vacationApplicationDao.reject(doctorId, startDate, endDate);
        } catch (Exception ignored) {

        }

        if (!isRejected) {
            request.setAttribute("errorMessage", MessageManager.get("schedules.reject.error"));
        }

        redirect(Command.GET_ALL_VACATION_APPLICATIONS);
    }
}
