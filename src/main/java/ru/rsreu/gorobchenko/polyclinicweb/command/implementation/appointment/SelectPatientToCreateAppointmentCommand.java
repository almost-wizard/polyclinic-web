package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.appointment;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.DoctorDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.UserDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;

@RoleRequires({UserRole.DOCTOR, UserRole.ADMINISTRATOR})
public class SelectPatientToCreateAppointmentCommand extends ActionCommand {
    private static final String PARAM_NAME_SELECTED_USER_ID = "selectedUserId";
    private static final String PARAM_NAME_DOCTOR_ID = "doctorId";
    private static final String PARAM_NAME_DATETIME = "datetime";

    public SelectPatientToCreateAppointmentCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_SELECTED_USER_ID));
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        UserDao userDao = factory.getUserDao();
        User results = userDao.getOne(userId);

        int id = Integer.parseInt(request.getParameter(PARAM_NAME_DOCTOR_ID));
        DoctorDao doctorDao = factory.getDoctorDao();
        Doctor doctor = doctorDao.getOne(id);
        request.setAttribute("selectedUser", results);
        request.setAttribute("doctor", doctor);
        request.setAttribute("datetime", request.getParameter(PARAM_NAME_DATETIME));
        forward(Page.CREATE_APPOINTMENT);
    }
}
