<%-- 
    Document   : GerenciarLeilao
    Created on : Sep 23, 2012, 10:39:27 PM
    Author     : giovani
--%><%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <h2>${requestScope.title}</h2>
   <form method="post" action="${appRoot}/leilao/editar">
    <input type="hidden" name="leilao_id" value="${leilao.id}" />
    <label ><span>(ID)Nome do Leilão: </span><input type="text" name="nome" value="${leilao.nomeLeilao}"/></label><br/>
    <label ><span>Quantidade de CER oferecida: </span><input type="text" name="quantCER" value="${leilao.quantidadeCER}"/></label><br/>
    <label ><span>Valor mínimo do lance: </span><input type="text" name="lanceMinimo" value="${leilao.lanceMinimo}"/></label><br />
    <fmt:formatDate  value="${leilao.dataInicial}" pattern="dd/MM/yyyy" var="fmtData" />
    <label ><span>Data Inicial: </span><input type="text" name="dataInicial" value="${fmtData}"/></label><br/>
    <fmt:formatDate  value="${leilao.horaInicial}" pattern="HH:mm" var="fmtData" />
    <label ><span>Hora Inicial: </span><input type="text" name="horaInicial" value="${fmtData}" /></label><br/>
    <fmt:formatDate  value="${leilao.dataFinal}" pattern="dd/MM/yyyy" var="fmtData" />
    <label ><span>Data de Término: </span><input type="text" name="dataFinal" value="${fmtData}" /></label><br/>
    <fmt:formatDate  value="${leilao.horaFinal}" pattern="HH:mm" var="fmtData" />
    <label ><span>Hora de Término: </span><input type="text" name="horaFinal" value="${fmtData}" /></label><br/>
    <input type="submit" name="acao" value="Alterar" onclick="return confirm('Realmente deseja realizar a alteração ?');" />
    <input type="submit" name="acao" value="Remover" onclick="return confirm('Realmente deseja remover o leilao ?');" />
   </form>