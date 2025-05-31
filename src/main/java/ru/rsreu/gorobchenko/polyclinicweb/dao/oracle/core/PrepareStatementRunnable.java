package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PrepareStatementRunnable {
    void implement(PreparedStatement statement) throws SQLException;

    default void execute(PreparedStatement statement) throws SQLException {
        this.implement(statement);
    }
}
