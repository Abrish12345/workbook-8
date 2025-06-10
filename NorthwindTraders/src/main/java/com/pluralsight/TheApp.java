package com.pluralsight;
import java.sql.*;

public class TheApp {
    public static void main(String[] args) throws SQLException {
        try {
            //1. open a connection to the database
            // use the database URL to point the correct database

            //this is like MySQL workbench and clicking localhost

            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "Barkot");

            //create statement
            //the statement is tied to the open connection

            //this one is like opening a new query window
            Statement statement = connection.createStatement();

            //define your query

            //this one is like typing the query in the new query windows
            String query = "SELECT productId, productName, unitPrice, unitsInStock  FROM products";

            //2. Execute your query

            //this is like me clicking the lightning bolt
            ResultSet results = statement.executeQuery(query);

            //process the result
            //this is a way to view the result set
            while (results.next()) {
                int productId = results.getInt("productId");
                String productName = results.getString("ProductName");
                double unitPrice = results.getDouble("unitPrice");
                int unitsInStock = results.getInt("unitsInStock");

                System.out.println("Product Id: " + productId);
                System.out.println("Name: " + productName);
                System.out.println("Price " + unitPrice);
                System.out.println("Stock: " + unitsInStock);
                System.out.println("------------------");

            }

            // 3. Close the connection
            connection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}


