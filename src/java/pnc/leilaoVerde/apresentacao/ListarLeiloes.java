/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.apresentacao;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pnc.leilaoVerde.controle.ListarLeiloesControl;
import pnc.leilaoVerde.dominio.Leilao;
import pnc.leilaoVerde.dominio.administrativo.Usuario;

/**
 *
 * @author Vinicius
 */
public class ListarLeiloes extends HttpServlet {

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

        ListarLeiloesControl control = new ListarLeiloesControl();

        List<Leilao> lista;

        lista = control.obterLeiloesFinalizados();
        request.setAttribute("leiloesFinalizados", lista);

        /* Montando os componentes da view */
        HttpSession session = request.getSession(false);
        Usuario usu = null;
        if (session != null) {
            usu = (Usuario) session.getAttribute("usuario");
        }

        if (usu != null) {
            if (usu.isAdmin()) {
                lista = control.obterLeiloesPropostos();
                request.setAttribute("leiloesPropostos", lista);

                lista = control.obterLeiloesAtivos();
                request.setAttribute("leiloesAprovados", lista);

                request.setAttribute("menuContexto", "menuAdministrador.jsp");
            } else {
                lista = control.obterLeiloesDaEntidade(usu.getId());
                request.setAttribute("leiloesPropostos", lista);

                lista = control.obterLeiloesDeOutrasEntidades(usu.getId());
                request.setAttribute("leiloesAprovados", lista);

                request.setAttribute("menuContexto", "menuEntidades.jsp");
            }
        } else {
            request.setAttribute("menuContexto", "menuGeral.jsp");
        }

        request.setAttribute("title", "Leilões");
        request.setAttribute("main", "ListarLeiloes.jsp");
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/leilao-template.jsp");
        view.forward(request, response);
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
