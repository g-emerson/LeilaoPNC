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
import javax.servlet.http.HttpSession;
import pnc.leilaoVerde.controle.RealizarLanceControl;
import pnc.leilaoVerde.dominio.LanceLeilaoException;
import pnc.leilaoVerde.dominio.administrativo.Usuario;
import pnc.leilaoVerde.dominio.entidades.Entidade;

/**
 *
 * @author giovani
 */
public class RealizarLance extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        Usuario usu = null;
        if (session != null) {
            usu = (Usuario) session.getAttribute("usuario");
        }
        if (usu != null) {
            try {

                Long leilaoID = Long.parseLong(request.getParameter("leilao_id"));

                RealizarLanceControl control = new RealizarLanceControl(leilaoID);

                request.setAttribute("leilao", control.getLeilao());

                /* Montando os componentes da view */
                request.setAttribute("title", "Realizar Lance");
                request.setAttribute("menuContexto", "menuEntidades.jsp");
                request.setAttribute("main", "RealizarLance.jsp");

                RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/leilao-template.jsp");
                view.forward(request, response);
            } finally {
            }
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
        HttpSession session = request.getSession(false);
        Usuario usu = null;
        if (session != null) {
            usu = (Usuario) session.getAttribute("usuario");
        }
        if (usu != null) {

            /* Montando os componentes da view */
            request.setAttribute("title", "Realizar Lance");
            request.setAttribute("menuContexto", "menuEntidades.jsp");
            request.setAttribute("main", "ResultadoOperacao.jsp");

            try {
                Entidade ent = (Entidade) usu;
                double valor = Double.parseDouble(request.getParameter("valorLance"));
                Long leilaoID = Long.parseLong(request.getParameter("leilao_id"));

                RealizarLanceControl control = new RealizarLanceControl(leilaoID);

                control.cadastrarLance(valor, ent);

                request.setAttribute("resultado", "Lance realizado com sucesso.");
            } catch (LanceLeilaoException e) {
                request.setAttribute("resultado", e.getMessage());
            } finally {
            }
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/leilao-template.jsp");
            view.forward(request, response);
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
