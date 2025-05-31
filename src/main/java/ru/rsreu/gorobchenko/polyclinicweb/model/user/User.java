package ru.rsreu.gorobchenko.polyclinicweb.model.user;

import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.Nullable;

import java.sql.Date;

/**
 * Represents a user in the system, extending the {@link BaseUser} with additional nullability support.
 * This class inherits user attributes from {@code BaseUser} and implements the {@link Nullable} interface,
 * allowing it to represent both valid users and a null user instance.
 */
public class User extends BaseUser implements Nullable<User> {
    /**
     * Constructs a new {@code User} with the specified attributes.
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
    public User(int id,
                String login,
                String password,
                String surname,
                String name,
                String patronymic,
                int roleId,
                Date registrationDate,
                boolean isBlocked,
                boolean isAuthorized) {
        super(id, login, password, surname, name, patronymic, roleId, registrationDate, isBlocked, isAuthorized);
    }

    /**
     * Constructs a new {@code User} from a {@link BaseUser} object.
     *
     * @param baseUser The {@code BaseUser} to construct the User from.
     */
    public User(BaseUser baseUser) {
        super(baseUser);
    }

    /**
     * Checks if this {@code User} instance is the null user.
     *
     * @return {@code true} if this is the null user instance, {@code false} otherwise.
     */
    public boolean isNull() {
        return this == NullUser.getInstance();
    }

    /**
     * Returns the null user instance for this type.
     *
     * @return The {@link NullUser} instance.
     */
    @Override
    public User getNull() {
        return NullUser.getInstance();
    }
}
