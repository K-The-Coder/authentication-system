/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.web.authenticationsystem;

import com.web.authenticationsystem.bean.User;
import com.web.authenticationsystem.database.UserDatabase;
import com.web.authenticationsystem.security.PasswordUtils;
import com.web.authenticationsystem.utility.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author kekem
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    private final ResponseUtils json = new ResponseUtils();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        json.setupResponseHeaders(response);
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (email == null || password == null || email.isBlank() || password.isBlank()){
            json.sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, Map.of(
                    "authenticated", false,
                    "error", "Email and password required!"
            ));
        }
        
        String storedHash = UserDatabase.getPasswordHash(email);
        
        if (storedHash != null && PasswordUtils.verifyPassword(password, storedHash)){
            HttpSession session = request.getSession();
            session.setAttribute("user", new User(email));
            
            json.sendJsonResponse(response, HttpServletResponse.SC_OK, Map.of(
                    "authenticated", true,
                    "email", email
            ));
            
        }
        else{
            json.sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, Map.of(
                    "authenticated", false,
                    "error", "Invalid Credentials"
            ));
            
        }
       
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        json.setupResponseHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
