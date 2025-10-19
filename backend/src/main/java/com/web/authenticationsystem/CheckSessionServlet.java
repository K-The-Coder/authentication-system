/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.web.authenticationsystem;

import com.google.gson.Gson;
import com.web.authenticationsystem.bean.User;
import com.web.authenticationsystem.utility.ResponseUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author kekem
 */
@WebServlet(name = "CheckSessionServlet", urlPatterns = {"/CheckSessionServlet"})
public class CheckSessionServlet extends HttpServlet {
    
    private final Gson gson = new Gson();
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
        response.setStatus(HttpServletResponse.SC_OK);
        
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            
            json.sendJsonResponse(response, HttpServletResponse.SC_OK, Map.of(
                    "authenticated", true,
                    "email", user.getEmail()
            ));
            
        }
        else{
            json.sendJsonResponse(response, HttpServletResponse.SC_OK, Map.of(
                    "authenticated", false
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
