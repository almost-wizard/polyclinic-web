package ru.rsreu.gorobchenko.polyclinicweb.model.user;

import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserState;

import java.sql.Date;

/**
 *  Represents a user in the system. This class stores common user information.
 *  It serves as a parent class for more specific user types and provides accessors for user attributes.
 */
public class BaseUser {
    private final int id;
    private final String login;
    private final String password;
    private final String surname;
    private final String name;
    private final String patronymic;
    private final UserRole role;
    private final Date registrationDate;
    private final UserState userState;

    /**
     * Constructs a new {@code BaseUser} with the specified attributes.
     *
     * @param id              The unique identifier for the user.
     * @param login           The login username for the user.
     * @param password        The password for the user.
     * @param surname         The surname of the user.
     * @param name            The first name of the user.
     * @param patronymic      The patronymic of the user.
     * @param roleId          The ID representing the user's role.
     * @param registrationDate The date the user registered.
     * @param isBlocked       Indicates if the user is blocked.
     * @param isAuthorized    Indicates if the user is authorized.
     */
    public BaseUser(int id,
                    String login,
                    String password,
                    String surname,
                    String name,
                    String patronymic,
                    int roleId,
                    Date registrationDate,
                    boolean isBlocked,
                    boolean isAuthorized) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.role = UserRole.construct(roleId);
        this.registrationDate = registrationDate;
        this.userState = UserState.construct(isAuthorized, isBlocked);
    }

    /**
     * Constructs a new {@code BaseUser} as a copy of another {@code BaseUser}.
     *
     * @param user The {@code BaseUser} to copy.
     */
    public BaseUser(BaseUser user) {
        this.id = user.id;
        this.login = user.login;
        this.password = user.password;
        this.surname = user.surname;
        this.name = user.name;
        this.patronymic = user.patronymic;
        this.role = user.role;
        this.registrationDate = user.registrationDate;
        this.userState = user.userState;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the login username of the user.
     *
     * @return The user's login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Returns the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the surname of the user.
     *
     * @return The user's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the first name of the user.
     *
     * @return The user's first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patronymic of the user.
     *
     * @return The user's patronymic.
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Returns the role of the user.
     *
     * @return The user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Returns the date of user registration.
     *
     * @return The user's registration date.
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Returns the state of the user.
     *
     * @return The user's state.
     */
    public UserState getState() {
        return userState;
    }

    /**
     * Returns the full name of the user.
     *
     * @return The user's full name as "Surname Name Patronymic".
     */
    public String getFullName() {
        return String.format("%s %s %s", getSurname(), getName(), getPatronymic());
    }
}
