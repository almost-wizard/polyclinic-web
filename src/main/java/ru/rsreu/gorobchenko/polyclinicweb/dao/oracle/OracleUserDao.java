package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle;

import ru.rsreu.gorobchenko.polyclinicweb.dao.UserDao;
import ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core.BaseOracleDao;
import ru.rsreu.gorobchenko.polyclinicweb.factory.UserFactory;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.NullUser;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.User;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserRole;
import ru.rsreu.gorobchenko.polyclinicweb.model.user.types.UserState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class OracleUserDao extends BaseOracleDao<User> implements UserDao {
    public OracleUserDao(Connection connection) {
        super(connection, NullUser.getInstance(), new UserFactory());
    }

    @Override
    public User getOne(String login, String password) {
        return super.getOne("user.one.by.login.password", (PreparedStatement statement) -> {
            statement.setString(1, login);
            statement.setString(2, password);
        });
    }

    @Override
    public User getOne(String login) {
        return super.getOne("user.one.by.login", (PreparedStatement statement) -> {
            statement.setString(1, login);
        });
    }

    @Override
    public User getOne(int id) {
        return super.getOne("user.one.by.id", (PreparedStatement statement) -> statement.setInt(1, id));
    }
    
    @Override
    public List<User> getAll() {
        return super.getAll("user.all");
    }

    @Override
    public List<User> getAll(UserState state) {
        return super.getAll("user.all.filter", (PreparedStatement statement) -> {
            statement.setBoolean(1, state.isBlocked());
            statement.setBoolean(2, state.isAuthorized());
        });
    }

    @Override
    public boolean create(User user) {
        return super.update("user.create", (PreparedStatement statement) -> {
            UserFactory.prepareUpdate(statement, user);
        });
    }

    @Override
    public boolean edit(int id, User user) {
        return super.update("user.edit", (PreparedStatement statement) -> {
            UserFactory.prepareUpdate(statement, user);
            statement.setInt(8, id);
        });
    }

    @Override
    public boolean delete(int id) {
        return super.update("user.delete", (PreparedStatement statement) -> statement.setInt(1, id));
    }

    @Override
    public boolean block(int id) {
        return super.update("user.block", (PreparedStatement statement) -> statement.setInt(1, id));
    }

    @Override
    public boolean unblock(int id) {
        return super.update("user.unblock", (PreparedStatement statement) -> statement.setInt(1, id));
    }

    @Override
    public List<User> find(String pattern, UserRole role) {
        String maskPattern = "%" + pattern + "%";
        return super.getAll("user.find", (PreparedStatement statement) -> {
            statement.setString(1, maskPattern);
            statement.setString(2, maskPattern);
            statement.setString(3, maskPattern);
            statement.setString(4, maskPattern);
            statement.setInt(5, role.getDatabaseRoleId());
        });
    }

    @Override
    public boolean authorize(int id) {
        return super.update("user.authorize", (PreparedStatement statement) -> statement.setInt(1, id));
    }

    @Override
    public boolean unauthorize(int id) {
        return super.update("user.unauthorize", (PreparedStatement statement) -> statement.setInt(1, id));
    }
}
