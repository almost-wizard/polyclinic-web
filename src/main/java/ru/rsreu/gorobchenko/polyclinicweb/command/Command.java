package ru.rsreu.gorobchenko.polyclinicweb.command;

import ru.rsreu.gorobchenko.polyclinicweb.command.implementation.GetAllDoctorsCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.implementation.appointment.*;
import ru.rsreu.gorobchenko.polyclinicweb.command.implementation.authorization.*;
import ru.rsreu.gorobchenko.polyclinicweb.command.implementation.error.*;
import ru.rsreu.gorobchenko.polyclinicweb.command.implementation.medical_card.*;
import ru.rsreu.gorobchenko.polyclinicweb.command.implementation.schedules.*;
import ru.rsreu.gorobchenko.polyclinicweb.command.implementation.user.*;

public enum Command {
    // Errors
    GET_401(Get401Command.class),
    GET_403(Get403Command.class),
    GET_404(Get404Command.class),

    // Authorization
    GET_LOGIN(GetLoginCommand.class),
    LOGIN(LoginCommand.class),
    GET_LOGOUT(GetLogoutCommand.class),
    LOGOUT(LogoutCommand.class),

    // User
    GET_PROFILE(GetProfileCommand.class),
    GET_ALL_USERS(GetAllUsersCommand.class),
    GET_CREATE_USER_FORM(GetCreateUserFormCommand.class),
    CREATE_USER(CreateUserCommand.class),
    GET_EDIT_USER_FORM(GetEditUserFormCommand.class),
    EDIT_USER(EditUserCommand.class),
    GET_DELETE_USER_FORM(GetDeleteUserPageCommand.class),
    DELETE_USER(DeleteUserCommand.class),
    BLOCK_USER(BlockUserCommand.class),
    UNBLOCK_USER(UnblockUserCommand.class),

    // Doctor
    GET_ALL_DOCTORS(GetAllDoctorsCommand.class),

    // Appointment
    GET_ALL_APPOINTMENTS(GetAllAppointmentsCommand.class),
    GET_DELETE_APPOINTMENT_FORM(GetDeleteAppointmentFormCommand.class),
    DELETE_APPOINTMENT(DeleteAppointmentCommand.class),
    GET_CREATE_APPOINTMENT_FORM(GetCreateAppointmentFormCommand.class),
    SEARCH_PATIENT_TO_CREATE_APPOINTMENT(SearchPatientToCreateAppointmentCommand.class),
    SELECT_PATIENT_TO_CREATE_APPOINTMENT(SelectPatientToCreateAppointmentCommand.class),
    CREATE_APPOINTMENT(CreateAppointmentCommand.class),

    // Medical card history
    GET_MEDICAL_CARD_HISTORY(GetMedicalCardHistoryCommand.class),
    GET_CREATE_MEDICAL_CARD_HISTORY_FORM(GetCreateMedicalCardHistoryFormCommand.class),
    CREATE_MEDICAL_CARD_HISTORY(CreateMedicalCardHistoryCommand.class),

    // Schedules
    GET_SCHEDULES(GetSchedulesCommand.class),
    EDIT_SCHEDULE(EditScheduleCommand.class),
    GET_ALL_VACATION_APPLICATIONS(GetAllVacationApplicationsCommand.class),
    APPROVE_VACATION(ApproveVacationCommand.class),
    REJECT_VACATION(RejectVacationCommand.class),
    GET_CREATE_VACATION_FORM(GetCreateVacationFormCommand.class),
    CREATE_VACATION(CreateVacationCommand.class),
    ;

    public static final String COMMAND_URL_PATTERN = "?command=%s";

    private final Class<? extends ActionCommand> command;

    Command(Class<? extends ActionCommand> command) {
        this.command = command;
    }

    public Class<? extends ActionCommand> getCurrentCommand() {
        return command;
    }

    public String constructUrl() {
        return String.format(COMMAND_URL_PATTERN, this.name().toLowerCase());
    }
}
