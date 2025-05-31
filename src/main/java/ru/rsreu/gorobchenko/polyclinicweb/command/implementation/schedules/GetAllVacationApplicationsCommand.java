package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.schedules;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.dao.VacationApplicationDao;
import ru.rsreu.gorobchenko.polyclinicweb.model.schedules.VacationApplication;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;
import java.util.List;

@RoleRequires({UserRole.DOCTOR, UserRole.ADMINISTRATOR})
public class GetAllVacationApplicationsCommand extends ActionCommand {
    public GetAllVacationApplicationsCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        VacationApplicationDao vacationApplicationDao = factory.getVacationApplicationDao();
        List<VacationApplication> applications = vacationApplicationDao.getAll();

        request.setAttribute("applications", applications);
        forward(Page.ALL_VACATION_APPLICATIONS);
    }
}
