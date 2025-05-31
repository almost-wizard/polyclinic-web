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
import ru.rsreu.gorobchenko.polyclinicweb.logic.AppointmentLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.Doctor;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;
import java.util.List;

@RoleRequires({UserRole.DOCTOR, UserRole.ADMINISTRATOR})
public class GetAllAppointmentsCommand extends ActionCommand {
    private static final String PARAM_NAME_DOCTOR_ID = "doctorId";
    private static final String PARAM_NAME_DATE = "date";

    public GetAllAppointmentsCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        DoctorDao doctorDao = factory.getDoctorDao();

        Doctor doctor;
        List<Appointment> appointments;
        try {
            int doctorId = Integer.parseInt(request.getParameter(PARAM_NAME_DOCTOR_ID));
            String dateParam = request.getParameter(PARAM_NAME_DATE);
            appointments = AppointmentLogic.getAll(doctorId, dateParam);
            doctor = doctorDao.getOne(doctorId);
        } catch (Exception e) {
            forward(Page.NOT_FOUND);
            return;
        }

        request.setAttribute("doctor", doctor);
        request.setAttribute("appointments", appointments);
        forward(Page.ALL_APPOINTMENTS);
    }
}
