package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.logic.UserLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;

@RoleRequires({UserRole.ADMINISTRATOR, UserRole.DOCTOR, UserRole.MODERATOR, UserRole.SYSTEM_ADMINISTRATOR})
public class GetProfileCommand extends ActionCommand {
    public GetProfileCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        request.setAttribute("user", UserLogic.fromRequestNoThrow(request));
        request.setAttribute("roles", UserRole.values());
        forward(Page.PROFILE);
    }
}
