package com.example.demo;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "nishmitha", "nish");

            // Prepare the stored procedure call
            CallableStatement callableStatement = connection.prepareCall("{call read_deposits_by_roi(?, ?)}");
            callableStatement.setDouble(1, 0.05); // Example: Set ROI parameter
            callableStatement.registerOutParameter(2, Types.REF_CURSOR); // Register the OUT parameter

            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the result set from the OUT parameter
            ResultSet resultSet = (ResultSet) callableStatement.getObject(2);

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from the result set
                long depositId = resultSet.getLong("deposit_id");
                String depositName = resultSet.getString("deposit_name");
                double depositROI = resultSet.getDouble("deposit_roi");
                String depositType = resultSet.getString("deposit_type");
                String depositDescription = resultSet.getString("deposit_description");

                // Do something with the retrieved data (e.g., print it)
                System.out.println("Deposit ID: " + depositId);
                System.out.println("Deposit Name: " + depositName);
                System.out.println("Deposit ROI: " + depositROI);
                System.out.println("Deposit Type: " + depositType);
                System.out.println("Deposit Description: " + depositDescription);
                System.out.println("--------------------------------------");
            }

            // Close resources
            resultSet.close();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

