package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core;

import java.sql.CallableStatement;
import java.sql.SQLException;

public interface CallableStatementRunnable {
    void implement(CallableStatement statement) throws SQLException;

    default void execute(CallableStatement statement) throws SQLException {
        this.implement(statement);
    }
}
