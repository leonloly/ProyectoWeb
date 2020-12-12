/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Autores;
import models.Paises;
import utils.Validation;

/**
 *
 * @author moi
 */
@WebServlet(name = "AutoresController", urlPatterns = {"/autores-controller"})
public class AutoresController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Map resMap = new HashMap<String, Object>();
            switch (request.getMethod()) {
                case "GET":
                    out.print(this.list(resMap));
                    break;
                case "POST":
                    out.print(this.save(request, resMap));
                    break;
                case "DELETE":
                    out.print(this.delete(request, resMap));
                    break;
            }
        }
    }

    private String delete(HttpServletRequest r, Map response) {
         response.put("id", r.getParameter("codigo"));
          response.put("message", "Procesado con exito");
        return new Gson().toJson(response);
    }

    private String list(Map response) {
        Autores a = new Autores();
        response.put("data", a.list());
        return new Gson().toJson(response);
    }

    private String save(HttpServletRequest r, Map response) throws IOException {
        Map params = Validation.requestMap(r.getReader());
        if (!params.isEmpty()) {
            Autores p = new Autores();
            if (params.get("codigo") != null) {
                p.setCodigo((int) Double.parseDouble(params.get("pais").toString()));
            }
            p.setNombre(params.get("nombre").toString());
            p.setCodigoPais(new Paises((int) Double.parseDouble(params.get("pais").toString())));
            if (p.save()) {
                response.put("message", "Procesado con exito");
            } else {
                response.put("message", "Error en el proceso");
            }
        } else {
            response.put("message", "sin parametros recibidos");
        }
        return new Gson().toJson(response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
