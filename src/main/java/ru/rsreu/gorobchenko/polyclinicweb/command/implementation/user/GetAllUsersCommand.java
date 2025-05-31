package ru.rsreu.gorobchenko.polyclinicweb.command.implementation.user;

import jakarta.servlet.ServletContext;
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
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserState;

import java.io.IOException;
import java.util.List;

@RoleRequires({UserRole.MODERATOR, UserRole.SYSTEM_ADMINISTRATOR})
public class GetAllUsersCommand extends ActionCommand {
    private static final String PARAM_USER_STATUS = "status";

    public GetAllUsersCommand(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void execute() throws ServletException, IOException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        UserDao userDao = factory.getUserDao();
        UserState state = UserState.construct(request.getParameter(PARAM_USER_STATUS));

        List<User> users;
        if (state != null) {
            users = userDao.getAll(state);
        } else {
            users = userDao.getAll();
        }
        request.setAttribute("users", users);
        forward(Page.ALL_USERS);
    }
}
