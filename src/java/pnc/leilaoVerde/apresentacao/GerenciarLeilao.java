/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnc.leilaoVerde.apresentacao;

import java.io.IOException;
import java.rmi.ServerException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pnc.leilaoVerde.controle.GerenciarLeilaoControl;
import pnc.leilaoVerde.dominio.Leilao;
import pnc.leilaoVerde.dominio.administrativo.Usuario;

/**
 *
 * @author giovani
 */
public class GerenciarLeilao extends HttpServlet {

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
        if (session != null) {
            Usuario usu = (Usuario) session.getAttribute("usuario");

            if (usu != null && usu.isAdmin()) {
                Long leilaoId = Long.parseLong(request.getParameter("leilao_id"));

                request.setAttribute("menuContexto", "menuAdministrador.jsp");

                try {
                    GerenciarLeilaoControl control = new GerenciarLeilaoControl(leilaoId);

                    request.setAttribute("leilao", control.getLeilao());
                    request.setAttribute("menuContexto", "menuAdministrador.jsp");
                    request.setAttribute("main", "GerenciarLeilao.jsp");
                } catch (Exception e) {
                    request.setAttribute("resultado", e.getMessage());
                    request.setAttribute("main", "ResultadoOperacao.jsp");
                }
            } else {
                request.setAttribute("resultado", "Usuário não tem permissão para realizar a operação!");
                request.setAttribute("menuContexto", "menuGeral.jsp");
                request.setAttribute("main", "ResultadoOperacao.jsp");
            }
        } else {
            request.setAttribute("resultado", "Sessão expirada!");
            request.setAttribute("menuContexto", "menuGeral.jsp");
            request.setAttribute("main", "ResultadoOperacao.jsp");
        }
        /* Montando os componentes da view */
        request.setAttribute("title", "Gerenciar leilão");
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
        HttpSession session = request.getSession(false);
        Usuario user = null;

        if (session == null || session.getAttribute("usuario") == null) {
            request.setAttribute("title", "Erro - sem sessão");
            request.setAttribute("menuContexto", "menuGeral.jsp");
            request.setAttribute("resultado", "Sessão encerrada ou inexistente ou usuário não logado.");
            request.setAttribute("main", "ResultadoOperacao.jsp");
        } else {
            user = (Usuario) session.getAttribute("usuario");

            if (user.isAdmin()) {
                request.setAttribute("menuContexto", "menuAdministrador.jsp");
                request.setAttribute("title", "Gerenciar Leilao");

                Long leilaoId = Long.parseLong(request.getParameter("leilao_id"));

                try {
                    GerenciarLeilaoControl control = new GerenciarLeilaoControl(leilaoId);

                    if (request.getParameter("acao").equals("Remover")) {
                        control.removerLeilao();

                        request.setAttribute("resultado", "Leilao removido!");
                        request.setAttribute("main", "ResultadoOperacao.jsp");
                    } else if (request.getParameter("acao").equals("Alterar")) {
                        Leilao leilao = control.getLeilao();

                        if (request.getParameter("nome") != null) {
                            leilao.setNomeLeilao(request.getParameter("nome"));
                        } else {
                            throw new Exception("Nome do leilao nulo!");
                        }

                        if (request.getParameter("lanceMinimo") != null) {
                            double lanceMinimo = Double.parseDouble(request.getParameter("lanceMinimo"));
                            leilao.setLanceMinimo(lanceMinimo);
                        }

                        if (request.getParameter("quantCER") != null) {
                            int quantCER = Integer.parseInt(request.getParameter("quantCER"));
                            leilao.setQuantidadeCER(quantCER);
                        }

                        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                        String data;

                        data = request.getParameter("dataInicial");
                        if (data != null && !"".equals(data)) {
                            leilao.setDataInicial(dateFormat.parse(data));
                        }

                        data = request.getParameter("dataFinal");
                        if (data != null && !"".equals(data)) {
                            leilao.setDataFinal(dateFormat.parse(data));
                        }

                        data = request.getParameter("horaInicial");
                        if (data != null && !"".equals(data)) {
                            leilao.setHoraInicial(timeFormat.parse(data));
                        }

                        data = request.getParameter("horaFinal");
                        if (data != null && !"".equals(data)) {
                            leilao.setHoraFinal(timeFormat.parse(data));
                        }

                        control.alterarLeilao();

                        request.setAttribute("title", "Gerenciar Leião");
                        request.setAttribute("resultado", "Alteração realizada.");
                        request.setAttribute("main", "ResultadoOperacao.jsp");
                    }
                } catch (Exception e) {
                    request.setAttribute("title", "Erro - Excecao");
                    request.setAttribute("resultado", e.getMessage());
                    request.setAttribute("main", "ResultadoOperacao.jsp");
                    throw new ServerException("erro", e);
                }
            } else {
                request.setAttribute("title", "Erro - Sem permissão");
                request.setAttribute("resultado", "Usuário não tem permissão para realizar a operação!");
                request.setAttribute("menuContexto", "menuEntidade.jsp");
                request.setAttribute("main", "ResultadoOperacao.jsp");
            }
        }

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/leilao-template.jsp");
        view.forward(request, response);
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
