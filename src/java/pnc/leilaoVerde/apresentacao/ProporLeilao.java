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
import pnc.leilaoVerde.controle.ProporLeilaoControl;
import pnc.leilaoVerde.dominio.administrativo.Usuario;

/**
 *
 * @author giovani
 */
public class ProporLeilao extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        Usuario user = null;

        if (session == null || session.getAttribute("usuario") == null) {
            request.setAttribute("title", "Erro - sem sessão");
            request.setAttribute("menuContexto", "menuGeral.jsp");
            request.setAttribute("resultado", "Sessão encerrada ou inexistente ou usuário não logado.");
            request.setAttribute("main", "ResultadoOperacao.jsp");
        } else {
            user = (Usuario) session.getAttribute("usuario");
            ProporLeilaoControl control = new ProporLeilaoControl(user.getId());
            try {
                request.setAttribute("qteCERsDisponiveis", control.obterCERsDisponiveis());
                request.setAttribute("entidadeValidada", control.isEntidadeValidada());

                request.setAttribute("title", "Proposta de Leilão");
                request.setAttribute("menuContexto", "menuEntidades.jsp");
                request.setAttribute("main", "ProporLeilao.jsp");

            }
            catch (Exception e) {
                request.setAttribute("resultado", e.getMessage());
                request.setAttribute("title", "Erro - Proposta de Leilão");
                request.setAttribute("menuContexto", "menuEntidades.jsp");
                request.setAttribute("main", "ResultadoOperacao.jsp");                
            }
        }

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/leilao-template.jsp");
        view.forward(request, response);
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
        /* TODO: Propor Leilão - Implementar lógica de processar uma proposta de leilão */
        response.setContentType("text/html;charset=UTF-8");
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
