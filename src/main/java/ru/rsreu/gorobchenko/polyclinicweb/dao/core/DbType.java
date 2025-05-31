package ru.rsreu.gorobchenko.polyclinicweb.dao.core;

import java.sql.SQLException;

public enum DbType {
    ORACLE {
        @Override
        public DaoFactory getDAOFactory() {
            DaoFactory oracleDbDaoFactory = null;
            try {
                oracleDbDaoFactory = OracleDbDaoFactory.getInstance();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return oracleDbDaoFactory;
        }
    };

    public static DbType getTypeByName(String dbType) {
        try {
            return DbType.valueOf(dbType.toUpperCase());
        } catch (Exception e) {
            throw new DbTypeException();
        }
    }

    public abstract DaoFactory getDAOFactory();

}
