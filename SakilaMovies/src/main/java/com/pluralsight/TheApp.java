package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TheApp {
    public static void main(String[] args) {
        if (args.length != 2) {
            //CHECK IF THE CORRECT NUMBER OF ARGUMENTS USERNAME AND PASSWORD ARE PROVIDED
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.UsingDriverManager <userName> <password>"
            );
            System.exit(1); // Exit if arguments are not provided
        }
        // Check if username and password are provided as command line arguments
        // Get username and password from command line arguments
        String userName = args[0];
        String password = args[1];
        Scanner scanner = new Scanner(System.in);

        //CREATE AND CONFIGURES A BASICDATASOURCE FOR MANAGING DB CONNECTIONS
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        try (Connection connection = dataSource.getConnection()) {
            //TRY TO CONNECT TO THE DATABASE USING THE DATA SOURCE

            System.out.println("Enter an actors Last name");
            String LastName = scanner.nextLine(); //GETS THE ACTORS LAST NAME FROM THE USER

            //PREPARES SQL QUERY TO FIND ACTORS BY LAST NAME
            String actorSearch = "SELECT actor_id, first_name, last_name FROM actor WHERE last_name=?";

            try (PreparedStatement statement = connection.prepareStatement(actorSearch)) {

                statement.setString(1, LastName); //SETS THE LAST NAME PARAMETER IN THE QUERY
                try (ResultSet results = statement.executeQuery()) {  //EXECUTES THE QUERY AND GETS THE RESULTS

                    if (results.next()) { //CHECKS IF ANY ACTORES MATCHED ROWS
                        System.out.println("Matching actors: ");
                        do {

                            //ITERATES THROUGH ALL MATCHING ROWS
                            int id = results.getInt("actor_id");
                            String firstName = results.getString("first_name");
                            String lastName = results.getString("last_name");

                            //PRINTS THE ACTORS ID AND FULL NAME
                            System.out.printf("ID: %d - %s %s%n", id, firstName, lastName);
                        } while (results.next());
                    } else {
                        //NO ACTORS FOUND WITH THAT LAST NAME
                        System.out.println("no actors found with that last name.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }

}
