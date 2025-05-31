package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.medical_card;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.MedicalCardHistoryDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.UserDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.MedicalCardHistory;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.NullUser;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RoleRequires(UserRole.DOCTOR)
public class GetMedicalCardHistoryCommand extends ActionCommand {
    public static final String PARAM_PATIENT_ID = "patientId";

    public GetMedicalCardHistoryCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        MedicalCardHistoryDao medicalCardHistoryDao = factory.getMedicalCardHistoryDao();
        UserDao userDao = factory.getUserDao();

        List<MedicalCardHistory> medicalCardHistoryList = new ArrayList<>();
        User patient = NullUser.getInstance();
        try {
            int patientId = Integer.parseInt(request.getParameter(PARAM_PATIENT_ID));
            medicalCardHistoryList = medicalCardHistoryDao.getAll(patientId);
            patient = userDao.getOne(patientId);
        } catch (Exception e) {
            forward(Page.NOT_FOUND);
            return;
        }

        request.setAttribute("patient", patient);
        request.setAttribute("medicalCardHistoryList", medicalCardHistoryList);
        forward(Page.MEDICAL_CARD_HISTORY);
    }
}
