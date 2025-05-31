package ru.rsreu.gorobchenko.polyclinicweb.logic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ru.rsreu.gorobchenko.polyclinicweb.dao.DoctorDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.UserDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DaoFactory;
import ru.rsreu.gorobchenko.polyclinicweb.dao.core.DbType;
import ru.rsreu.gorobchenko.polyclinicweb.exception.user.*;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.NullUser;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;

public class UserLogic {
    public static final String PARAMETER_USER = "user";

    public static User login(String login, String password) throws UserNotFoundException, UserBlockedException, UserPatientException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        UserDao userDao = factory.getUserDao();
        User user = userDao.getOne(login, password);

        if (user == NullUser.getInstance()) {
            throw new UserNotFoundException();
        } else if (user.getState().isBlocked()) {
            throw new UserBlockedException();
        } else if (user.getRole().isPatient()) {
            throw new UserPatientException();
        }

        userDao.authorize(user.getId());

        return user;
    }

    public static boolean logout(int id) {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        UserDao userDao = factory.getUserDao();
        return userDao.unauthorize(id);
    }

    public static void create(User user, int specializationId) throws UserExistsException {
        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        UserDao userDao = factory.getUserDao();

        if (!userDao.getOne(user.getLogin()).isNull()) {
            throw new UserExistsException();
        }

        boolean isCreated = userDao.create(user);

        if (user.getRole().isDoctor()) {
            DoctorDao doctorDao = factory.getDoctorDao();
            user = userDao.getOne(user.getLogin(), user.getPassword());
            isCreated &= doctorDao.setSpecialization(user.getId(), specializationId);
        }

        if (!isCreated) {
            throw new IllegalArgumentException();
        }
    }

    public static User fromRequest(HttpServletRequest request) throws UserUnAuthorizedException, UserBlockedException {
        Object object = request.getSession().getAttribute(PARAMETER_USER);
        if (!(object instanceof User)) {
            throw new UserUnAuthorizedException();
        }
        User user = (User) object;

        DaoFactory factory = DaoFactory.getInstance(DbType.ORACLE);
        UserDao userDao = factory.getUserDao();
        User updated = userDao.getOne(user.getId());

        if (updated == NullUser.getInstance() || !updated.getState().isAuthorized()) {
            throw new UserBlockedException();
        }

        return (User) object;
    }

    public static User fromRequestNoThrow(HttpServletRequest request) {
        Object object = request.getSession().getAttribute(PARAMETER_USER);
        if (!(object instanceof User)) {
            return NullUser.getInstance();
        }
        return (User) object;
    }

    public static User fromSession(HttpSession session) {
        Object object = session.getAttribute(PARAMETER_USER);
        if (!(object instanceof User)) {
            return NullUser.getInstance();
        }
        return (User) object;
    }
}
