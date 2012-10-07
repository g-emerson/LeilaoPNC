/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.apresentacao;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pnc.leilaoVerde.controle.ValidarEntidadeControl;
import pnc.leilaoVerde.dominio.administrativo.Usuario;

/**
 *
 * @author giovani
 */
public class ValidarEntidade extends HttpServlet {

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            if (session != null) {
                Usuario usu = (Usuario) session.getAttribute("usuario");

                if (usu != null && usu.isAdmin()) {
                    Long entId = Long.getLong(request.getParameter("entId"));
                    boolean validar = Boolean.parseBoolean(request.getParameter("validar"));
                    ValidarEntidadeControl control = new ValidarEntidadeControl();

                    control.validarEntidade(entId);

                    out.printf("{ \"resultado\":\"true\", \"entId\":\"%l\" }",
                            entId);

                } else {
                    out.printf("{ \"resultado\":\"false\", \"erro_msg\":\"%s\" }",
                            "Usuário sem permissão para realizar a operação");
                }
            } else {
                out.printf("{ \"resultado\":\"false\", \"erro_msg\":\"%s\" }",
                        "Sessão expirada");
            }
        } catch (Exception e) {
            out.printf("{ \"resultado\":\"false\", \"erro_msg\":\"%s\" }",
                    e.getMessage());
        } finally {
            out.close();
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
