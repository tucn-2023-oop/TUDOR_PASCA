package com.example.passwordmanager;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/password_manager";
    private static final String dbUser = "postgres";
    private static final String dbPassword = "123QWEasd";

    public static void writeUser(String username, String email, String password) {
        String query = "INSERT INTO \"user\"(username, password, email) VALUES(?, ?, ?);";
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void writeAccount(int userId, String title, String url, String username, String password) {
        String query = "INSERT INTO \"account\"(user_id, username, password, url, title) VALUES(?, ?, ?, ?, ?);";
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, url);
            preparedStatement.setString(5, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void writeAccount(int userId, Account account) {
        String query = "INSERT INTO \"account\"(user_id, username, password, url, title) VALUES(?, ?, ?, ?, ?);";
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getUrl());
            preparedStatement.setString(5, account.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void updateAccount(int userId, Account oldAccount, Account newAccount) {
        deleteAccountByTitle(oldAccount.getTitle());
        writeAccount(userId, newAccount);
    }

    public static boolean isUserPropertyTaken(String propertyName, String value) {
        String query = "SELECT * FROM \"user\" WHERE " + propertyName + "=?";
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, value);
            ResultSet result = preparedStatement.executeQuery();
            return result.isBeforeFirst();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return true;
        }
    }

    public static boolean isExistingUser(String username, String password) {
        String query = "SELECT * FROM \"user\" WHERE username=? AND password=?";
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            return result.isBeforeFirst();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return true;
        }
    }

    private static ResultSet getEntriesByUserId(String table, int userId) throws SQLException{
        String query = "SELECT * FROM \"%s\" WHERE user_id=?".formatted(table);
        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        return preparedStatement.executeQuery();
    }

    public static ArrayList<Account> getAccounts(int userId) {
        try {
            ResultSet result = Database.getEntriesByUserId("account", userId);
            ArrayList<Account> accountList = new ArrayList<>();
            while (result.next()) {
                String title = result.getString("title");
                String url = result.getString("url");
                String username = result.getString("username");
                String password = result.getString("password");
                Account account = new Account(title, url, username, password);
                accountList.add(account);
            }
            return accountList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static boolean isTitleTakenByUserId(int userId, String title) {
        String query = "SELECT * from \"account\" WHERE user_id=? AND title=?";
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, title);
            ResultSet result = preparedStatement.executeQuery();
            return result.isBeforeFirst();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return true;
        }
    }

    public static int getUserId(String username, String password) {
        String query = "SELECT id FROM \"user\" WHERE username=? AND password=?";
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getInt("id");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public static void deleteAccountByTitle(String title) {
        String query = "DELETE FROM \"account\" WHERE title=?";
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}