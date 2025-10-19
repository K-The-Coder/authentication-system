/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.web.authenticationsystem.utility;

import com.google.gson.Gson;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author kekem
 */
public class ResponseUtils {
    private final Gson gson = new Gson();
    
    public void sendJsonResponse(HttpServletResponse response, int statusCode, Map<String, Object> data) throws IOException {
        response.setStatus(statusCode);
        try (PrintWriter out = response.getWriter()){
            out.write(gson.toJson(data));
        }
    }
    
    public void setupResponseHeaders(HttpServletResponse response){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // Use your frontend's origin
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }
}
