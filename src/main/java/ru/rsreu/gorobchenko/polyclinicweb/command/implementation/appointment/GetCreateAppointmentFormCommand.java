package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.appointment;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.DoctorDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.NullUser;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;
import java.util.List;

@RoleRequires({UserRole.DOCTOR, UserRole.ADMINISTRATOR})
public class GetCreateAppointmentFormCommand extends ActionCommand {
    private static final String PARAM_NAME_DOCTOR_ID = "doctorId";
    public static final String PARAM_NAME_DATETIME = "datetime";

    public GetCreateAppointmentFormCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(PARAM_NAME_DOCTOR_ID));
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        DoctorDao doctorDao = factory.getDoctorDao();
        Doctor doctor = doctorDao.getOne(id);
        request.setAttribute("doctor", doctor);
        request.setAttribute("datetime", request.getParameter(PARAM_NAME_DATETIME));
        request.setAttribute("foundUsers", List.of());
        request.setAttribute("selectedUser", NullUser.getInstance());
        forward(Page.CREATE_APPOINTMENT);
    }
}
