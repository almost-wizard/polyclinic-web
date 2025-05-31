package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionFactory;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.exception.user.UserExistsException;
import ru.rsreu.gorobchenko.polyclinicweb.logic.UserLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.resource.MessageManager;

import java.io.IOException;
import java.sql.Date;

@RoleRequires({UserRole.SYSTEM_ADMINISTRATOR})
public class CreateUserCommand extends ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_PATRONYMIC = "patronymic";
    private static final String PARAM_NAME_ROLE = "role";
    private static final String PARAM_NAME_SPECIALIZATION = "specialization";
    private static final String PARAM_NAME_IS_BLOCKED = "isBlocked";

    public CreateUserCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        try {
            User user = prepareUser(request);
            int specializationId = 0;
            if (user.getRole().isDoctor()) {
                specializationId = Integer.parseInt(request.getParameter(PARAM_NAME_SPECIALIZATION));
            }

            UserLogic.create(user, specializationId);
        } catch (UserExistsException ignored) {
            request.setAttribute("errorMessage", MessageManager.get("user.create.exists.error"));
            ActionFactory.reflexConstructCommand(Command.GET_CREATE_USER_FORM, request, response).execute();
            return;
        } catch (Exception ignored) {
            request.setAttribute("errorMessage", MessageManager.get("user.create.error"));
            ActionFactory.reflexConstructCommand(Command.GET_CREATE_USER_FORM, request, response).execute();
            return;
        }

        redirect(Command.GET_ALL_USERS);
    }

    public static User prepareUser(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String surname = request.getParameter(PARAM_NAME_SURNAME);
        String name = request.getParameter(PARAM_NAME_NAME);
        String patronymic = request.getParameter(PARAM_NAME_PATRONYMIC);
        UserRole role = UserRole.construct(Integer.parseInt(request.getParameter(PARAM_NAME_ROLE)));
        boolean isBlocked = Boolean.parseBoolean(request.getParameter(PARAM_NAME_IS_BLOCKED));

        return new User(0, login, password, surname, name, patronymic, role.getDatabaseRoleId(), new Date(System.currentTimeMillis()), isBlocked, false);
    }
}
