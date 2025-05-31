package ru.rsreu.gorobchenko.polyclinicweb.dao.oracle.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public interface ModelFactory<T> {
    T fromResultSet(ResultSet resultSet, String parametersPrefix) throws SQLException, ParseException;
}
