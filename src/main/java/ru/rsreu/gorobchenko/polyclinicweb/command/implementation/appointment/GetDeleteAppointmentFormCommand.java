package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.appointment;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.AppointmentDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.DoctorDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;

@RoleRequires({UserRole.DOCTOR, UserRole.ADMINISTRATOR})
public class GetDeleteAppointmentFormCommand extends ActionCommand {
    private static final String PARAM_NAME_APPOINTMENT_ID = "appointmentId";

    public GetDeleteAppointmentFormCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(PARAM_NAME_APPOINTMENT_ID));
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        AppointmentDao appointmentDao = factory.getAppointmentDao();
        Appointment appointment = appointmentDao.getOne(id);
        DoctorDao doctorDao = factory.getDoctorDao();
        Doctor doctor = doctorDao.getOne(appointment.getDoctor().getId());
        request.setAttribute("appointment", appointment);
        request.setAttribute("doctor", doctor);
        forward(Page.DELETE_APPOINTMENT);
    }
}
