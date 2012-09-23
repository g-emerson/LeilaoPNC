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

/**
 *
 * @author giovani
 */
public class ProporLeilao extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO: criar a lógica do caso de uso utilizando o controlador do
             * modelo. */

            /* Montando os componentes da view */
            request.setAttribute("title", "Proposta de Leilão");
            request.setAttribute("menuContexto", "menuEntidades.jsp");
            request.setAttribute("main", "ProporLeilao.jsp");

			RequestDispatcher view =  request.getRequestDispatcher("WEB-INF/views/leilao-template.jsp");
			view.forward(request, response);
        }
		finally {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        /* TODO: Criar lógica para _recuperar_ o formulário de proposta de leilão */
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* Montando os componentes da view */
            request.setAttribute("title", "Proposta de Leilão");
            request.setAttribute("menuContexto", "menuEntidades.jsp");
            request.setAttribute("main", "ProporLeilao.jsp");

            RequestDispatcher view =  request.getRequestDispatcher("WEB-INF/views/leilao-template.jsp");
            view.forward(request, response);
        }
        finally {
        }
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
        /* TODO: Implementar lógica de processar uma proposta de leilão */
        response.setContentType("text/html;charset=UTF-8");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
