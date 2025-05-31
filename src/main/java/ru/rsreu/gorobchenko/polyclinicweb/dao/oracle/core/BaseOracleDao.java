package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core;

import ru.rsreu.gorobchenko.polyclinicweb.exception.SqlProcedureException;
import ru.rsreu.gorobchenko.polyclinicweb.resource.QueryManager;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BaseOracleDao<T extends Nullable<T>> {
    private final Connection connection;

    private final T nullObject;
    private final ModelFactory<T> factory;

    public BaseOracleDao(Connection connection, T nullObject, ModelFactory<T> factory) {
        this.connection = connection;
        this.nullObject = nullObject;
        this.factory = factory;
    }

    public T getOne(String queryName, PrepareStatementRunnable prepareStatementRunnable) {
        return this._getOne(queryName, prepareStatementRunnable);
    }

    public T getOne(String queryName) {
        return this._getOne(queryName, (PreparedStatement ps) -> {});
    }

    public List<T> getAll(String queryName, PrepareStatementRunnable prepareStatementRunnable) {
        return this._getAll(queryName, prepareStatementRunnable);
    }

    public List<T> getAll(String queryName) {
        return this._getAll(queryName, (PreparedStatement ps) -> {});
    }

    public boolean update(String queryName, PrepareStatementRunnable prepareStatementRunnable) {
        return this._update(queryName, prepareStatementRunnable);
    }

    public void execute(String queryName, CallableStatementRunnable callableStatementRunnable) throws SqlProcedureException {
        this._execute(queryName, callableStatementRunnable);
    }

    private T _getOne(String queryName, PrepareStatementRunnable prepareStatementRunnable) {
        String query = QueryManager.get(queryName);

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatementRunnable.execute(statement);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return factory.fromResultSet(resultSet, "");
            }
        } catch (SQLException | ParseException ignored) {

        }

        return nullObject;
    }

    private List<T> _getAll(String queryName, PrepareStatementRunnable prepareStatementRunnable) {
        List<T> results = new ArrayList<>();
        String query = QueryManager.get(queryName);

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatementRunnable.execute(statement);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                results.add(factory.fromResultSet(resultSet, ""));
            }
        } catch (SQLException | ParseException ignored) {

        }

        return results;
    }

    private boolean _update(String queryName, PrepareStatementRunnable prepareStatementRunnable) {
        String query = QueryManager.get(queryName);

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatementRunnable.execute(statement);

            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            return false;
        }
    }

    private void _execute(String queryName, CallableStatementRunnable callableStatementRunnable) throws SqlProcedureException {
        String query = QueryManager.get(queryName);

        try (CallableStatement statement = connection.prepareCall(query)) {
            callableStatementRunnable.execute(statement);
            statement.execute();
        } catch (SQLException ignored) {
            throw new SqlProcedureException();
        }
    }
}
