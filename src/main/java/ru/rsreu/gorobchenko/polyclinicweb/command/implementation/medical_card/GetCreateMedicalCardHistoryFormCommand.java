package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.medical_card;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.AppointmentDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.Appointment;
import ru.rsreu.gorobchenko.polyclinicweb.model.appointment.NullAppointment;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;

@RoleRequires(UserRole.DOCTOR)
public class GetCreateMedicalCardHistoryFormCommand extends ActionCommand {
    private static final String PARAM_APPOINTMENT_ID = "appointmentId";

    public GetCreateMedicalCardHistoryFormCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        AppointmentDao appointmentDao = factory.getAppointmentDao();

        Appointment appointment = NullAppointment.getInstance();
        try {
            int appointmentId = Integer.parseInt(request.getParameter(PARAM_APPOINTMENT_ID));
            appointment = appointmentDao.getOne(appointmentId);
        } catch (Exception e) {
            forward(Page.NOT_FOUND);
            return;
        }

        request.setAttribute("appointment", appointment);
        forward(Page.CREATE_MEDICAL_CARD_HISTORY);
    }
}
