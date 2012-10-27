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
import pnc.leilaoVerde.controle.CadastrarEntidadeControl;
import pnc.leilaoVerde.dominio.entidades.SegmentoMercado;

/**
 *
 * @author giovani
 */
public class CadastrarEntidade extends HttpServlet {

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
        
        CadastrarEntidadeControl cadEnt = new CadastrarEntidadeControl();
        
        try {
            //teste github applicaçao
            List<SegmentoMercado> lista = cadEnt.getListaSegmentos();
            
            request.setAttribute("listaSegmentoMercado", lista);
            
            request.setAttribute("title", "Cadastro de Entidade");
            request.setAttribute("menuContexto", "menuGeral.jsp");
            request.setAttribute("main", "CadastrarEntidade.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/leilao-template.jsp");
            rd.forward(request, response);
        } finally {
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
        String resultado = "";
        response.setContentType("text/html;charset=UTF-8");

        CadastrarEntidadeControl cadEnt = new CadastrarEntidadeControl();

        try {
            cadEnt.setCNPJ(request.getParameter("cnpj"));
            cadEnt.setNome(request.getParameter("nome"));
            cadEnt.setQuantidadeCER(Integer.parseInt(request.getParameter("quantCER")));
            cadEnt.setIdSegMe(Long.parseLong(request.getParameter("segmentoMercado")));
            // TODO: CadastrarEntidade - Testar se a senha enviada é igual à senha de confirmação
            cadEnt.setSenha(request.getParameter("passwd"));
            cadEnt.setEmail(request.getParameter("email"));

            cadEnt.cadastrarEntidade();

            resultado = String.format("Cadastro realizado com sucesso ! ID = %d",
                    cadEnt.getEntidadeCadastrada().getId());

            HttpSession session = request.getSession(true);
            session.setAttribute("usuario", cadEnt.getEntidadeCadastrada());
            request.setAttribute("menuContexto", "menuEntidades.jsp");
        } catch (Exception ex) {
            resultado = "Falha no cadastro: " + ex.getMessage();
            request.setAttribute("menuContexto", "menuGeral.jsp");
        }

        request.setAttribute("resultado", resultado);

        request.setAttribute("title", "Cadastro de Entidade");
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
    }// </editor-fold>
}
