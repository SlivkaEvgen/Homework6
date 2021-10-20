package org.homework.util;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection implements Closeable {

    private static final String URL = PropertiesLoader.getProperties("db.url");
    private static final String USERNAME = PropertiesLoader.getProperties("db.name");
    private static final String PASSWORD = PropertiesLoader.getProperties("db.password");
    private static final String JDBC_DRIVER = PropertiesLoader.getProperties("db.driver");
    private static DatabaseConnection DATABASE_CONNECTION;
    private final Connection CONNECTION;

    @SneakyThrows
    public DatabaseConnection() {
        System.out.println("DatabaseConnection");
        Class.forName(JDBC_DRIVER);
        this.CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static DatabaseConnection getInstance() {
        System.out.println("DatabaseConnection getInstance");
        if (DATABASE_CONNECTION == null) {
            synchronized (DatabaseConnection.class) {
                if (DATABASE_CONNECTION == null) {
                    DATABASE_CONNECTION = new DatabaseConnection();
                }
            }
        }
        return DATABASE_CONNECTION;
    }

    public Connection getConnection() {
        System.out.println("Base getConnection");
        return CONNECTION;
    }

    @SneakyThrows
    @Override
    public void close() {
        System.out.println("Base close");
        CONNECTION.close();
    }
}