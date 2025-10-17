/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.authenticationsystem.bean;

/**
 *
 * @author kekem
 */
public class User {
    private String email;
    private String passwordHash;
    
    public User(){}
    
    public User(String email){
        this.email = email;
    }
    
    public User(String email, String passwordHash){
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    
}
