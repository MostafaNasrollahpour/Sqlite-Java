package org.example;


import java.sql.*;


public class DataBase {
    Connection connection = null;

    public DataBase(){

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            Statement statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT NOT NULL)";

            statement.executeUpdate(sql);

            System.out.println("Table created successfully");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            closeDataBase();
        }
    }

    public void insertData(String name, String email){

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            // Insert data
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            closeDataBase();
        }
    }

    public void selectData(){
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            // Query data
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            closeDataBase();
        }
    }

    public void updateData(String email, int id){
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            // Update data
            String sql = "UPDATE users SET email = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            System.out.println("Data updated successfully");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            closeDataBase();
        }
    }

    public void deleteData(int id){
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            // Delete data
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Data deleted successfully");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            closeDataBase();
        }
    }

    public void closeDataBase(){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}