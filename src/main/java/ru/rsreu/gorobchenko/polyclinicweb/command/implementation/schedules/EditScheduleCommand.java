package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.schedules;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionFactory;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.exception.DoctorAppointmentExistsException;
import ru.rsreu.gorobchenko.polyclinicweb.logic.SchedulesLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.resource.MessageManager;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormat;
import ru.rsreu.gorobchenko.polyclinicweb.service.DateFormatter;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

@RoleRequires(UserRole.ADMINISTRATOR)
public class EditScheduleCommand extends ActionCommand {
    public static final String PARAMETER_DOCTOR_ID = "doctorId";
    public static final String PARAMETER_DATE = "date";
    public static final String PARAMETER_VACATION = "vacation";
    public static final String PARAMETER_WORKDAY = "workday";

    public EditScheduleCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        try {
            int doctorId = Integer.parseInt(request.getParameter(PARAMETER_DOCTOR_ID));
            Date date = DateFormatter.parse(request.getParameter(PARAMETER_DATE), DateFormat.HTML_DATE);
            boolean isVacation = request.getParameter(PARAMETER_VACATION) != null;
            boolean isWorkday = request.getParameter(PARAMETER_WORKDAY) != null;
            SchedulesLogic.updateScheduleDay(isVacation, isWorkday, doctorId, date);
        } catch (DoctorAppointmentExistsException ignored) {
            request.setAttribute("errorMessage", MessageManager.get("schedules.edit.appointment-exists.error"));
            ActionFactory.reflexConstructCommand(Command.GET_SCHEDULES, request, response).execute();
            return;
        } catch (ParseException e) {
            request.setAttribute("errorMessage", MessageManager.get("schedules.edit.error"));
            ActionFactory.reflexConstructCommand(Command.GET_SCHEDULES, request, response).execute();
            return;
        }

        redirect(Command.GET_ALL_DOCTORS);
    }
}
