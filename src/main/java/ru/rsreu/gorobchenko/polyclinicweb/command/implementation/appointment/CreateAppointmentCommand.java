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
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormat;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormatter;

import java.io.IOException;
import java.sql.Date;

@RoleRequires({UserRole.DOCTOR, UserRole.ADMINISTRATOR})
public class CreateAppointmentCommand extends ActionCommand {
    public static final String PARAM_NAME_PATIENT_ID = "patientId";
    public static final String PARAM_NAME_DOCTOR_ID = "doctorId";
    public static final String PARAM_NAME_DATETIME = "datetime";

    public CreateAppointmentCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        AppointmentDao appointmentDao = factory.getAppointmentDao();
        boolean isCreated = false;
        try {
            int patientId = Integer.parseInt(request.getParameter(PARAM_NAME_PATIENT_ID));
            int doctorId = Integer.parseInt(request.getParameter(PARAM_NAME_DOCTOR_ID));
            Date datetime = DateFormatter.parse(request.getParameter(PARAM_NAME_DATETIME), DateFormat.FULL);
            isCreated = appointmentDao.create(patientId, doctorId, datetime);
        } catch (Exception ignored) {

        }

        if (!isCreated) {
            request.setAttribute("errorMessage", MessageManager.get("user.create.error"));
            redirect(Command.CREATE_APPOINTMENT);
        } else {
            redirect(Command.GET_ALL_DOCTORS);
        }
    }
}
