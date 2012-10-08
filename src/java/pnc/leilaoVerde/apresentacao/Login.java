/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.apresentacao;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pnc.leilaoVerde.controle.LoginControl;
import pnc.leilaoVerde.dominio.administrativo.Usuario;

/**
 *
 * @author giovani
 */
public class Login extends HttpServlet {

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("title", "Login");
        request.setAttribute("menuContexto", "menuGeral.jsp");
        request.setAttribute("main", "Login.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/leilao-template.jsp");
        rd.forward(request, response);
    }

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
        response.setContentType("text/html;charset=UTF-8");

        String senha = request.getParameter("passwd");
        String login = request.getParameter("email");
        boolean autenticado = false;

        LoginControl lc = new LoginControl();

        autenticado = lc.autenticarUsuario(login, senha);

        if (autenticado) {
            Usuario usu = lc.getUsuario();
            request.getSession().setAttribute("usuario", usu);

            if (usu.isAdmin()) {
                request.setAttribute("menuContexto", "menuAdministrador.jsp");
            } else {
                request.setAttribute("menuContexto", "menuEntidades.jsp");
            }
            request.setAttribute("resultado", "Bem vindo !!");
        }
        else {
            request.setAttribute("menuContexto", "menuGeral.jsp");
            request.setAttribute("resultado", "Autenticação falhou !!");
        }

        request.setAttribute("title", "Login");

        request.setAttribute("main", "ResultadoOperacao.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/leilao-template.jsp");
        rd.forward(request, response);
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
