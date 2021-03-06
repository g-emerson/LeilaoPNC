/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.apresentacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pnc.leilaoVerde.controle.ListarEntidadesControl;
import pnc.leilaoVerde.dominio.administrativo.Usuario;
import pnc.leilaoVerde.dominio.entidades.Entidade;
import pnc.leilaoVerde.dominio.entidades.EstadoEntidade;

/**
 *
 * @author Vinicius
 */
public class ListarEntidades extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            ListarEntidadesControl control = new ListarEntidadesControl();

            List<Entidade> lista = control.getRankingDeEntidades();

            request.setAttribute("rankingEntidades", lista);

            /* Montando os componentes da view */
            HttpSession session = request.getSession(false);
            Usuario usu = null;
            if (session != null) {
                usu = (Usuario) session.getAttribute("usuario");
            }
            if ( usu != null ) {
                if (usu.isAdmin()) {
                    request.setAttribute("menuContexto", "menuAdministrador.jsp");

                    lista = control.getEntidadesPeloStatus(EstadoEntidade.PENDENTE);
                    request.setAttribute("listaPendentes", lista);
                }
                else {
                    request.setAttribute("menuContexto", "menuEntidades.jsp");
                }
            }
            else {
                request.setAttribute("menuContexto", "menuGeral.jsp");
            }

            request.setAttribute("title", "Entidades");
            request.setAttribute("main", "ListarEntidades.jsp");
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/leilao-template.jsp");
            view.forward(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
