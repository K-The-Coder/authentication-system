/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.authenticationsystem.database;

import java.sql.*;

/**
 *
 * @author kekem
 */
public class UserDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/authdb";
    private static final String DB_USER = "root";
    private static final String PASSWORD = "";
    
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception){
            throw new RuntimeException("Failed to load SQL Driver", exception);
        }
    }
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
    }
    
    public static boolean registerUser(String email, String passwordHash){
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, email);
            stmt.setString(2, passwordHash);
            stmt.executeUpdate();
            
            return true;
            
        }
        catch (SQLIntegrityConstraintViolationException exception){
            return false;
        }
        catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
       
    }
    
    public static String getPasswordHash(String email){
        String sql = "SELECT password FROM users WHERE email = ?";
        
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getString("password");
            }
            
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
        
        return null;
    }
}
