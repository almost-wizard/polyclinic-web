package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.authorization;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.exception.user.UserBlockedException;
import ru.rsreu.gorobchenko.polyclinicweb.exception.user.UserNotFoundException;
import ru.rsreu.gorobchenko.polyclinicweb.exception.user.UserPatientException;
import ru.rsreu.gorobchenko.polyclinicweb.logic.UserLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.resource.MessageManager;

import java.io.IOException;

public class LoginCommand extends ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public LoginCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        try {
            User user = UserLogic.login(login, password);
            HttpSession session = request.getSession(true);
            User current = UserLogic.fromSession(session);
            if (current != null) {
                UserLogic.logout(current.getId());
            }
            session.setAttribute("user", user);
            redirect(Command.GET_PROFILE);
        } catch (UserNotFoundException e) {
            request.setAttribute("errorMessage", MessageManager.get("user.login.error"));
            forward(Page.LOGIN);
        } catch (UserBlockedException e) {
            request.setAttribute("errorMessage", MessageManager.get("user.blocked.error"));
            forward(Page.LOGIN);
        } catch (UserPatientException e) {
            request.setAttribute("errorMessage", MessageManager.get("user.login.patient"));
            forward(Page.LOGIN);
        }
    }
}
