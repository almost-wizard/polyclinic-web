package ru.rsreu.gorobchenko.polyclinicweb.dao;

import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserState;

import java.util.List;

/**
 *  Defines the contract for accessing and managing user data.
 *  This interface provides methods for retrieving, creating, updating, and deleting user records
 *  from a data storage.
 */
public interface UserDao {
    /**
     * Retrieves a single {@link User} based on the provided login and password.
     *
     * @param login    The login username of the user.
     * @param password The password of the user.
     * @return The {@link User} object if found, otherwise, returns a null object instance.
     */
    User getOne(String login, String password);

    /**
     * Retrieves a single {@link User} based on the provided login.
     *
     * @param login    The login username of the user.
     * @return The {@link User} object if found, otherwise, returns a null object instance.
     */
    User getOne(String login);

    /**
     * Retrieves a single {@link User} based on the provided ID.
     *
     * @param id The unique identifier of the user.
     * @return The {@link User} object if found, otherwise, returns a null object instance.
     */
    User getOne(int id);

    /**
     * Retrieves all {@link User} objects from the data storage.
     *
     * @return A list containing all {@link User} objects.
     */
    List<User> getAll();

    /**
     * Retrieves all {@link User} objects with the given {@link UserState}.
     *
     * @param state The state of users to filter for.
     * @return A list containing all {@link User} objects that match the state.
     */
    List<User> getAll(UserState state);

    /**
     * Creates a new {@link User} in the data storage.
     *
     * @param user The {@link User} object to be created.
     * @return {@code true} if the user was created successfully, {@code false} otherwise.
     */
    boolean create(User user);

    /**
     * Edits an existing {@link User} in the data storage using its ID and a new {@link User} object.
     *
     * @param id      The unique identifier of the user to be edited.
     * @param newUser The {@link User} object containing the updated user information.
     * @return {@code true} if the user was edited successfully, {@code false} otherwise.
     */
    boolean edit(int id, User newUser);

    /**
     * Deletes a {@link User} from the data storage based on its ID.
     *
     * @param id The unique identifier of the user to be deleted.
     * @return {@code true} if the user was deleted successfully, {@code false} otherwise.
     */
    boolean delete(int id);

    /**
     * Blocks a {@link User} in the data storage based on its ID.
     *
     * @param id The unique identifier of the user to be blocked.
     * @return {@code true} if the user was blocked successfully, {@code false} otherwise.
     */
    boolean block(int id);

    /**
     * Unblocks a {@link User} in the data storage based on its ID.
     *
     * @param id The unique identifier of the user to be unblocked.
     * @return {@code true} if the user was unblocked successfully, {@code false} otherwise.
     */
    boolean unblock(int id);

    /**
     * Finds a list of {@link User} objects that match a pattern and have a given {@link UserRole}.
     *
     * @param pattern The pattern used for searching users.
     * @param role The role of users to filter for.
     * @return A list containing all {@link User} objects that match the pattern and role.
     */
    List<User> find(String pattern, UserRole role);

    /**
     * Authorizes a {@link User} in the data storage based on its ID.
     *
     * @param id The unique identifier of the user to be authorized.
     * @return {@code true} if the user was authorized successfully, {@code false} otherwise.
     */
    boolean authorize(int id);

    /**
     * Unauthorizes a {@link User} in the data storage based on its ID.
     *
     * @param id The unique identifier of the user to be unauthorized.
     * @return {@code true} if the user was unauthorized successfully, {@code false} otherwise.
     */
    boolean unauthorize(int id);
}
