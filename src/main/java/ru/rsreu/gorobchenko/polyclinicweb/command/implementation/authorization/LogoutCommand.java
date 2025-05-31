package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.authorization;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.logic.UserLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;

@RoleRequires({UserRole.ADMINISTRATOR, UserRole.DOCTOR, UserRole.MODERATOR, UserRole.SYSTEM_ADMINISTRATOR})
public class LogoutCommand extends ActionCommand {
    public LogoutCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        User user = UserLogic.fromRequestNoThrow(request);
        UserLogic.logout(user.getId());
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        redirect(Command.GET_LOGIN);
    }
}
