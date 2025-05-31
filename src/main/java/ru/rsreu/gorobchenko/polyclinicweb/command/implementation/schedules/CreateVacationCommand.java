package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.schedules;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.dao.VacationApplicationDao;
import ru.rsreu.gorobchenko.polyclinicweb.exception.SqlProcedureException;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.resource.MessageManager;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormat;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormatter;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

@RoleRequires(UserRole.DOCTOR)
public class CreateVacationCommand extends ActionCommand {
    public static final int HARDCODE_USER_ID = 4;
    public static final String PARAMETER_START_DATE = "startDate";
    public static final String PARAMETER_END_DATE = "endDate";

    public CreateVacationCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        VacationApplicationDao vacationApplicationDao = factory.getVacationApplicationDao();

        try {
            Date startDate = DateFormatter.parse(request.getParameter(PARAMETER_START_DATE), DateFormat.HTML_DATE);
            Date endDate = DateFormatter.parse(request.getParameter(PARAMETER_END_DATE), DateFormat.HTML_DATE);
            vacationApplicationDao.create(HARDCODE_USER_ID, startDate, endDate);
            redirect(Command.GET_ALL_VACATION_APPLICATIONS);
        } catch (SqlProcedureException e) {
            request.setAttribute("errorMessage", MessageManager.get("schedules.create.exists-weekend.error"));
            forward(Page.CREATE_VACATION_APPLICATION);
        } catch (ParseException e) {
            request.setAttribute("errorMessage", MessageManager.get("schedules.create.error"));
            forward(Page.CREATE_VACATION_APPLICATION);
        }
    }
}
