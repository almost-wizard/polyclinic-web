package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.rsreu.gorobchenko.polyclinicweb.annotations.RoleRequires;
import ru.rsreu.gorobchenko.polyclinicweb.command.ActionCommand;
import ru.rsreu.gorobchenko.polyclinicweb.command.Page;
import ru.rsreu.gorobchenko.polyclinicweb.dao.UserDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;

import java.io.IOException;

@RoleRequires({UserRole.SYSTEM_ADMINISTRATOR})
public class GetDeleteUserPageCommand extends ActionCommand {
    private static final String PARAM_NAME_USER_ID = "userId";

    public GetDeleteUserPageCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter(PARAM_NAME_USER_ID));
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        UserDao userDao = factory.getUserDao();
        User user = userDao.getOne(userId);
        request.setAttribute("user", user);
        forward(Page.DELETE_USER);
    }
}