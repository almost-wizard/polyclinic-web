package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.medical_card;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.dao.MedicalCardHistoryDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.resource.MessageManager;

import java.io.IOException;

@RoleRequires(UserRole.DOCTOR)
public class CreateMedicalCardHistoryCommand extends ActionCommand {
    private static final String PARAM_APPOINTMENT_ID = "appointmentId";
    private static final String PARAM_DIAGNOSIS = "diagnosis";
    private static final String PARAM_THERAPY = "therapy";

    public CreateMedicalCardHistoryCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        MedicalCardHistoryDao medicalCardHistoryDao = factory.getMedicalCardHistoryDao();

        boolean isCreated = false;
        try {
            int appointmentId = Integer.parseInt(request.getParameter(PARAM_APPOINTMENT_ID));
            String diagnosis = request.getParameter(PARAM_DIAGNOSIS);
            String therapy = request.getParameter(PARAM_THERAPY);
            isCreated = medicalCardHistoryDao.create(appointmentId, diagnosis, therapy);
        } catch (Exception ignored) {

        }

        if (!isCreated) {
            request.setAttribute("errorMessage", MessageManager.get("medical-card-history.create.error"));
            redirect(Command.GET_CREATE_MEDICAL_CARD_HISTORY_FORM);
        } else {
            redirect(Command.GET_ALL_DOCTORS);
        }
    }
}
