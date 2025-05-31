package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.appointment;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.dao.AppointmentDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.resource.MessageManager;

import java.io.IOException;

@RoleRequires({UserRole.DOCTOR, UserRole.ADMINISTRATOR})
public class DeleteAppointmentCommand extends ActionCommand {
    private static final String PARAM_NAME_ID = "appointmentId";

    public DeleteAppointmentCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));

        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        AppointmentDao appointmentDao = factory.getAppointmentDao();

        boolean isDeleted = appointmentDao.delete(id);
        if (!isDeleted) {
            request.setAttribute("errorMessage", MessageManager.get("appointment.delete.error"));
            redirect(Command.GET_DELETE_APPOINTMENT_FORM);
        } else {
            redirect(Command.GET_ALL_DOCTORS);
        }
    }
}
