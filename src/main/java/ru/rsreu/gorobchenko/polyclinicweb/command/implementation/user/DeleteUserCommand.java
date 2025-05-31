package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Command;
import ru.rsreu.gorobchenko.polyclinicweb.dao.UserDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.logic.UserLogic;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.resource.MessageManager;

import java.io.IOException;

@RoleRequires({UserRole.SYSTEM_ADMINISTRATOR})
public class DeleteUserCommand extends ActionCommand {
    private static final String PARAM_NAME_ID = "userId";

    public DeleteUserCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));

        if (id == UserLogic.fromRequestNoThrow(request).getId()) {
            redirect(Command.GET_ALL_USERS);
            return;
        }

        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        UserDao userDao = factory.getUserDao();

        boolean isDeleted = userDao.delete(id);
        if (!isDeleted) {
            request.setAttribute("errorMessage", MessageManager.get("user.delete.error"));
            redirect(Command.GET_DELETE_USER_FORM);
        } else {
            redirect(Command.GET_ALL_USERS);
        }
    }
}
