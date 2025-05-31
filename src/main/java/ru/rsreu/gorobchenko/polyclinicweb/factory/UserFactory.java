package ru.rsreu.gorobchenko.polyclinicweb.factory;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.ModelFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFactory implements ModelFactory<User> {
    public static final String PARAMETER_ID_NAME = "USER_ID";
    public static final String PARAMETER_LOGIN_NAME = "LOGIN";
    public static final String PARAMETER_PASSWORD_NAME = "PASSWORD";
    public static final String PARAMETER_SURNAME_NAME = "SURNAME";
    public static final String PARAMETER_NAME_NAME = "NAME";
    public static final String PARAMETER_PATRONYMIC_NAME = "PATRONYMIC";
    public static final String PARAMETER_USER_GROUP_ID_NAME = "USER_GROUP_ID";
    public static final String PARAMETER_REGISTRATION_DATE_NAME = "REGISTRATION_DATE";
    public static final String PARAMETER_IS_BLOCKED_NAME = "IS_BLOCKED";
    public static final String PARAMETER_IS_AUTHORIZED_NAME = "IS_AUTHORIZED";

    public static final int PARAMETER_LOGIN_INDEX = 1;
    public static final int PARAMETER_PASSWORD_INDEX = 2;
    public static final int PARAMETER_SURNAME_INDEX = 3;
    public static final int PARAMETER_NAME_INDEX = 4;
    public static final int PARAMETER_PATRONYMIC_INDEX = 5;
    public static final int PARAMETER_ROLE_ID_INDEX = 6;
    public static final int PARAMETER_IS_BLOCKED_INDEX = 7;

    @Override
    public User fromResultSet(ResultSet resultSet, String parametersPrefix) throws SQLException {
        int id = resultSet.getInt(parametersPrefix + PARAMETER_ID_NAME);
        String login = resultSet.getString(parametersPrefix + PARAMETER_LOGIN_NAME);
        String password = resultSet.getString(parametersPrefix + PARAMETER_PASSWORD_NAME);
        String surname = resultSet.getString(parametersPrefix + PARAMETER_SURNAME_NAME);
        String name = resultSet.getString(parametersPrefix + PARAMETER_NAME_NAME);
        String patronymic = resultSet.getString(parametersPrefix + PARAMETER_PATRONYMIC_NAME);
        int roleId = resultSet.getInt(parametersPrefix + PARAMETER_USER_GROUP_ID_NAME);
        Date registrationDate = resultSet.getDate(parametersPrefix + PARAMETER_REGISTRATION_DATE_NAME);
        boolean isBlocked = resultSet.getBoolean(parametersPrefix + PARAMETER_IS_BLOCKED_NAME);
        boolean isAuthorized = resultSet.getBoolean(parametersPrefix + PARAMETER_IS_AUTHORIZED_NAME);
        return new User(id, login, password, surname, name, patronymic, roleId, registrationDate, isBlocked, isAuthorized);
    }

    public static void prepareUpdate(PreparedStatement statement, User user) throws SQLException {
        statement.setString(PARAMETER_LOGIN_INDEX, user.getLogin());
        statement.setString(PARAMETER_PASSWORD_INDEX, user.getPassword());
        statement.setString(PARAMETER_SURNAME_INDEX, user.getSurname());
        statement.setString(PARAMETER_NAME_INDEX, user.getName());
        statement.setString(PARAMETER_PATRONYMIC_INDEX, user.getPatronymic());
        statement.setInt(PARAMETER_ROLE_ID_INDEX, user.getRole().getDatabaseRoleId());
        statement.setBoolean(PARAMETER_IS_BLOCKED_INDEX, user.getState().isBlocked());
    }
}
