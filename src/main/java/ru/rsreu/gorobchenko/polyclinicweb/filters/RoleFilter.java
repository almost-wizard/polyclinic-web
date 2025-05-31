package ru.rsreu.gorobchenko.polyclinicweb.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.exception.user.UserBlockedException;
import ru.rsreu.gorobchenko.polyclinicweb.exception.user.UserUnAuthorizedException;
import ru.rsreu.gorobchenko.polyclinicweb.logic.UserLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;

import static ru.rsreu.gorobchenko.polyclinicweb.command.ActionFactory.PARAMETER_COMMAND;

public class RoleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String action = request.getParameter(PARAMETER_COMMAND);

        try {
            Command currentEnum = Command.valueOf(action.toUpperCase());
            Class<? extends ActionCommand> commandClassName = currentEnum.getCurrentCommand();

            if (!hasRights(commandClassName, httpRequest)) {
                ActionCommand.forward(Page.FORBIDDEN, httpRequest, httpResponse);
                return;
            }
        } catch (UserUnAuthorizedException | IllegalArgumentException e) {
            ActionCommand.forward(Page.FORBIDDEN, httpRequest, httpResponse);
            return;
        } catch (UserBlockedException e) {
            httpRequest.getSession().invalidate();
            ActionCommand.forward(Page.FORBIDDEN, httpRequest, httpResponse);
            return;
        }

        chain.doFilter(request, response);
    }

    private static boolean hasRights(Class<? extends ActionCommand> commandClassName, HttpServletRequest request) throws UserUnAuthorizedException, UserBlockedException {
        RoleRequires annotation = commandClassName.getAnnotation(RoleRequires.class);
        if (annotation == null) {
            return true;
        }

        UserRole[] requiredRoles = annotation.value();
        if (requiredRoles == null || requiredRoles.length == 0) {
            return true;
        }

        User user = UserLogic.fromRequest(request);

        for (UserRole requiredRole : requiredRoles) {
            if (user.getRole() == requiredRole) {
                return true;
            }
        }

        return false;
    }
}
